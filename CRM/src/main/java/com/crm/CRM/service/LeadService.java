package com.crm.CRM.service;

import com.crm.CRM.dto.LeadRequestDTO;
import com.crm.CRM.dto.LeadResponseDTO;
import com.crm.CRM.entity.Lead;
import com.crm.CRM.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    public LeadResponseDTO createLead(LeadRequestDTO leadRequest) {
        validateLeadRequest(leadRequest);
        Lead lead = new Lead();
        lead.setName(leadRequest.getName());
        lead.setEmail(leadRequest.getEmail());
        lead.setPhone(leadRequest.getPhone());
        lead.setStatus(leadRequest.getStatus());
        lead.setCompany(leadRequest.getCompany());
        lead.setLeadSource(leadRequest.getLeadSource());
        Lead savedLead = leadRepository.save(lead);
        return mapToDTO(savedLead);
    }

    public List<LeadResponseDTO> getAllLeads() {
        return leadRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Page<LeadResponseDTO> getLeads(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return leadRepository.findAll(pageable).map(this::mapToDTO);
    }

    public LeadResponseDTO getLeadById(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));
        return mapToDTO(lead);
    }

    public LeadResponseDTO updateLead(Long id, LeadRequestDTO leadRequest) {
        validateLeadRequest(leadRequest);
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));
        lead.setName(leadRequest.getName());
        lead.setEmail(leadRequest.getEmail());
        lead.setPhone(leadRequest.getPhone());
        lead.setStatus(leadRequest.getStatus());
        lead.setCompany(leadRequest.getCompany());
        lead.setLeadSource(leadRequest.getLeadSource());
        Lead updatedLead = leadRepository.save(lead);
        return mapToDTO(updatedLead);
    }

    public void deleteLead(Long id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + id));
        leadRepository.delete(lead);
    }

    public List<LeadResponseDTO> searchLeads(String query) {
        return leadRepository.findByNameContainingOrEmailContainingOrPhoneContaining(query, query, query)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<LeadResponseDTO> filterLeadsBySource(String leadSource) {
        return leadRepository.findByLeadSource(leadSource)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<LeadResponseDTO> bulkUpdateLeads(Map<Long, LeadRequestDTO> leadUpdates) {
        List<Lead> leads = leadUpdates.entrySet().stream()
                .map(entry -> {
                    Long id = entry.getKey();
                    LeadRequestDTO request = entry.getValue();
                    Lead lead = leadRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Lead not found: " + id));
                    lead.updateFromDTO(request);
                    return lead;
                })
                .collect(Collectors.toList());

        leadRepository.saveAll(leads);
        return leads.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void bulkDeleteLeads(List<Long> leadIds) {
        leadRepository.deleteAllById(leadIds);
    }

    private LeadResponseDTO mapToDTO(Lead lead) {
        LeadResponseDTO responseDTO = new LeadResponseDTO();
        responseDTO.setId(lead.getId());
        responseDTO.setName(lead.getName());
        responseDTO.setEmail(lead.getEmail());
        responseDTO.setPhone(lead.getPhone());
        responseDTO.setStatus(lead.getStatus());
        responseDTO.setCompany(lead.getCompany());
        responseDTO.setLeadSource(lead.getLeadSource());
        return responseDTO;
    }

    private void validateLeadRequest(LeadRequestDTO leadRequest) {
        if (!StringUtils.hasText(leadRequest.getName())) {
            throw new IllegalArgumentException("Lead name cannot be empty");
        }
        if (!StringUtils.hasText(leadRequest.getEmail())) {
            throw new IllegalArgumentException("Lead email cannot be empty");
        }
        if (!StringUtils.hasText(leadRequest.getPhone())) {
            throw new IllegalArgumentException("Lead phone cannot be empty");
        }
    }
}