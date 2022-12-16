package com.iapps.epapers.persistence.repository;

import com.iapps.epapers.persistence.domain.EpaperRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EpaperRequestRepository
        extends JpaRepository<EpaperRequest, Long>, QuerydslPredicateExecutor<EpaperRequest> {

}
