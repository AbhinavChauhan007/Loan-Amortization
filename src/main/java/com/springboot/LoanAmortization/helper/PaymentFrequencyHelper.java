package com.springboot.LoanAmortization.helper;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.springboot.LoanAmortization.dto.PaymentUnit;

@Component
public class PaymentFrequencyHelper {

	public static Date addDays(Date date, int numberOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
		return cal.getTime();
	}

	public static Date addWeeks(Date date, int numberOfWeeks) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_MONTH, numberOfWeeks);
		return cal.getTime();
	}

	public static Date addMonths(Date date, int numberOfMonths) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, numberOfMonths);
		return cal.getTime();
	}

	public static Date addYears(Date date, int numberOfYears) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, numberOfYears);
		return cal.getTime();
	}

	public static int diffDays(Date startDate, Date endDate) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		return (int) ChronoUnit.DAYS.between(cal1.toInstant(), cal2.toInstant());
	}

	static int getFrequency(int term, PaymentUnit termUnit, PaymentUnit paymentFrequency, Date startDate) {
		int frequency = 0;
		Date endDate;
		switch (termUnit) {
		case DAILY:
			switch (paymentFrequency) {
			case DAILY:
				frequency = term;
				break;

			case WEEKLY:
				frequency = (int) Math.ceil((double) term / 7);
				break;

			case BI_WEEKLY:
				frequency = (int) Math.ceil((double) term / 14);
				break;

			case QUARDRA_WEEKLY:
				frequency = (int) Math.ceil((double) term / 28);
				break;

			case MONTHLY:
				endDate = addDays(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 1);
					frequency += 1;
				}
				break;

			case THIRTY_DAY_MONTH:
				endDate = addDays(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				endDate = addDays(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 2);
					frequency += 1;
				}
				break;

			case QUARTERLY:
				endDate = addDays(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 3);
					frequency += 1;
				}
				break;

			case HALF_YEARLY:
				endDate = addDays(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 6);
					frequency += 1;
				}
				break;

			case YEARLY:
				endDate = addDays(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addYears(startDate, 1);
					frequency += 1;
				}
				break;
			}
			break;
		case WEEKLY:
			switch (paymentFrequency) {
			case DAILY:
				frequency = term * 7;
				break;

			case WEEKLY:
				frequency = term;
				break;

			case BI_WEEKLY:
				frequency = (int) Math.ceil((double) term / 2);
				break;

			case QUARDRA_WEEKLY:
				frequency = (int) Math.ceil((double) term / 4);
				break;

			case MONTHLY:
				endDate = addWeeks(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 1);
					frequency += 1;
				}
				break;

			case THIRTY_DAY_MONTH:
				endDate = addWeeks(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				endDate = addWeeks(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 2);
					frequency += 1;
				}
				break;

			case QUARTERLY:
				endDate = addWeeks(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 3);
					frequency += 1;
				}
				break;

			case HALF_YEARLY:
				endDate = addWeeks(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 6);
					frequency += 1;
				}
				break;

			case YEARLY:
				endDate = addWeeks(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addYears(startDate, 1);
					frequency += 1;
				}
				break;
			}
			break;

		case BI_WEEKLY:
			switch (paymentFrequency) {
			case DAILY:
				frequency = term * 14;
				break;

			case WEEKLY:
				frequency = term * 2;
				break;

			case BI_WEEKLY:
				frequency = term;
				break;

			case QUARDRA_WEEKLY:
				frequency = (int) Math.ceil((double) term / 2);
				break;

			case MONTHLY:
				endDate = addWeeks(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 1);
					frequency += 1;
				}
				break;

			case THIRTY_DAY_MONTH:
				endDate = addWeeks(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				endDate = addWeeks(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 2);
					frequency += 1;
				}
				break;

			case QUARTERLY:
				endDate = addWeeks(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 3);
					frequency += 1;
				}
				break;

			case HALF_YEARLY:
				endDate = addWeeks(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 6);
					frequency += 1;
				}
				break;

			case YEARLY:
				endDate = addWeeks(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addYears(startDate, 1);
					frequency += 1;
				}
				break;
			}
			break;

		case QUARDRA_WEEKLY:
			switch (paymentFrequency) {
			case DAILY:
				frequency = term * 28;
				break;

			case WEEKLY:
				frequency = term * 4;
				break;

			case BI_WEEKLY:
				frequency = term * 2;
				break;

			case QUARDRA_WEEKLY:
				frequency = term;
				break;

			case MONTHLY:
				endDate = addWeeks(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 1);
					frequency += 1;
				}
				break;

			case THIRTY_DAY_MONTH:
				endDate = addWeeks(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				endDate = addWeeks(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 2);
					frequency += 1;
				}
				break;

			case QUARTERLY:
				endDate = addWeeks(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 3);
					frequency += 1;
				}
				break;

			case HALF_YEARLY:
				endDate = addWeeks(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 6);
					frequency += 1;
				}
				break;

			case YEARLY:
				endDate = addWeeks(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addYears(startDate, 1);
					frequency += 1;
				}
				break;
			}
			break;

		case MONTHLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, term);
				frequency = diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, term);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 7);
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, term);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 14);
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, term);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 28);
				break;

			case SEMI_MONTHLY:
				frequency = term * 2;
				break;

			case MONTHLY:
				frequency = term;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				frequency = (int) Math.ceil((double) term / 2);
				break;

			case QUARTERLY:
				frequency = (int) Math.ceil((double) term / 4);
				break;

			case HALF_YEARLY:
				frequency = (int) Math.ceil((double) term / 6);
				break;

			case YEARLY:
				frequency = (int) Math.ceil((double) term / 12);
				break;
			}
			break;

		case THIRTY_DAY_MONTH:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addDays(startDate, term * 30);
				frequency = diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addDays(startDate, term * 30);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 7);
				break;

			case BI_WEEKLY:
				endDate = addDays(startDate, term * 30);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 14);
				break;

			case QUARDRA_WEEKLY:
				endDate = addDays(startDate, term * 30);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 28);
				break;

			case SEMI_MONTHLY:
				frequency = term * 2;
				break;

			case MONTHLY:
				endDate = addDays(startDate, term * 30);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 1);
					frequency += 1;
				}
				break;

			case THIRTY_DAY_MONTH:
				frequency = term;

			case BI_MONTHLY:
				endDate = addDays(startDate, term * 30);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 2);
					frequency += 1;
				}
				break;

			case QUARTERLY:
				endDate = addDays(startDate, term * 30);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 4);
					frequency += 1;
				}
				break;

			case HALF_YEARLY:
				endDate = addDays(startDate, term * 30);
				while (startDate.before(endDate)) {
					startDate = addMonths(startDate, 6);
					frequency += 1;
				}
				break;

			case YEARLY:
				endDate = addDays(startDate, term * 30);
				while (startDate.before(endDate)) {
					startDate = addYears(startDate, 1);
					frequency += 1;
				}
				break;
			}
			break;

		case BI_MONTHLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, term * 2);
				frequency = diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, term * 2);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 7);
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, term * 2);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 14);
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, term * 2);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 28);
				break;

			case SEMI_MONTHLY:
				frequency = term * 4;
				break;

			case MONTHLY:
				frequency = term * 2;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, term * 2);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				frequency = term;
				break;

			case QUARTERLY:
				frequency = (int) Math.ceil((double) term * 2 / 3);
				break;

			case HALF_YEARLY:
				frequency = (int) Math.ceil((double) term / 3);
				break;

			case YEARLY:
				frequency = (int) Math.ceil((double) term / 6);
				break;
			}
			break;

		case QUARTERLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, term * 3);
				frequency = diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, term * 3);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 7);
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, term * 3);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 14);
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, term * 3);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 28);
				break;

			case SEMI_MONTHLY:
				frequency = term * 6;
				break;

			case MONTHLY:
				frequency = term * 3;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, term * 4);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				frequency = (int) Math.ceil((double) term * 1.5);
				break;

			case QUARTERLY:
				frequency = term;
				break;

			case HALF_YEARLY:
				frequency = (int) Math.ceil((double) term / 2);
				break;

			case YEARLY:
				frequency = (int) Math.ceil((double) term / 4);
				break;
			}
			break;

		case HALF_YEARLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, term * 6);
				frequency = diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, term * 6);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 7);
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, term * 6);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 14);
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, term * 6);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 28);
				break;

			case SEMI_MONTHLY:
				frequency = term * 12;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, term * 6);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case MONTHLY:
				frequency = term * 6;
				break;

			case BI_MONTHLY:
				frequency = term * 3;
				break;

			case QUARTERLY:
				frequency = term * 2;
				break;

			case HALF_YEARLY:
				frequency = term;
				break;

			case YEARLY:
				frequency = (int) Math.ceil((double) term / 2);
				break;
			}
			break;

		case YEARLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, term * 12);
				frequency = diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, term * 12);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 7);
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, term * 12);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 14);
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, term * 12);
				frequency = (int) Math.ceil((double) diffDays(startDate, endDate) / 28);
				break;

			case SEMI_MONTHLY:
				frequency = term * 24;
				break;

			case MONTHLY:
				frequency = term * 12;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addYears(startDate, term);
				while (startDate.before(endDate)) {
					startDate = addDays(startDate, 30);
					frequency += 1;
				}
				break;

			case BI_MONTHLY:
				frequency = term * 6;
				break;

			case QUARTERLY:
				frequency = term * 4;
				break;

			case HALF_YEARLY:
				frequency = term * 2;
				break;

			case YEARLY:
				frequency = term;
				break;
			}
		}
		return frequency;

	}

	static double getpaymentInterestRate(PaymentUnit termUnit, PaymentUnit paymentFrequency, Date startDate) {
		double InterestRate = 0.0;
		Date endDate;
		switch (termUnit) {
		case DAILY:
			switch (paymentFrequency) {
			case DAILY:
				InterestRate = 1;
				break;

			case WEEKLY:
				InterestRate = (double) 1 / 7;
				break;

			case BI_WEEKLY:
				InterestRate = (double) 1 / 14;
				break;

			case QUARDRA_WEEKLY:
				InterestRate = (double) 1 / 28;
				break;

			case SEMI_MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 2 / diffDays(startDate, endDate);
				break;

			case MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 1 / diffDays(startDate, endDate);
				break;

			case THIRTY_DAY_MONTH:
				InterestRate = (double) 1 / 30;
				break;

			case BI_MONTHLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) 1 / diffDays(startDate, endDate);
				break;

			case QUARTERLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) 1 / diffDays(startDate, endDate);
				break;

			case HALF_YEARLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) 1 / diffDays(startDate, endDate);
				break;

			case YEARLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) 1 / diffDays(startDate, endDate);
				break;
			}
			break;

		case WEEKLY:
			switch (paymentFrequency) {
			case DAILY:
				InterestRate = (double) 7;
				break;

			case WEEKLY:
				InterestRate = (double) 1;
				break;

			case BI_WEEKLY:
				InterestRate = (double) 1 / 2;
				break;

			case QUARDRA_WEEKLY:
				InterestRate = (double) 1 / 4;
				break;

			case SEMI_MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 14 / diffDays(startDate, endDate);
				break;

			case MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 7 / diffDays(startDate, endDate);
				break;

			case THIRTY_DAY_MONTH:
				InterestRate = (double) 7 / 30;
				break;

			case BI_MONTHLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) 7 / diffDays(startDate, endDate);
				break;

			case QUARTERLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) 7 / diffDays(startDate, endDate);
				break;

			case HALF_YEARLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) 7 / diffDays(startDate, endDate);
				break;

			case YEARLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) 7 / diffDays(startDate, endDate);
				break;
			}
			break;

		case BI_WEEKLY:
			switch (paymentFrequency) {
			case DAILY:
				InterestRate = (double) 14;
				break;

			case WEEKLY:
				InterestRate = (double) 2;
				break;

			case BI_WEEKLY:
				InterestRate = (double) 1;
				break;

			case QUARDRA_WEEKLY:
				InterestRate = (double) 1 / 2;
				break;

			case SEMI_MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 28 / diffDays(startDate, endDate);
				break;

			case MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 14 / diffDays(startDate, endDate);
				break;

			case THIRTY_DAY_MONTH:
				InterestRate = (double) 14 / 30;
				break;

			case BI_MONTHLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) 14 / diffDays(startDate, endDate);
				break;

			case QUARTERLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) 14 / diffDays(startDate, endDate);
				break;

			case HALF_YEARLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) 14 / diffDays(startDate, endDate);
				break;

			case YEARLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) 14 / diffDays(startDate, endDate);
				break;
			}
			break;

		case QUARDRA_WEEKLY:
			switch (paymentFrequency) {
			case DAILY:
				InterestRate = (double) 28;
				break;

			case WEEKLY:
				InterestRate = (double) 4;
				break;

			case BI_WEEKLY:
				InterestRate = (double) 2;
				break;

			case QUARDRA_WEEKLY:
				InterestRate = (double) 1;
				break;

			case SEMI_MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 56 / diffDays(startDate, endDate);
				break;

			case MONTHLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) 28 / diffDays(startDate, endDate);
				break;

			case THIRTY_DAY_MONTH:
				InterestRate = (double) 28 / 30;
				break;

			case BI_MONTHLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) 28 / diffDays(startDate, endDate);
				break;

			case QUARTERLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) 28 / diffDays(startDate, endDate);
				break;

			case HALF_YEARLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) 28 / diffDays(startDate, endDate);
				break;

			case YEARLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) 28 / diffDays(startDate, endDate);
				break;
			}
			break;

		case MONTHLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 7;
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 14;
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 28;
				break;

			case SEMI_MONTHLY:
				InterestRate = (double) 2;
				break;

			case MONTHLY:
				InterestRate = (double) 1;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 30;
				break;

			case BI_MONTHLY:
				InterestRate = (double) 1 / 2;
				break;

			case QUARTERLY:
				InterestRate = (double) 1 / 4;
				break;

			case HALF_YEARLY:
				InterestRate = (double) 1 / 6;
				break;

			case YEARLY:
				InterestRate = (double) 1 / 12;
				break;
			}
			break;

		case THIRTY_DAY_MONTH:
			switch (paymentFrequency) {
			case DAILY:
				InterestRate = (double) 1 /30;
				break;

			case WEEKLY:
				InterestRate = (double) 7 / 30;
				break;

			case BI_WEEKLY:
				InterestRate = (double) 14 / 30;
				break;

			case QUARDRA_WEEKLY:
				InterestRate = (double) 28 / 30;
				break;

			case SEMI_MONTHLY:
				InterestRate = 2;
				break;

			case MONTHLY:
				endDate = addDays(startDate, 30);
				InterestRate = (double) 1 / diffDays(startDate, endDate);
				break;

			case THIRTY_DAY_MONTH:
				InterestRate = 1;

			case BI_MONTHLY:
				InterestRate = 0.5;
				break;

			case QUARTERLY:
				InterestRate = (double) 1/3;
				break;

			case HALF_YEARLY:
				InterestRate = (double) 1/6;
				break;

			case YEARLY:
				InterestRate = (double) 1/12;
				break;
			}
			break;

		case BI_MONTHLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) diffDays(startDate, endDate) / 7;
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) diffDays(startDate, endDate) / 14;
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) diffDays(startDate, endDate) / 28;
				break;

			case SEMI_MONTHLY:
				InterestRate = (double) 4;
				break;

			case MONTHLY:
				InterestRate = (double) 2;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, 2);
				InterestRate = (double) diffDays(startDate, endDate) / 30;
				break;

			case BI_MONTHLY:
				InterestRate = (double) 1;
				break;

			case QUARTERLY:
				InterestRate = (double) 2 / 3;
				break;

			case HALF_YEARLY:
				InterestRate = (double) 1 / 3;
				break;

			case YEARLY:
				InterestRate = (double) 1 / 6;
				break;
			}
			break;

		case QUARTERLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) diffDays(startDate, endDate) / 7;
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) diffDays(startDate, endDate) / 14;
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) diffDays(startDate, endDate) / 28;
				break;

			case SEMI_MONTHLY:
				InterestRate = (double) 6;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, 3);
				InterestRate = (double) diffDays(startDate, endDate) / 30;
				break;

			case MONTHLY:
				InterestRate = (double) 3;
				break;

			case BI_MONTHLY:
				InterestRate = (double) 3 / 2;
				break;

			case QUARTERLY:
				InterestRate = (double) 1;
				break;

			case HALF_YEARLY:
				InterestRate = (double) 1 / 2;
				break;

			case YEARLY:
				InterestRate = (double) 1 / 4;
				break;
			}
			break;

		case HALF_YEARLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) diffDays(startDate, endDate) / 7;
				break;

			case BI_WEEKLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) diffDays(startDate, endDate) / 14;
				break;

			case QUARDRA_WEEKLY:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) diffDays(startDate, endDate) / 28;
				break;

			case SEMI_MONTHLY:
				InterestRate = (double) 12;
				break;

			case MONTHLY:
				InterestRate = (double) 6;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addMonths(startDate, 6);
				InterestRate = (double) diffDays(startDate, endDate) / 30;
				break;

			case BI_MONTHLY:
				InterestRate = (double) 3;
				break;

			case QUARTERLY:
				InterestRate = (double) 2;
				break;

			case HALF_YEARLY:
				InterestRate = (double) 1;
				break;

			case YEARLY:
				InterestRate = (double) 1 / 2;
				break;
			}
			break;

		case YEARLY:
			switch (paymentFrequency) {
			case DAILY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate);
				break;

			case WEEKLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 7;
				break;

			case BI_WEEKLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 14;
				break;

			case QUARDRA_WEEKLY:
				endDate = addYears(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 28;
				break;

			case SEMI_MONTHLY:
				InterestRate = (double) 24;
				break;

			case MONTHLY:
				InterestRate = (double) 12;
				break;

			case THIRTY_DAY_MONTH:
				endDate = addYears(startDate, 1);
				InterestRate = (double) diffDays(startDate, endDate) / 30;
				break;

			case BI_MONTHLY:
				InterestRate = (double) 6;
				break;

			case QUARTERLY:
				InterestRate = (double) 3;
				break;

			case HALF_YEARLY:
				InterestRate = (double) 2;
				break;

			case YEARLY:
				InterestRate = (double) 1;
				break;
			}
		}
		return InterestRate;

	}

	public static int getFrequencyforSemiMonthly(int term, PaymentUnit termUnit, PaymentUnit paymentFrequency, Date startDate,
			int firstPaymentDateNumber, int secondPaymentDateNumber) {
		Date endDate;
		Calendar sd, ed;
		int frequency = 0;
		Calendar firstPaymentDate = Calendar.getInstance();
		firstPaymentDate.setTime(startDate);
		firstPaymentDate.set(Calendar.DAY_OF_MONTH, firstPaymentDateNumber);
		Calendar secondPaymentDate = Calendar.getInstance();
		secondPaymentDate.setTime(startDate);
		secondPaymentDate.set(Calendar.DAY_OF_MONTH, secondPaymentDateNumber);
		Calendar movingStartDate = Calendar.getInstance();
		movingStartDate.setTime(startDate);
		switch (termUnit) {
		case DAILY:
			endDate = addDays(startDate, term);
			sd = Calendar.getInstance();
			sd.setTime(startDate);
			ed = Calendar.getInstance();
			ed.setTime(endDate);
			while (((firstPaymentDate.before(ed) || firstPaymentDate.equals(ed)) && firstPaymentDate.after(sd))) {
				frequency += 1;
				firstPaymentDate.add(Calendar.MONTH, 1);
			}

			while ((secondPaymentDate.before(ed) || secondPaymentDate.equals(ed)) && secondPaymentDate.after(sd)) {
				frequency += 1;
				secondPaymentDate.add(Calendar.MONTH, 1);
			}

			if (frequency == 0 && term > 0)
				frequency = 1;
			break;

		case WEEKLY:
			endDate = addWeeks(startDate, term);
			sd = Calendar.getInstance();
			sd.setTime(startDate);
			ed = Calendar.getInstance();
			ed.setTime(endDate);
			while (((firstPaymentDate.before(ed) || firstPaymentDate.equals(ed)) && firstPaymentDate.after(sd))) {
				frequency += 1;
				firstPaymentDate.add(Calendar.MONTH, 1);
			}

			while ((secondPaymentDate.before(ed) || secondPaymentDate.equals(ed)) && secondPaymentDate.after(sd)) {
				frequency += 1;
				secondPaymentDate.add(Calendar.MONTH, 1);
			}

			if (frequency == 0 && term > 0)
				frequency = 1;
			break;

		case BI_WEEKLY:
			endDate = addWeeks(startDate, term * 2);
			sd = Calendar.getInstance();
			sd.setTime(startDate);
			ed = Calendar.getInstance();
			ed.setTime(endDate);
			while (((firstPaymentDate.before(ed) || firstPaymentDate.equals(ed)) && firstPaymentDate.after(sd))) {
				frequency += 1;
				firstPaymentDate.add(Calendar.MONTH, 1);
			}

			while ((secondPaymentDate.before(ed) || secondPaymentDate.equals(ed)) && secondPaymentDate.after(sd)) {
				frequency += 1;
				secondPaymentDate.add(Calendar.MONTH, 1);
			}

			if (frequency == 0 && term > 0)
				frequency = 1;
			break;

		case QUARDRA_WEEKLY:
			endDate = addWeeks(startDate, term * 4);
			sd = Calendar.getInstance();
			sd.setTime(startDate);
			ed = Calendar.getInstance();
			ed.setTime(endDate);
			while (((firstPaymentDate.before(ed) || firstPaymentDate.equals(ed)) && firstPaymentDate.after(sd))) {
				frequency += 1;
				firstPaymentDate.add(Calendar.MONTH, 1);
			}

			while ((secondPaymentDate.before(ed) || secondPaymentDate.equals(ed)) && secondPaymentDate.after(sd)) {
				frequency += 1;
				secondPaymentDate.add(Calendar.MONTH, 1);
			}

			if (frequency == 0 && term > 0)
				frequency = 1;
			break;

		}
		return frequency;

	}

}
