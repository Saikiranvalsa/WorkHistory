package com.saikiran.WorkHistory.service;

import com.saikiran.WorkHistory.controller.MyCustomers;
import com.saikiran.WorkHistory.dto.OwnerHistoryDto;
import com.saikiran.WorkHistory.model.Owner;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    public List<OwnerHistoryDto> getWorksOfCustomer(String customerNumber, Principal principal) {
        Owner owner=ownerRepository.findByUserUsername(principal.getName());
        List<Work> works=owner.getWorks();
        return works.stream().filter(work->work.getCustomerNumber().equals(customerNumber)).toList().stream().map(work -> {
            OwnerHistoryDto ownerHistoryDto=new OwnerHistoryDto();
            ownerHistoryDto.setCustomerNumber(work.getCustomerNumber());
            ownerHistoryDto.setDate(work.getDate());
            ownerHistoryDto.setMachine(work.getMachine());
            ownerHistoryDto.setAcres(work.getAcres());
            ownerHistoryDto.setPaid(work.getPaid());
            ownerHistoryDto.setWorkType(work.getWorkType());
            ownerHistoryDto.setAmount(work.getAmount());
            ownerHistoryDto.setCustomerName(work.getCustomerName());
            ownerHistoryDto.setDue(work.getDue());
            return ownerHistoryDto;
        }).toList();
    }

    public List<OwnerHistoryDto> getWorkHistory(Principal principal) {
        Owner owner=ownerRepository.findByUserUsername(principal.getName());
        List<Work> works=owner.getWorks();
        return works.stream().map(work -> {
            OwnerHistoryDto ownerHistoryDto=new OwnerHistoryDto();
            ownerHistoryDto.setMachine(work.getMachine());
            ownerHistoryDto.setDue(work.getDue());
            ownerHistoryDto.setDate(work.getDate());
            ownerHistoryDto.setAcres(work.getAcres());
            ownerHistoryDto.setPaid(work.getPaid());
            ownerHistoryDto.setAmount(work.getAmount());
            ownerHistoryDto.setWorkType(work.getWorkType());
            ownerHistoryDto.setCustomerName(work.getCustomerName());
            ownerHistoryDto.setCustomerNumber(work.getCustomerNumber());
            return ownerHistoryDto;
        }).toList();
    }

    public List<MyCustomers> getMyCustomers(Principal principal) {
        Owner owner=ownerRepository.findByUserUsername(principal.getName());
        List<Work> works=owner.getWorks();
        return works.stream()
                .collect(Collectors.toMap(
                        Work::getCustomerNumber,
                        work -> {
                            MyCustomers myCustomers = new MyCustomers();
                            myCustomers.setName(work.getCustomerName());
                            myCustomers.setNumber(work.getCustomerNumber());
                            return myCustomers;
                        },
                        (existing, duplicate) -> existing
                ))
                .values()
                .stream()
                .toList();
    }
}
