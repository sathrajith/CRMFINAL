package com.crm.CRM.controller;

import com.crm.CRM.dto.DealsRequestDTO;
import com.crm.CRM.dto.DealsResponseDTO;
import com.crm.CRM.service.DealsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deals")
public class DealsController {

    @Autowired
    private DealsService dealsService;

    @PostMapping
    public ResponseEntity<DealsResponseDTO> createDeal(@RequestBody DealsRequestDTO dealsRequest) {
        DealsResponseDTO createdDeal = dealsService.createDeal(dealsRequest);
        return new ResponseEntity<>(createdDeal, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DealsResponseDTO>> getAllDeals() {
        List<DealsResponseDTO> deals = dealsService.getAllDeals();
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<DealsResponseDTO>> getDeals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dealname") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<DealsResponseDTO> deals = dealsService.getDeals(page, size, sortBy, direction);
        return ResponseEntity.ok(deals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealsResponseDTO> getDealById(@PathVariable Long id) {
        DealsResponseDTO deal = dealsService.getDealById(id);
        return ResponseEntity.ok(deal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealsResponseDTO> updateDeal(
            @PathVariable Long id,
            @RequestBody DealsRequestDTO dealsRequest) {
        DealsResponseDTO updatedDeal = dealsService.updateDeal(id, dealsRequest);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeal(@PathVariable Long id) {
        dealsService.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<DealsResponseDTO>> searchDeals(@RequestParam String query) {
        List<DealsResponseDTO> deals = dealsService.searchDeals(query);
        return ResponseEntity.ok(deals);
    }

    @PutMapping("/bulk-update")
    public ResponseEntity<List<DealsResponseDTO>> bulkUpdateDeals(@RequestBody Map<Long, DealsRequestDTO> dealsUpdates) {
        List<DealsResponseDTO> updatedDeals = dealsService.bulkUpdateDeals(dealsUpdates);
        return ResponseEntity.ok(updatedDeals);
    }

    @DeleteMapping("/bulk-delete")
    public ResponseEntity<Void> bulkDeleteDeals(@RequestBody List<Long> dealIds) {
        dealsService.bulkDeleteDeals(dealIds);
        return ResponseEntity.noContent().build();
    }
}
