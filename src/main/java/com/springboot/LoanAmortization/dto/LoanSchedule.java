package com.springboot.LoanAmortization.dto;


public class LoanSchedule {
	 private int Payment;
	 protected double OpeningBalance;
	 protected double rateOfInterest;
	 protected double MonthlyEMI;
	 protected double InterestPaid;
	 protected double PrincipalPaid;
	 protected double RemainingBalance;
	 protected double prepaymentAmount;
	 protected double preClosureCharge;
	 public LoanSchedule(int payment, double openingBalance, double rateOfInterest, double monthlyEMI, double interestPaid,
			double principalPaid, double remainingBalance, double prepaymentAmount, double preClosureCharge) {
		Payment = payment;
		OpeningBalance = openingBalance;
		this.rateOfInterest = rateOfInterest;
		MonthlyEMI = monthlyEMI;
		InterestPaid = interestPaid;
		PrincipalPaid = principalPaid;
		RemainingBalance = remainingBalance;
		this.prepaymentAmount = prepaymentAmount;
		this.preClosureCharge = preClosureCharge;
	}
	 
	 
	public LoanSchedule() {
		Payment = 0;
		OpeningBalance = 0;
		rateOfInterest = 0;
		MonthlyEMI = 0;
		InterestPaid = 0;
		PrincipalPaid = 0;
		RemainingBalance = 0;
		prepaymentAmount = 0;
		preClosureCharge = 0;
	}


	public int getPayment() {
		return Payment;
	}
	public void setPayment(int payment) {
		Payment = payment;
	}
	public double getOpeningBalance() {
		return OpeningBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		OpeningBalance = openingBalance;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public double getMonthlyEMI() {
		return MonthlyEMI;
	}
	public void setMonthlyEMI(double monthlyEMI) {
		MonthlyEMI = monthlyEMI;
	}
	public double getInterestPaid() {
		return InterestPaid;
	}
	public void setInterestPaid(double interestPaid) {
		InterestPaid = interestPaid;
	}
	public double getPrincipalPaid() {
		return PrincipalPaid;
	}
	public void setPrincipalPaid(double principalPaid) {
		PrincipalPaid = principalPaid;
	}
	public double getRemainingBalance() {
		return RemainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		RemainingBalance = remainingBalance;
	}
	public double getPrepaymentAmount() {
		return prepaymentAmount;
	}
	public void setPrepaymentAmount(double prepaymentAmount) {
		this.prepaymentAmount = prepaymentAmount;
	}
	public double getPreClosureCharge() {
		return preClosureCharge;
	}
	public void setPreClosureCharge(double preClosureCharge) {
		this.preClosureCharge = preClosureCharge;
	}



	
}
	

