package com.travelagency.controller;

import com.travelagency.model.Location;
import com.travelagency.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/*************************************************************************
 * {@link LocationController} Controller
 *
 * @author Hasan Mahmud
 * @since 2021-07-06
 *************************************************************************/
@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {
    private final LocationService service;

    /*************************************************************************
     * Create a new  Location
     * @param ob {@link  Location} object
     * @param rs {@link HttpServletResponse} object
     * @return {@link  Location}
     *************************************************************************/
    @PostMapping
    public Location create(@Valid @RequestBody Location ob, HttpServletRequest rq, HttpServletResponse rs) {
        rs.setStatus(HttpServletResponse.SC_CREATED);
        return service.create(ob, rs);
    }

    /*************************************************************************
     * Get all active {@link  Location}
     * @param pageSize
     * @param page
     * @return {@link List < Location>}
     *************************************************************************/
    @GetMapping("/getAll/active")
    public List<Location> getAllActive(@RequestParam int page, @RequestParam int pageSize) {
        return service.getAll(true, PageRequest.of(page, pageSize));
    }

    /*************************************************************************
     * Get all inactive {@link  Location}
     * @param pageSize
     * @param page
     * @return {@link List<Location>}
     *************************************************************************/
    @GetMapping("/getAll/inactive")
    public List<Location> getAllInactive(@RequestParam int page, @RequestParam int pageSize) {
        return service.getAll(false, PageRequest.of(page, pageSize));
    }

    /*************************************************************************
     * Get active {@link  Location}
     * @param id Id of a {@link  Location}
     * @return {@link  Location}
     *************************************************************************/
    @GetMapping("/get/{id}")
    public Location getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /*************************************************************************
     * Update {@link  Location}
     * @param ob {@link  Location} object
     * @return {@link  Location}
     *************************************************************************/
    @PutMapping
    public Location update(@Valid @RequestBody Location ob, HttpServletRequest rq, HttpServletResponse rs) {
        return service.update(ob, rs);
    }

    /*************************************************************************
     * Get All active dropdown data
     * @return
     *************************************************************************/
    @GetMapping("getAll/active/dropdown")
    public List<Map<String, Object>> getAllActiveDropdown() {
        return service.getAllActiveDropdown();
    }

    /*************************************************************************
     * Check if academicQualification name already exist
     *
     * @return
     *************************************************************************/
    @GetMapping("/exists/byName")
    public Boolean existsByName(@RequestParam String name) {
        return service.existsByName(name);
    }
}
