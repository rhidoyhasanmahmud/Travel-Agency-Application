package com.travelagency.service;

import com.travelagency.model.Location;
import com.travelagency.model.LocationRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class LocationService {
    private final LocationRepo repo;

    public Location create(Location ob, HttpServletResponse rs) {
        return repo.save(ob);
    }

    public List<Location> getAll(Boolean isActive, PageRequest pageable) {
        return repo.findAllByIsActiveOrderByIdDesc(isActive, pageable).stream().peek(ob -> {
        }).collect(Collectors.toList());
    }

    public Location getById(Long id) {
        return repo.findById(id).get();
    }

    public Location update(Location ob, HttpServletResponse rs) {
        return repo.save(ob);
    }

    public List<Map<String, Object>> getAllActiveDropdown() {
        List<Map<String, Object>> list = new ArrayList<>();
        repo.findAllByIsActive(true).forEach(it->{
            Map<String, Object> map = new HashMap<>();
            map.put("name", it.getName());
            map.put("id", it.getId());
            list.add(map);
        });
        return list;
    }

    public Boolean existsByName(String name) {
        return repo.existsByNameIgnoreCase(name);
    }
}
