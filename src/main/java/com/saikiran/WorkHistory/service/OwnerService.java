package com.saikiran.WorkHistory.service;

import com.saikiran.WorkHistory.dto.OwnerHistoryDto;
import com.saikiran.WorkHistory.model.Owner;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    public List<Work> getWorksOfCustomer(String customerNumber, Principal principal) {
        Owner owner=ownerRepository.findByUserUsername(principal.getName());
        List<Work> works=owner.getWorks();
        return works.stream().filter(work->work.getCustomerNumber().equals(customerNumber)).toList();
    }

    public List<OwnerHistoryDto> getWorkHistory(Principal principal) {
        Owner owner=ownerRepository.findByUserUsername(principal.getName());
        List<Work> works=owner.getWorks();
        return works.stream().map(work -> {
            OwnerHistoryDto ownerHistoryDto=new OwnerHistoryDto();
            ownerHistoryDto.setMachine(work.getMachine());
            ownerHistoryDto.setDue(work.getDue());
            ownerHistoryDto.setDate(work.getDate());
            ownerHistoryDto.setPaid(work.getPaid());
            ownerHistoryDto.setAmount(work.getAmount());
            ownerHistoryDto.setWorkType(work.getWorkType());
            ownerHistoryDto.setCustomerName(work.getCustomerName());
            ownerHistoryDto.setCustomerNumber(work.getCustomerNumber());
            return ownerHistoryDto;
        }).toList();
    }
}
