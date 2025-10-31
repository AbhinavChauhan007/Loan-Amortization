package com.springboot.LoanAmortization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.LoanAmortization.dto.LoanScheduleRequest;
import com.springboot.LoanAmortization.helper.AmortizationHelper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AmortizationSchedule {

    @Autowired
    AmortizationHelper helper;

    @PostMapping("/getLoanSchedule")
    public ResponseEntity LoanSchedule(@RequestBody(required = false) LoanScheduleRequest params) {
        return helper.getLoanSchedule(params);

    }

    @PostMapping("/getEMI")
    public Double getEMI(@RequestBody(required = false) LoanScheduleRequest params) {
        return helper.roundingEMI(helper.periodicPayment(params.getPrincipal(), params.getRoi(), params.getTerm(), params.getTermUnit(), params.getPaymentFrequency(), params.getStartDate()), params.getRoundingDirection(), params.getRoundTo());
    }
}
