package com.saikiran.WorkHistory.service;

import com.saikiran.WorkHistory.dto.WorkDto;

import com.saikiran.WorkHistory.exception.UserAlreadyFound;
import com.saikiran.WorkHistory.model.Owner;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.repository.OwnerRepository;
import com.saikiran.WorkHistory.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class WorkService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    public Work addWorkDetails(WorkDto workDto, Principal principal) {
        Owner owner= null;
        try {
            owner = ownerRepository.findByUserUsername(principal.getName());
        } catch (Exception e) {
            throw new UserAlreadyFound("failed");
        }
        Work work=new Work();
        work.setWorkType(workDto.getWorkType());
        work.setDate(workDto.getDate());
        work.setAmount(workDto.getAmount());
        work.setCustomerName(workDto.getCustomerName());
        work.setCustomerNumber(workDto.getCustomerNumber());
        work.setDue(workDto.getAmount()-workDto.getPaid());
        work.setMachine(workDto.getMachine());
        work.setPaid(workDto.getPaid());
        work.setOwner(owner);
        workRepository.save(work);
        return work;
    }


}
