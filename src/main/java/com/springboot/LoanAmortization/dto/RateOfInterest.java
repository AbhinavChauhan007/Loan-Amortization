package com.springboot.LoanAmortization.dto;

public class RateOfInterest{
	protected int startTermNo, endTermNo;
	protected double rateOfInterest;
	
	
		
	public RateOfInterest() {
		this.startTermNo=0;
		this.endTermNo=0;
		this.rateOfInterest=0;
	}


	public RateOfInterest(int startTermNo, int endTermNo, double roi) {
		super();
		this.startTermNo = startTermNo;
		this.endTermNo = endTermNo;
		this.rateOfInterest = roi;
	}


	public int getStartTermNo() {
		return startTermNo;
	}


	public void setStartTermNo(int startTermNo) {
		this.startTermNo = startTermNo;
	}


	public int getEndTermNo() {
		return endTermNo;
	}


	public void setEndTermNo(int endTermNo) {
		this.endTermNo = endTermNo;
	}


	public double getRateOfInterest() {
		return rateOfInterest;
	}


	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	
}