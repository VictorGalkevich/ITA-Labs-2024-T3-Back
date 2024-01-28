package com.ventionteams.applicationexchange.controller;

import com.ventionteams.applicationexchange.dto.LotReadDTO;
import com.ventionteams.applicationexchange.dto.LotUpdateDTO;
import com.ventionteams.applicationexchange.entity.Lot;
import com.ventionteams.applicationexchange.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/lots")
@RequiredArgsConstructor
public class LotController {
    private final LotService lotService;

    @GetMapping
    public ResponseEntity<List<LotReadDTO>> findAll() {
        return ResponseEntity.ok(lotService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LotReadDTO>> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(lotService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LotReadDTO> create(@RequestBody Lot lot) {
        return ResponseEntity.ok(lotService.create(lot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotReadDTO> update(@PathVariable("id") Integer id, @RequestBody LotUpdateDTO lotUpdateDTO) {
        return ResponseEntity.ok(lotService.update(id, lotUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        lotService.delete(id);
        return new ResponseEntity<>(OK);
    }
}
