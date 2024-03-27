package com.ventionteams.applicationexchange.service;

import com.ventionteams.applicationexchange.annotation.TransactionalService;
import com.ventionteams.applicationexchange.dto.create.RequestCreateEditDto;
import com.ventionteams.applicationexchange.dto.create.UserAuthDto;
import com.ventionteams.applicationexchange.dto.read.BidForUserDto;
import com.ventionteams.applicationexchange.dto.read.RequestReadDto;
import com.ventionteams.applicationexchange.entity.Category;
import com.ventionteams.applicationexchange.entity.User;
import com.ventionteams.applicationexchange.entity.enumeration.LotStatus;
import com.ventionteams.applicationexchange.exception.UserNotRegisteredException;
import com.ventionteams.applicationexchange.mapper.RequestMapper;
import com.ventionteams.applicationexchange.repository.CategoryRepository;
import com.ventionteams.applicationexchange.repository.RequestRepository;
import com.ventionteams.applicationexchange.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@TransactionalService
@RequiredArgsConstructor
public class RequestService extends UserItemService {
    private final RequestRepository requestRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;

    public Page<RequestReadDto> findAll(Integer page, Integer limit, String status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return requestRepository.findByStatus(LotStatus.valueOf(status), req)
                .map(requestMapper::toReadDto);
    }

    public Optional<RequestReadDto> findById(Long id) {
        return requestRepository.findById(id)
                .map(requestMapper::toReadDto);
    }

    @Transactional
    public RequestReadDto create(RequestCreateEditDto dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        entityValidator.validate(user, () -> {throw new UserNotRegisteredException();});
        Optional<Category> category = categoryRepository.findById(dto.categoryId());
        entityValidator.validate(category, Category.class);
        return Optional.of(dto)
                .map(requestMapper::toPurchase)
                .map(x -> {
                    x.setUser(user.get());
                    x.setStatus(LotStatus.MODERATED);
                    return x;
                })
                .map(requestRepository::save)
                .map(requestMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<RequestReadDto> update(Long id, RequestCreateEditDto dto, UserAuthDto userDto) {
        Optional<User> user = userRepository.findById(userDto.id());
        entityValidator.validate(user, () -> {throw new UserNotRegisteredException();});
        Optional<Category> category = categoryRepository.findById(dto.categoryId());
        entityValidator.validate(category, Category.class);
        return requestRepository.findById(id)
                .map(request -> {
                    permissionValidator.validate(request, userDto);
                    requestMapper.map(request, dto);
                    return request;
                })
                .map(requestRepository::save)
                .map(requestMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Long id, UserAuthDto user) {
        return requestRepository.findById(id)
                .map(request -> {
                    permissionValidator.validate(request, user);
                    requestRepository.delete(request);
                    return true;
                })
                .orElse(false);
    }

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

    public Page<RequestReadDto> findAllRequests(UUID id, Integer page, Integer limit, LotStatus status) {
        PageRequest req = PageRequest.of(page - 1, limit);
        return requestRepository.findAllByUserId(id, req)
                .map(requestMapper::toReadDto);
    }
}
