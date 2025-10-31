import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LoanService {

  getInitialData() {
    return [
      { month: 1, principal: 1000, interest: 50, roi: 0, prepayment: 0, balance: 1000 },
      { month: 2, principal: 1000, interest: 50, roi: 0, prepayment: 0, balance: 1000 },
      // Add more months as needed
    ];
  }

  updateLoanData(loans: any[], index: number, updatedLoan: any) {
    // Implement your recalculation logic here
    // For now, just return the same array
    return [...loans];
  }
}
