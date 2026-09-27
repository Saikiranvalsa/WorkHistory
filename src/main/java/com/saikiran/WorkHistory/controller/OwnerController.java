package com.saikiran.WorkHistory.controller;

import com.saikiran.WorkHistory.dto.OwnerHistoryDto;
import com.saikiran.WorkHistory.model.Owner;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.repository.OwnerRepository;
import com.saikiran.WorkHistory.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OwnerRepository ownerRepository;
    @GetMapping("/owner/works")
    public List<OwnerHistoryDto> getWorkHistory(Principal principal){
        return ownerService.getWorkHistory(principal);
    }
    @GetMapping("/owner/works/{customerNumber}")
    public List<Work> getWorksofCustomer(@PathVariable String customerNumber,Principal principal){
        return ownerService.getWorksOfCustomer(customerNumber,principal);
    }
}
