package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.RequestCreateEditDto;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.OfferReadDto;
import com.ventionteams.applicationexchange.dto.read.RequestReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.PurchaseRequest;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.Currency;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.entity.enumeration.OfferStatus;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.OfferMapper;
import com.ventionteams.applicationexchange.mapper.RequestMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import com.ventionteams.applicationexchange.repository.OfferRepository;
import com.ventionteams.applicationexchange.repository.RequestRepository;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ventionteams.applicationexchange.entity.enumeration.Currency.USD;

@TransactionalService
@RequiredArgsConstructor
public class RequestService extends UserItemService {
    private final RequestRepository requestRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final RequestMapper requestMapper;
    private final OfferMapper offerMapper;
    private final RatesService ratesService;

    public Page<RequestReadDto> findAll(
            Integer page,
            Integer limit,
            String status,
            UserAuthDto user,
            Currency currency
    ) {
        PageRequest req = PageRequest.of(page - 1, limit);
        Currency users = userRepository.getCurrency(user.id());
        return requestRepository.findByStatus(LotStatus.valueOf(status), req)
                .map(request -> {
                    convertFromUSD(request, currency == null ? users : currency);
                    return request;
                })
                .map(requestMapper::toReadDto);
    }

    public Optional<RequestReadDto> findById(Long id, UserAuthDto user, Currency currency) {
        Currency users = userRepository.getCurrency(user.id());
        return requestRepository.findById(id)
                .map(request -> {
                    convertFromUSD(request, currency == null ? users : currency);
                    return request;
                })
                .map(requestMapper::toReadDto);
    }

    @Transactional
    public RequestReadDto create(RequestCreateEditDto dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {throw new UserNotRegisteredException();});
        Optional<Category> category = categoryRepository.findById(dto.categoryId());
        validateEntity(category, Category.class);
        return Optional.of(dto)
                .map(requestMapper::toPurchase)
                .map(request -> {
                    request.setUser(user.get());
                    request.setStatus(LotStatus.MODERATED);
                    convertToUSD(request);
                    return request;
                })
                .map(requestRepository::save)
                .map(requestMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<RequestReadDto> update(Long id, RequestCreateEditDto dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        validateEntity(user, () -> {throw new UserNotRegisteredException();});
        Optional<Category> category = categoryRepository.findById(dto.categoryId());
        validateEntity(category, Category.class);
        return requestRepository.findById(id)
                .map(request -> {
                    validatePermissions(request, userDto);
                    requestMapper.map(request, dto);
                    convertToUSD(request);
                    return request;
                })
                .map(requestRepository::save)
                .map(requestMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Long id, UserAuthDto user) {
        return requestRepository.findById(id)
                .map(request -> {
                    validatePermissions(request, user);
                    requestRepository.delete(request);
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public Optional<RequestReadDto> approve(Long id) {
        return requestRepository.findById(id)
                .map(request -> {
                    request.setStatus(LotStatus.ACTIVE);
                    request.setRejectMessage(null);
                    return request;
                })
                .map(requestRepository::save)
                .map(requestMapper::toReadDto);
    }

    @Transactional
    public Optional<RequestReadDto> reject(Long id, String message) {
        return requestRepository.findById(id)
                .map(request -> {
                    request.setStatus(LotStatus.CANCELLED);
                    request.setRejectMessage(message);
                    return request;
                })
                .map(requestRepository::save)
                .map(requestMapper::toReadDto);
    }

    public Page<RequestReadDto> findAllRequests(UUID id, Integer page, Integer limit, LotStatus status, Currency currency) {
        PageRequest req = PageRequest.of(page - 1, limit);
        Currency users = userRepository.getCurrency(id);
        return requestRepository.findAllByUserIdAndStatus(id, status, req)
                .map(request -> {
                    convertFromUSD(request, currency == null ? users : currency);
                    return request;
                })
                .map(requestMapper::toReadDto);
    }

    private void convertToUSD(PurchaseRequest request) {
        double total = ratesService.convertToUSD(request.getDesiredPrice(), request.getCurrency());
        double ppu = ratesService.convertToUSD(request.getPricePerUnit(), request.getCurrency());
        request.setDesiredPrice(total);
        request.setPricePerUnit(ppu);
    }

    private void convertFromUSD(PurchaseRequest request, Currency currency) {
        currency = currency == null ? USD : currency;
        double total = ratesService.convertFromUSD(request.getDesiredPrice(), currency);
        double ppu = ratesService.convertFromUSD(request.getPricePerUnit(), currency);
        request.setDesiredPrice(total);
        request.setPricePerUnit(ppu);
        request.setCurrency(currency);
    }

    public Page<OfferReadDto> findOffers(Long id, Integer page, Integer limit, String status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        List<OfferStatus> statuses = Arrays.stream(status.split(","))
                .map(String::trim)
                .map(OfferStatus::valueOf)
                .toList();
        return offerRepository.findAllByPurchaseRequestIdAndStatusIn(id, statuses,req)
                .map(offerMapper::toReadDto);
    }
}
