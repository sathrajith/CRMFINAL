package com.crm.CRM.dto;

import java.time.LocalDate;

public class DealsResponseDTO {

    private Long id;
    private String dealowner;
    private String dealname;
    private String accountName;
    private String type;
    private String nextStep;
    private String leadSource;
    private String contactName;
    private Double amount;
    private LocalDate closingDate;
    private String stage;
    private Float probability;
    private Double expectedRevenue;
    private String campaignSource;

    public Long getId() {
        return id;
    }
    public String getDealowner() {
        return dealowner;
    }
    public String getDealname() {
        return dealname;
    }
    public String getAccountName() {
        return accountName;
    }
    public String getType() {
        return type;
    }
    public String getNextStep() {
        return nextStep;
    }
    public String getLeadSource() {
        return leadSource;
    }
    public String getContactName() {
        return contactName;
    }
    public Double getAmount() {
        return amount;
    }
    public LocalDate getClosingDate() {
        return closingDate;
    }
    public String getStage() {
        return stage;
    }
    public Float getProbability() {
        return probability;
    }
    public Double getExpectedRevenue() {
        return expectedRevenue;
    }
    public String getCampaignSource() {
        return campaignSource;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setDealowner(String dealowner) {
        this.dealowner = dealowner;
    }
    public void setDealname(String dealname) {
        this.dealname = dealname;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }
    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public void setProbability(Float probability) {
        this.probability = probability;
    }
    public void setExpectedRevenue(Double expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }
    public void setCampaignSource(String campaignSource) {
        this.campaignSource = campaignSource;
    }


//    public DealsResponseDTO(Long id, String dealowner, String dealname, String accountName, String type,
//                            String nextStep, String leadSource, String contactName, Double amount,
//                            LocalDate closingDate, String stage, Float probability,
//                            Double expectedRevenue, String campaignSource) {
//        this.id = id;
//        this.dealowner = dealowner;
//        this.dealname = dealname;
//        this.accountName = accountName;
//        this.type = type;
//        this.nextStep = nextStep;
//        this.leadSource = leadSource;
//        this.contactName = contactName;
//        this.amount = amount;
//        this.closingDate = closingDate;
//        this.stage = stage;
//        this.probability = probability;
//        this.expectedRevenue = expectedRevenue;
//        this.campaignSource = campaignSource;
//    }

}
