package com.iapps.epapers.persistence.repository;

import com.iapps.epapers.persistence.domain.EpaperRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpaperRequestRepository extends JpaRepository<EpaperRequest, Long> {
}
