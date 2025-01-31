package com.crm.CRM.dto;

public class LeadResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String company;
    private String leadSource;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLeadSource() {
        return leadSource;
    }
    public void setLeadSource(String LeadSource) {
        this.leadSource = LeadSource;
    }
}
