package com.travelagency.travelagency.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Long> {
    Boolean existsByNameIgnoreCase(String name);
    List<Location> findAllByIsActiveOrderByIdDesc(Boolean isActive, Pageable pageable);
    List<Location> findAllByIsActive(Boolean isActive);
}
