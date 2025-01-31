package com.crm.CRM.controller;

import com.crm.CRM.dto.LeadRequestDTO;
import com.crm.CRM.dto.LeadResponseDTO;
import com.crm.CRM.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public ResponseEntity<LeadResponseDTO> createLead(@RequestBody LeadRequestDTO leadRequest) {
        LeadResponseDTO response = leadService.createLead(leadRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<LeadResponseDTO>> getAllLeads() {
        List<LeadResponseDTO> leads = leadService.getAllLeads();
        return ResponseEntity.ok(leads);
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<LeadResponseDTO>> getLeads(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<LeadResponseDTO> leads = leadService.getLeads(page, size, sortBy, direction);
        return ResponseEntity.ok(leads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadResponseDTO> getLeadById(@PathVariable Long id) {
        LeadResponseDTO lead = leadService.getLeadById(id);
        return ResponseEntity.ok(lead);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeadResponseDTO> updateLead(@PathVariable Long id, @RequestBody LeadRequestDTO leadRequest) {
        LeadResponseDTO updatedLead = leadService.updateLead(id, leadRequest);
        return ResponseEntity.ok(updatedLead);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LeadResponseDTO>> searchLeads(@RequestParam String query) {
        List<LeadResponseDTO> leads = leadService.searchLeads(query);
        return ResponseEntity.ok(leads);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LeadResponseDTO>> filterLeadsBySource(@RequestParam String leadSource) {
        List<LeadResponseDTO> leads = leadService.filterLeadsBySource(leadSource);
        return ResponseEntity.ok(leads);
    }

    @PutMapping("/bulk-update")
    public ResponseEntity<List<LeadResponseDTO>> bulkUpdateLeads(@RequestBody Map<Long, LeadRequestDTO> leadUpdates) {
        List<LeadResponseDTO> updatedLeads = leadService.bulkUpdateLeads(leadUpdates);
        return ResponseEntity.ok(updatedLeads);
    }

    @DeleteMapping("/bulk-delete")
    public ResponseEntity<Void> bulkDeleteLeads(@RequestBody List<Long> leadIds) {
        leadService.bulkDeleteLeads(leadIds);
        return ResponseEntity.noContent().build();
    }
}