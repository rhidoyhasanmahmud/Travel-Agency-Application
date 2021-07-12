package com.travelagency.controller;

import com.travelagency.model.Status;
import com.travelagency.service.StatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/*************************************************************************
 * {@link StatusController} Controller
 *
 * @author Hasan Mahmud
 * @since 2021-07-06
 *************************************************************************/
@RestController
@RequestMapping("/api/status")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StatusController {
    private final StatusService service;

    @PostMapping
    public Status create(@Valid @RequestBody Status ob, HttpServletRequest rq, HttpServletResponse rs) {
        rs.setStatus(HttpServletResponse.SC_CREATED);
        return service.create(ob, rs);
    }

    @GetMapping("/getAll")
    public List<Status> getAll(@RequestParam int page, @RequestParam int pageSize, HttpServletRequest rq) {
        rq.getHeader("header");
        return service.getAll(PageRequest.of(page, pageSize), rq);
    }

    @GetMapping("/get/{id}")
    @Cacheable(value = "status", key = "#id")
    // @Cacheable(value = "status", key = "#id", unless = "#result.id<3")
    public Status getById(@PathVariable Long id) {
        log.warn("Access Into Service Layer for Invoked Data From Database");
        return service.getById(id);
    }

    @PutMapping
    @CachePut(value = "status", key = "#ob.id")
    public Status update(@Valid @RequestBody Status ob, HttpServletRequest rq, HttpServletResponse rs) {
        return service.update(ob, rs);
    }

    // @CacheEvict(cacheNames = "status", key = "#id") -> Delete cache data by ID
}
