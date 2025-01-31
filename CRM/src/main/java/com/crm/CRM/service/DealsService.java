package com.crm.CRM.service;

import com.crm.CRM.dto.DealsRequestDTO;
import com.crm.CRM.dto.DealsResponseDTO;
import com.crm.CRM.entity.Deals;
import com.crm.CRM.repository.DealsRepository;
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
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;

    public DealsResponseDTO createDeal(DealsRequestDTO dealsRequest) {
        validateDealRequest(dealsRequest);
        Deals deal = mapToEntity(dealsRequest);
        Deals savedDeal = dealsRepository.save(deal);
        return mapToDTO(savedDeal);
    }

    public List<DealsResponseDTO> getAllDeals() {
        return dealsRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Page<DealsResponseDTO> getDeals(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return dealsRepository.findAll(pageable).map(this::mapToDTO);
    }

    public DealsResponseDTO getDealById(Long id) {
        Deals deal = dealsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with id: " + id));
        return mapToDTO(deal);
    }

    public DealsResponseDTO updateDeal(Long id, DealsRequestDTO dealsRequest) {
        validateDealRequest(dealsRequest);
        Deals deal = dealsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with id: " + id));
        updateEntity(deal, dealsRequest);
        Deals updatedDeal = dealsRepository.save(deal);
        return mapToDTO(updatedDeal);
    }

    public void deleteDeal(Long id) {
        Deals deal = dealsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deal not found with id: " + id));
        dealsRepository.delete(deal);
    }

    public List<DealsResponseDTO> searchDeals(String query) {
        return dealsRepository.findByDealnameContainingOrAccountNameContainingOrContactNameContaining(query, query, query)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<DealsResponseDTO> bulkUpdateDeals(Map<Long, DealsRequestDTO> dealsUpdates) {
        List<Deals> deals = dealsUpdates.entrySet().stream()
                .map(entry -> {
                    Long id = entry.getKey();
                    DealsRequestDTO request = entry.getValue();
                    Deals deal = dealsRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Deal not found: " + id));
                    updateEntity(deal, request);
                    return deal;
                })
                .collect(Collectors.toList());

        dealsRepository.saveAll(deals);
        return deals.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void bulkDeleteDeals(List<Long> dealIds) {
        dealsRepository.deleteAllById(dealIds);
    }

    private Deals mapToEntity(DealsRequestDTO dealsRequest) {
        Deals deal = new Deals();
        deal.setDealowner(dealsRequest.getDealowner());
        deal.setDealname(dealsRequest.getDealname());
        deal.setAccountName(dealsRequest.getAccountName());
        deal.setType(dealsRequest.getType());
        deal.setNextStep(dealsRequest.getNextStep());
        deal.setLeadSource(dealsRequest.getLeadSource());
        deal.setContactName(dealsRequest.getContactName());
        deal.setAmount(dealsRequest.getAmount());
        deal.setClosingDate(dealsRequest.getClosingDate());
        deal.setStage(dealsRequest.getStage());
        deal.setProbability(dealsRequest.getProbability());
        deal.setExpectedRevenue(dealsRequest.getExpectedRevenue());
        deal.setCampaignSource(dealsRequest.getCampaignSource());
        return deal;
    }

    private void updateEntity(Deals deal, DealsRequestDTO dealsRequest) {
        deal.setDealowner(dealsRequest.getDealowner());
        deal.setDealname(dealsRequest.getDealname());
        deal.setAccountName(dealsRequest.getAccountName());
        deal.setType(dealsRequest.getType());
        deal.setNextStep(dealsRequest.getNextStep());
        deal.setLeadSource(dealsRequest.getLeadSource());
        deal.setContactName(dealsRequest.getContactName());
        deal.setAmount(dealsRequest.getAmount());
        deal.setClosingDate(dealsRequest.getClosingDate());
        deal.setStage(dealsRequest.getStage());
        deal.setProbability(dealsRequest.getProbability());
        deal.setExpectedRevenue(dealsRequest.getExpectedRevenue());
        deal.setCampaignSource(dealsRequest.getCampaignSource());
    }

    private DealsResponseDTO mapToDTO(Deals deal) {
        DealsResponseDTO responseDTO = new DealsResponseDTO();
        responseDTO.setId(deal.getId());
        responseDTO.setDealowner(deal.getDealowner());
        responseDTO.setDealname(deal.getDealname());
        responseDTO.setAccountName(deal.getAccountName());
        responseDTO.setType(deal.getType());
        responseDTO.setNextStep(deal.getNextStep());
        responseDTO.setLeadSource(deal.getLeadSource());
        responseDTO.setContactName(deal.getContactName());
        responseDTO.setAmount(deal.getAmount());
        responseDTO.setClosingDate(deal.getClosingDate());
        responseDTO.setStage(deal.getStage());
        responseDTO.setProbability(deal.getProbability());
        responseDTO.setExpectedRevenue(deal.getExpectedRevenue());
        responseDTO.setCampaignSource(deal.getCampaignSource());
        return responseDTO;
    }

    private void validateDealRequest(DealsRequestDTO dealsRequest) {
        if (!StringUtils.hasText(dealsRequest.getDealowner())) {
            throw new IllegalArgumentException("Deal owner cannot be empty");
        }
        if (!StringUtils.hasText(dealsRequest.getDealname())) {
            throw new IllegalArgumentException("Deal name cannot be empty");
        }
        if (!StringUtils.hasText(dealsRequest.getAccountName())) {
            throw new IllegalArgumentException("Account name cannot be empty");
        }
    }
}