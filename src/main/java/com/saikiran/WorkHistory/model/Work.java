package com.saikiran.WorkHistory.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false)
    private String machine;
    @Column(nullable = false)
    private String workType;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal acres;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Double paid;
    private Double due;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
}
