package com.saikiran.WorkHistory.service;

import com.saikiran.WorkHistory.dto.CustomerHistoryDto;
import com.saikiran.WorkHistory.model.Customer;
import com.saikiran.WorkHistory.model.Work;
import com.saikiran.WorkHistory.repository.CustomerRepository;
import com.saikiran.WorkHistory.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private CustomerRepository customerRepository;
    public List<CustomerHistoryDto> getWorks(Principal principal) {
        List<Work> works= workRepository.findByCustomerNumber(principal.getName());
        return works.stream().map(work->{
            CustomerHistoryDto customerHistoryDto=new CustomerHistoryDto();
            customerHistoryDto.setMachine(work.getMachine());
            customerHistoryDto.setPaid(work.getPaid());
            customerHistoryDto.setDate(work.getDate());
            customerHistoryDto.setWorkType(work.getWorkType());
            customerHistoryDto.setAmount(work.getAmount());
            customerHistoryDto.setDue(work.getDue());
            customerHistoryDto.setOwnerName(work.getOwner().getUser().getName());
            customerHistoryDto.setOwnerNumber(work.getOwner().getUser().getNumber());
            return customerHistoryDto;
        }).toList();
    }

    public List<Work> getWorksofOwner(String ownernumber, Principal principal) {
        Customer customer=customerRepository.findByUserUsername(principal.getName());
        List<Work> works=workRepository.findByCustomerNumber(customer.getUser().getNumber());
        return works.stream().filter(work->work.getOwner().getUser().getNumber().equals(ownernumber)).toList();
    }
}
