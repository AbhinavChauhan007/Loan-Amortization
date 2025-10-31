package com.springboot.LoanAmortization.dto;

import java.util.List;

public class LoanScheduleResponse {
	private double principal;
	private int numberOfEMIs;
	private double totalAmountPaid;
	private double totalInterestPaid;
	private double totalPrepayment;
	private double preClosureCharge;
    private List<RateOfInterest> rateOfInterests;
	private List<LoanSchedule> loanSchedule;
	
	
	
	public LoanScheduleResponse() {
		this.principal = 0;
		this.numberOfEMIs = 0;
		this.totalAmountPaid = 0;
		this.totalInterestPaid = 0;
		this.totalPrepayment = 0;
		this.preClosureCharge = 0;
		this.loanSchedule = null;
		this.rateOfInterests = null;
		
	}
	public LoanScheduleResponse(double principal, int numberOfEMIs, double totalPayment, double totalInterestPaid,
			double totalPrepayment, double preClosureCharge, List<LoanSchedule> loanSchedule, List<RateOfInterest> rates) {
		super();
		this.principal = principal;
		this.numberOfEMIs = numberOfEMIs;
		this.totalAmountPaid = totalPayment;
		this.totalInterestPaid = totalInterestPaid;
		this.totalPrepayment = totalPrepayment;
		this.preClosureCharge = preClosureCharge;
		this.loanSchedule = loanSchedule;
		this.rateOfInterests = rates;
		
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public int getNumberOfEMIs() {
		return numberOfEMIs;
	}
	public void setNumberOfEMIs(int numberOfEMIs) {
		this.numberOfEMIs = numberOfEMIs;
	}
	
	public double getTotalAmountPaid() {
		return totalAmountPaid;
	}
	public void setTotalAmountPaid(double totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}
	public double getPreClosureCharge() {
		return preClosureCharge;
	}
	public void setPreClosureCharge(double preClosureCharge) {
		this.preClosureCharge = preClosureCharge;
	}
	public double getTotalInterestPaid() {
		return totalInterestPaid;
	}
	public void setTotalInterestPaid(double totalInterestPaid) {
		this.totalInterestPaid = totalInterestPaid;
	}
	public List<LoanSchedule> getLoanSchedule() {
		return loanSchedule;
	}
	public void setLoanSchedule(List<LoanSchedule> loanSchedule) {
		this.loanSchedule = loanSchedule;
	}
	public double getTotalPrepayment() {
		return totalPrepayment;
	}
	public void setTotalPrepayment(double totalPrepayment) {
		this.totalPrepayment = totalPrepayment;
	}
	public List<RateOfInterest> getRateOfInterests() {
		return rateOfInterests;
	}
	public void setRateOfInterests(List<RateOfInterest> rateOfInterests) {
		this.rateOfInterests = rateOfInterests;
	}
	
	
	
	
	
}