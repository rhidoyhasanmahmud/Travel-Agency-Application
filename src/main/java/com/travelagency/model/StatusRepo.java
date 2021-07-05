package com.travelagency.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepo extends JpaRepository<Status, Long> {
    List<Status> findAllByUser_IdOrderByIdDesc(Long userId, Pageable pageable);
}
