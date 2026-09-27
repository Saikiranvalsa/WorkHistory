package com.saikiran.WorkHistory.controller;

import com.saikiran.WorkHistory.dto.WorkDto;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class WorkController {
    @Autowired
    private WorkService workService;
    @PostMapping("/work")
    public Work addWorkDetails(@RequestBody WorkDto workDto, Principal principal){
        return workService.addWorkDetails(workDto,principal);
    }

}
