import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

interface ScheduleRow {
  termNo: number;
  openingBalance: number;
  monthlyEmi: number;
  principalPaid: number;
  interestPaid: number;
  roi: number;
  prepaymentAmount: number;
  remainingBalance: number;
}

@Component({
  selector: 'loan-table',
  standalone: true,
  imports: [FormsModule, CommonModule, CurrencyPipe, HttpClientModule],
  templateUrl: './loan-table.html',
  styleUrls: ['./loan-table.css']
})
export class LoanTableComponent {

  // Form inputs
  principal: number = 800000;
  roi: number = 8.5;
  term: number = 5;
  termUnit: string = 'YEARLY';
  paymentFrequency: string = 'MONTHLY';
  startDate!: string;
  roundingMode?: string;
  roundTo?: number;

  preClosure: { preClosureCharge?: number, preClosurePercent?: number } = {};
  prepayment: { [key: string]: number } = {};
  floatingInterest: { [key: string]: number } = {};

  termUnits: { label: string; value: string }[] = [
    { label: 'Day', value: 'DAILY' },
    { label: 'Week', value: 'WEEKLY' },
    { label: 'Bi-Week', value: 'BI_WEEKLY' },
    { label: 'Quadra-Week', value: 'QUARDRA_WEEKLY' },
    { label: 'Semi-Month', value: 'SEMI_MONTHLY' },
    { label: 'Month', value: 'MONTHLY' },
    { label: 'Thirty Day Month', value: 'THIRTY_DAY_MONTH' },
    { label: 'Bi-Month', value: 'BI_MONTHLY' },
    { label: 'Quarter', value: 'QUARTERLY' },
    { label: 'Half Year', value: 'HALF_YEARLY' },
    { label: 'Year', value: 'YEARLY' }
  ];

  paymentFrequencies: string[] = ['DAILY', 'WEEKLY', 'BI_WEEKLY', 'QUARDRA_WEEKLY', 'SEMI_MONTHLY', 'MONTHLY', 'THIRTY_DAY_MONTH', 'BI_MONTHLY', 'QUARTERLY', 'HALF_YEARLY', 'YEARLY'];
  roundingDirections: string[] = ['ROUND_UP', 'ROUND_DOWN', 'TRUNCATE'];

  emis: number = 0;
  totalAmountPaid: number = 0;
  totalPricipalPaid: number = 0;
  totalInterestPaid: number = 0;
  totalPrepayments: number = 0;
  totalPreClosureCharge: number = 0;

  tableData: ScheduleRow[] = [];
  advancedOpen: boolean = false;

  validationError: string = '';

  constructor(private http: HttpClient) { }

  /** 
   * Submits the form and fetches the loan schedule
   */
  submitForm() {
    this.validationError = '';

    // Basic validations
    if (!this.principal || this.principal <= 0) {
      this.validationError = 'Principal must be greater than 0';
      return;
    }
    if (!this.roi || this.roi <= 0) {
      this.validationError = 'Rate of Interest must be greater than 0';
      return;
    }
    if (!this.term || this.term <= 0) {
      this.validationError = 'Term must be greater than 0';
      return;
    }
    if (!this.termUnit) {
      this.validationError = 'Please select a Term Unit';
      return;
    }
    if (!this.paymentFrequency) {
      this.validationError = 'Please select a Payment Frequency';
      return;
    }

    // Build request body
    const requestBody = {
      principal: this.principal,
      roi: this.roi,
      term: this.term,
      termUnit: this.termUnit,
      paymentFrequency: this.paymentFrequency,
      startDate: this.startDate,
      prepayment: this.prepayment,
      floatingInterest: this.floatingInterest,
      preClosure: this.preClosure,
      roundTo: this.roundTo,
      roundingDirection: this.roundingMode
    };

    // Call backend
    this.http.post<any>('http://localhost:8080/api/getLoanSchedule', requestBody)
      .pipe(catchError(err => {
        console.error('Error calling backend:', err);
        return of(null);
      }))
      .subscribe(response => {
        if (!response) return;

        this.emis = response.numberOfEMIs;
        this.totalAmountPaid = response.totalAmountPaid;
        this.totalInterestPaid = response.totalInterestPaid;
        this.totalPrepayments = response.totalPrepayment;
        this.totalPreClosureCharge = response.preClosureCharge;
        this.totalPricipalPaid = response.principal;

        this.tableData = response.loanSchedule.map((row: any) => ({
          termNo: row.payment,
          openingBalance: row.openingBalance,
          monthlyEmi: row.monthlyEMI,
          principalPaid: row.principalPaid,
          interestPaid: row.interestPaid,
          roi: row.rateOfInterest,
          prepaymentAmount: row.prepaymentAmount,
          remainingBalance: row.remainingBalance
        }));
      });
  }

  /**
   * Updates ROI or Prepayment on change and re-calls backend
   */
  updateCell(row: ScheduleRow, field: keyof ScheduleRow, value: number) {
    if (field === 'roi') {
      this.floatingInterest[row.termNo] = value;
    } else if (field === 'prepaymentAmount') {
      this.prepayment[row.termNo] = value;
    }

    // Trigger recalculation from backend
    this.submitForm();
  }

  /** Resets everything */
  resetForm() {
    this.principal = undefined!;
    this.roi = undefined!;
    this.term = undefined!;
    this.termUnit = '';
    this.paymentFrequency = '';
    this.startDate = '';
    this.roundingMode = undefined;
    this.roundTo = undefined;

    this.preClosure = {};
    this.prepayment = {};
    this.floatingInterest = {};

    this.emis = 0;
    this.totalAmountPaid = 0;
    this.totalPricipalPaid = 0;
    this.totalInterestPaid = 0;
    this.totalPrepayments = 0;
    this.totalPreClosureCharge = 0;

    this.tableData = [];
    this.advancedOpen = false;
    this.validationError = '';
  }
}

