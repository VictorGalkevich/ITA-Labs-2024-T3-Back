package com.ventionteams.applicationexchange.repository;

import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.model.Lot;
import com.ventionteams.applicationexchange.parser.Parser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class LotRepository {
    List<Lot> lots = new Parser().parse().getLots();
    Map<Integer, Lot> lotTree =  lots
            .stream().collect(Collectors.toMap(Lot::getId, Function.identity()));

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
                .title(lotUpdateDTO.getTitle())
                .category(lotUpdateDTO.getCategory())
                .subcategory(lotUpdateDTO.getSubcategory())
                .quantity(lotUpdateDTO.getQuantity())
                .pricePerUnit(lotUpdateDTO.getPricePerUnit())
                .location(lotUpdateDTO.getLocation())
                .description(lotUpdateDTO.getDescription())
                .status(lotUpdateDTO.getStatus())
                .imageUrl(lotUpdateDTO.getImageUrl())
                .build();

        return lotTree.replace(id, lot);
    }

    public Lot findById(Integer id) {
        return lotTree.get(id);
    }

    public void delete(Integer id) {
        lotTree.remove(id);
    }
}
