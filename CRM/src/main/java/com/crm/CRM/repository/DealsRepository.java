package com.crm.CRM.repository;

import com.crm.CRM.entity.Deals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface DealsRepository extends JpaRepository<Deals, Long> {

    List<Deals> findByDealnameContainingOrAccountNameContainingOrContactNameContaining(String dealName, String dealType, String dealStatus);

//    Arrays findByLeadSource(String leadSource);
}
