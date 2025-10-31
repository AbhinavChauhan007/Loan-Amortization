package com.springboot.LoanAmortization.dto;

import java.util.Date;
import java.util.HashMap;

public class LoanScheduleRequest {
	protected double principal;
	protected double roi;
	protected int term;
	protected PaymentUnit termUnit;
	protected PaymentUnit paymentFrequency;
	protected Date startDate;
	protected HashMap<Integer, Double> prepayment;
	protected HashMap<Integer, Double> floatingInterest;
	protected HashMap<String, Double> preClosure;
	protected RoundingDirection roundingDirection;
	protected double roundTo;
	
	
	
	public LoanScheduleRequest() {
		super();
		this.principal = 0;
		this.roi = 0;
		this.term = 0;
		this.termUnit = null;
		this.paymentFrequency = null;
		this.startDate = null;
		this.prepayment = null;
		this.floatingInterest = null;
		this.preClosure = null;
	}

	public LoanScheduleRequest(double principal, double roi, int term, PaymentUnit termUnit, PaymentUnit paymentFrequency, Date startDate,
			HashMap<Integer, Double> prepayment, HashMap<Integer, Double> floatingInterest,
			HashMap<String, Double> preClosure) {
		super();
		this.principal = principal;
		this.roi = roi;
		this.term = term;
		this.termUnit = termUnit;
		this.paymentFrequency = paymentFrequency;
		this.startDate = startDate;
		this.prepayment = prepayment;
		this.floatingInterest = floatingInterest;
		this.preClosure = preClosure;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getRoi() {
		return roi;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public PaymentUnit getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(PaymentUnit termUnit) {
		this.termUnit = termUnit;
	}

	public PaymentUnit getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(PaymentUnit paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public HashMap<Integer, Double> getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(HashMap<Integer, Double> prepayment) {
		this.prepayment = prepayment;
	}

	public HashMap<Integer, Double> getFloatingInterest() {
		return floatingInterest;
	}

	public void setFloatingInterest(HashMap<Integer, Double> floatingInterest) {
		this.floatingInterest = floatingInterest;
	}

	public HashMap<String, Double> getPreClosure() {
		return preClosure;
	}

	public void setPreClosure(HashMap<String, Double> preClosure) {
		this.preClosure = preClosure;
	}

	public double getRoundTo() {
		return roundTo;
	}

	public void setRoundTo(double roundTo) {
		this.roundTo = roundTo;
	}

	public RoundingDirection getRoundingDirection() {
		return roundingDirection;
	}

	public void setRoundingDirection(RoundingDirection roundingDirection) {
		this.roundingDirection = roundingDirection;
	}
	
	

	
	
	
}
