package com.travelagency.service;

import com.travelagency.model.LocationRepo;
import com.travelagency.model.Status;
import com.travelagency.model.StatusRepo;
import com.travelagency.model.UserRepo;
import com.travelagency.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    private final JwtProvider tokenProvider;

    public Status create(Status ob, HttpServletResponse rs) {
        ob.setLocation(locationRepo.findById(ob.getLocationId()).get());
        ob.setUser(userRepo.findById(ob.getUserId()).get());
        return repo.save(ob);
    }

    public List<Status> getAll(PageRequest pageable, HttpServletRequest rq) {
        Claims claims = tokenProvider.extractAllClaims(rq);
        Long loggedUserId = Long.valueOf(claims.get("id").toString());
        List<Status> allStatus = new ArrayList<>();

        List<Status> loggedUserStatus = repo.findAllByUser_IdOrderByIdDesc(loggedUserId, pageable).stream().peek(ob -> {
            setMappingObjectVal(ob);
        }).collect(Collectors.toList());
        List<Status> allPublicStatus = repo.findByHavePrivacyAndUser_IdNot(true, loggedUserId).stream().peek(statusOb -> {
            setMappingObjectVal(statusOb);
        }).collect(Collectors.toList());

        allStatus.addAll(allPublicStatus);
        allStatus.addAll(loggedUserStatus);

        return allStatus;
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
