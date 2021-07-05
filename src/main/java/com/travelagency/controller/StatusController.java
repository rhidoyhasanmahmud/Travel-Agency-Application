package com.travelagency.controller;

import com.travelagency.model.Status;
import com.travelagency.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class StatusController {
    private final StatusService service;

    /*************************************************************************
     * Create a new  Status
     * @param ob {@link  Status} object
     * @param rs {@link HttpServletResponse} object
     * @return {@link  Status}
     *************************************************************************/
    @PostMapping
    public Status create(@Valid @RequestBody Status ob, HttpServletRequest rq, HttpServletResponse rs) {
        rs.setStatus(HttpServletResponse.SC_CREATED);
        return service.create(ob, rs);
    }

    /*************************************************************************
     * Get all active {@link  Status}
     * @param pageSize
     * @param page
     * @return {@link List < Status>}
     *************************************************************************/
    @GetMapping("/getAll")
    public List<Status> getAll(@RequestParam int page, @RequestParam int pageSize, @RequestParam Long userId) {
        return service.getAll(userId, PageRequest.of(page, pageSize));
    }


    /*************************************************************************
     * Get active {@link  Status}
     * @param id Id of a {@link  Status}
     * @return {@link  Status}
     *************************************************************************/
    @GetMapping("/get/{id}")
    public Status getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /*************************************************************************
     * Update {@link  Status}
     * @param ob {@link  Status} object
     * @return {@link  Status}
     *************************************************************************/
    @PutMapping
    public Status update(@Valid @RequestBody Status ob, HttpServletRequest rq, HttpServletResponse rs) {
        return service.update(ob, rs);
    }
}
