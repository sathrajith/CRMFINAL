package com.crm.CRM.repository;

import com.crm.CRM.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    List<Lead> findByNameContainingOrEmailContainingOrPhoneContaining(String name, String email, String phone);
    List<Lead> findByLeadSource(String leadSource);
}