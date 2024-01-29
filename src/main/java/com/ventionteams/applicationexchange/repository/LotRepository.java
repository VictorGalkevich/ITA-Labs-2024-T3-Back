package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.container.JsonContainer;
import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.mapper.LotMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Repository
@RequiredArgsConstructor
public class LotRepository {
    private final JsonContainer jsonContainer;
    private final LotMapper mapper;
    private final Map<Integer, Lot> lotTree = new TreeMap<>();

    public List<Lot> findAll() {
        return lotTree.values().stream().toList();
    }

    public Lot save(Lot lot) {
        lotTree.put(lot.getId(), lot);
        return lot;
    }

    public Lot update(Integer id, LotUpdateDTO lotUpdateDTO) {
        Lot lot = Lot.builder()
                .id(id)
                .title(lotUpdateDTO.title())
                .category(lotUpdateDTO.category())
                .subcategory(lotUpdateDTO.subcategory())
                .quantity(lotUpdateDTO.quantity())
                .pricePerUnit(lotUpdateDTO.pricePerUnit())
                .location(lotUpdateDTO.location())
                .description(lotUpdateDTO.description())
                .status(lotUpdateDTO.status())
                .imageUrl(lotUpdateDTO.imageUrl())
                .build();

        return lotTree.replace(id, lot);
    }

    public Optional<Lot> findById(Integer id) {
        return Optional.ofNullable(lotTree.get(id));
    }

    public void delete(Integer id) {
        lotTree.remove(id);
    }

    @SneakyThrows
    @PostConstruct
    private void init() {
        for (LotReadDTO lot : jsonContainer.lots()) {
            Lot val = mapper.toLot(lot);
            lotTree.put(val.getId(), val);
        }
    }
}
