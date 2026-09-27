package com.saikiran.WorkHistory.repository;

import com.saikiran.WorkHistory.model.Owner;
import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    Owner findByUserUsername(String name);
}
