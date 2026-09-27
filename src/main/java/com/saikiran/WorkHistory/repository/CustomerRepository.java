package com.saikiran.WorkHistory.repository;

import com.saikiran.WorkHistory.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUserUsername(String name);
}
