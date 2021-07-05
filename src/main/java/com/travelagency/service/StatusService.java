package com.travelagency.service;

import com.travelagency.model.LocationRepo;
import com.travelagency.model.Status;
import com.travelagency.model.StatusRepo;
import com.travelagency.model.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class StatusService {
    private final LocationRepo locationRepo;
    private final UserRepo userRepo;
    private final StatusRepo repo;

    public Status create(Status ob, HttpServletResponse rs) {
        ob.setLocation(locationRepo.findById(ob.getLocationId()).get());
        ob.setUser(userRepo.findById(ob.getUserId()).get());
        return repo.save(ob);
    }

    public List<Status> getAll(Long userId, PageRequest pageable) {
        return repo.findAllByUser_IdOrderByIdDesc(userId, pageable).stream().peek(ob -> {
            setMappingObjectVal(ob);
        }).collect(Collectors.toList());
    }

    public Status getById(Long id) {
        Status ob = repo.findById(id).get();
        return setMappingObjectVal(ob);
    }
    private Status setMappingObjectVal(Status ob) {
        ob.setLocationId(ob.getLocation().getId());
        ob.setLocationName(ob.getLocation().getName());
        ob.setUserId(ob.getUser().getId());
        ob.setUserName(ob.getUser().getName());
        return ob;
    }

    public Status update(Status ob, HttpServletResponse rs) {
        ob.setLocation(locationRepo.findById(ob.getLocationId()).get());
        ob.setUser(userRepo.findById(ob.getUserId()).get());
        return repo.save(ob);
    }
}
