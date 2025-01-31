package com.crm.CRM.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "deals")
public class Deals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "DealOwner Name is required")
    private String dealowner;

    @NotBlank(message = "Deal Name is required")
    private String dealname;

    @NotBlank(message = "AccountName is required")
    private String accountName;

    @NotBlank(message = "Type is required")
    private String type;

    private String nextStep;

    @NotBlank(message = "Lead Source is required")
    private String leadSource;

    @NotBlank(message = "Contact Name is required")
    private String contactName;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Closing Date is required")
    private LocalDate closingDate;

    @NotBlank(message = "Stage is required")
    private String stage;

    private Float probability; // Changed from float to Float

    private Double expectedRevenue; // Changed from String to Double

    private String campaignSource;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDealowner() {
        return dealowner;
    }
    public void setDealowner(String dealowner) {
        this.dealowner = dealowner;
    }
    public String getDealname() {
        return dealname;
    }
    public void setDealname(String dealname) {
        this.dealname = dealname;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNextStep() {
        return nextStep;
    }
    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }
    public String getLeadSource() {
        return leadSource;
    }
    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDate getClosingDate() {
        return closingDate;
    }
    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public Float getProbability() {
        return probability;
    }
    public void setProbability(Float probability) {
        this.probability = probability;
    }
    public Double getExpectedRevenue() {
        return expectedRevenue;
    }
    public void setExpectedRevenue(Double expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }
    public String getCampaignSource() {
        return campaignSource;
    }
    public void setCampaignSource(String campaignSource) {
        this.campaignSource = campaignSource;
    }
}
