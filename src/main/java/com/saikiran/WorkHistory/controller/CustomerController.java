package com.saikiran.WorkHistory.controller;

import com.saikiran.WorkHistory.dto.CustomerHistoryDto;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/customer/works")
    public List<CustomerHistoryDto> getWorks(Principal principal){
        return customerService.getWorks(principal);
    }
    @GetMapping("/customer/works/{ownernumber}")
    public List<CustomerHistoryDto> getWorksofOwner(@PathVariable String ownernumber, Principal principal){
        return customerService.getWorksofOwner(ownernumber,principal);
    }
}
