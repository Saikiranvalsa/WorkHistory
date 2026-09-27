package com.saikiran.WorkHistory.repository;

import com.saikiran.WorkHistory.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work,Integer> {
    List<Work> findByCustomerNumber(String name);
}
