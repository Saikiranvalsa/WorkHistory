package com.saikiran.WorkHistory.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OwnerHistoryDto {
    private String customerName;
    private String customerNumber;
    private String machine;
    private String workType;
    private Double amount;
    private Double paid;
    private Double due;
    private LocalDate date;
}
