package com.springboot.LoanAmortization.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.springboot.LoanAmortization.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AmortizationHelper {

    public ResponseEntity getLoanSchedule(LoanScheduleRequest params) {
        try {
            List<LoanSchedule> scheduleList = getAmortizationSchedule(params);
            List<RateOfInterest> rates = new ArrayList<>();
            double netPayment;
            double netInterestPaid = 0;
            double netPrepayment = 0;
            int startTerm = 1;
            double roi = params.getRoi();
            Optional<LoanSchedule> lastRecord = scheduleList.stream().filter(loanSchedule -> loanSchedule.getPreClosureCharge() > 0).findFirst();
            double preClosureCharge = lastRecord.map(LoanSchedule::getPreClosureCharge).orElse(0.0);
            for (int i = 0; i < scheduleList.size(); i++) {
                netPrepayment += scheduleList.get(i).getPrepaymentAmount();
                netInterestPaid += scheduleList.get(i).getInterestPaid();
                if (roi != scheduleList.get(i).getRateOfInterest()) {
                    rates.add(new RateOfInterest(startTerm, i, roi));
                    startTerm = i + 1;
                    roi = scheduleList.get(i).getRateOfInterest();
                }
            }
            if (startTerm <= scheduleList.size()) {
                rates.add(new RateOfInterest(startTerm, scheduleList.size(), roi));
            }
            netPayment = params.getPrincipal() + netInterestPaid + preClosureCharge;
            LoanScheduleResponse response = new LoanScheduleResponse(params.getPrincipal(), scheduleList.size(), netPayment,
                    netInterestPaid, netPrepayment, preClosureCharge, scheduleList, rates);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while calculating loan schedule. Error Detail -> " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<LoanSchedule> getAmortizationSchedule(LoanScheduleRequest params) {
        int numPayments = params.getPaymentFrequency().equals(PaymentUnit.SEMI_MONTHLY)
                && (params.getTermUnit().equals(PaymentUnit.DAILY) || params.getTermUnit().equals(PaymentUnit.WEEKLY) ||
                params.getTermUnit().equals(PaymentUnit.BI_WEEKLY) || params.getTermUnit().equals(PaymentUnit.QUARDRA_WEEKLY))
                ? PaymentFrequencyHelper.getFrequencyforSemiMonthly(params.getTerm(), params.getTermUnit(),
                params.getPaymentFrequency(), params.getStartDate(), 10, 20)
                : PaymentFrequencyHelper.getFrequency(params.getTerm(), params.getTermUnit(),
                params.getPaymentFrequency(), params.getStartDate());
        List<LoanSchedule> loanScheduleList = new ArrayList<>();
        int month;
        boolean isPrepayment = params.getPrepayment() != null && !params.getPrepayment().isEmpty();
        boolean isFloatingROI = params.getFloatingInterest() != null && !params.getFloatingInterest().isEmpty();
        double principal = params.getPrincipal();
        double preClosureCharge = 0.0;
        double prepaymentAmount;
        double remainingBalance = principal;
        double rateOfInterest = params.getRoi();
        for (month = 1; month <= numPayments; month++) {
            LoanSchedule newSchedule;
            if (isFloatingROI)
                rateOfInterest = params.getFloatingInterest().getOrDefault(month, rateOfInterest);

            if (isPrepayment)
                prepaymentAmount = params.getPrepayment().getOrDefault(month, 0.0);
            else
                prepaymentAmount = 0;

            if (remainingBalance > 0) {
                double paymentInterestRate = getPaymentInterestRate(rateOfInterest, params.getTermUnit(), params.getPaymentFrequency(), params.getStartDate());
                double EMI = periodicPayment(params.getPrincipal(), paymentInterestRate, params.getTerm(),
                        params.getTermUnit(), params.getPaymentFrequency(), params.getStartDate());
                double interestPaid = principal * paymentInterestRate;
                double principalPaid = 0.0;
                double roundedEMI = roundingEMI(EMI, params.getRoundingDirection(), params.getRoundTo());

                if (remainingBalance + interestPaid < EMI) {
                    principalPaid += remainingBalance;
                    roundedEMI = principalPaid + interestPaid;
                    remainingBalance = 0;
                } else {
                    prepaymentAmount += roundedEMI - EMI;
                    principalPaid = roundedEMI - interestPaid + prepaymentAmount;
                    remainingBalance = principal - principalPaid;
                }
                if (!isPrepayment && prepaymentAmount > 0.0)
                    isPrepayment = true;

                if (isPrepayment) {
                    if (remainingBalance <= 0.0 && month < numPayments && params.getPreClosure() != null) {
                        preClosureCharge = params.getPreClosure().getOrDefault("preClosureCharge", 0.0)
                                + (params.getPrincipal()
                                * params.getPreClosure().getOrDefault("preClosurePercent", 0.0) / 100);
                    }
                }
                newSchedule = new LoanSchedule(month, principal, rateOfInterest, roundedEMI, interestPaid, principalPaid,
                        remainingBalance, prepaymentAmount, preClosureCharge);
                principal = remainingBalance + preClosureCharge;
            } else {
                newSchedule = new LoanSchedule(month, 0, 0, 0, 0, 0, 0, 0, 0);
            }
            loanScheduleList.add(newSchedule);

        }
        return loanScheduleList;

    }

    public double periodicPayment(double LoanAmount, double paymentInterestRate, int term, PaymentUnit termUnit,
                                  PaymentUnit paymentFrequency, Date startDate) {
        int freq = paymentFrequency.equals(PaymentUnit.SEMI_MONTHLY)
                && (termUnit.equals(PaymentUnit.DAILY) || termUnit.equals(PaymentUnit.WEEKLY) ||
                termUnit.equals(PaymentUnit.BI_WEEKLY) || termUnit.equals(PaymentUnit.QUARDRA_WEEKLY))
                ? PaymentFrequencyHelper.getFrequencyforSemiMonthly(term, termUnit, paymentFrequency, startDate,
                10, 20)
                : PaymentFrequencyHelper.getFrequency(term, termUnit, paymentFrequency, startDate);

        return LoanAmount * paymentInterestRate / (1 - 1 / Math.pow(1 + paymentInterestRate, freq));
    }

    private double getPaymentInterestRate(double rateOfInterest, PaymentUnit termUnit, PaymentUnit paymentFrequency,
                                          Date startDate) {
        double paymentInterestRate = rateOfInterest / PaymentFrequencyHelper.getpaymentInterestRate(
                termUnit, paymentFrequency, startDate);
        paymentInterestRate /= 100;
        return paymentInterestRate;
    }

    public double roundingEMI(double emiAmount, RoundingDirection roundingDirection, double roundDirectionToNext) {
        if (roundingDirection == null || roundDirectionToNext <= 0)
            return emiAmount;
        double delta;
        switch (roundingDirection) {
            case ROUND_UP:
                delta = emiAmount % roundDirectionToNext;
                emiAmount += (roundDirectionToNext - delta);
                break;

            case ROUND_DOWN:
                delta = emiAmount % roundDirectionToNext;
                emiAmount -= delta;
                break;

            case TRUNCATE:
                emiAmount = Math.floor(emiAmount);
                break;
        }
        return emiAmount;
    }

}
