package com.crm.CRM.entity;

import com.crm.CRM.dto.LeadRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Invalid phone number")
    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Lead Source is required")
    private String leadSource;

    @NotBlank(message = "Company is required")
    private String status;

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

    public void updateFromDTO(LeadRequestDTO request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.status = request.getStatus();
        this.company = request.getCompany();
        this.leadSource = request.getLeadSource();
    }
}

