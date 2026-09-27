package com.saikiran.WorkHistory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkDto {
    private String machine;
    private String workType;
    private LocalDate date;
    private Double amount;
    private Double paid;
    private String customerName;
    private String customerNumber;
}
