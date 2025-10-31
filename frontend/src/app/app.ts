import { Component } from '@angular/core';
import { LoanTableComponent } from './loan-table/loan-table'; // <-- fixed path

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [LoanTableComponent],
  template: `
    <h1>Loan Amortization Calculator</h1>
    <loan-table></loan-table>
  `,
  styles: [`
    h1 {
      width: 100%;
      padding: 20px 5vw;
      background-color: #e9f2ff;
      color: #0d6efd;
      font-weight: 700;
      font-size: 2rem;
      text-align: left;
      border-radius: 10px;
      margin: 0 0 20px 0;
      box-shadow: 0 2px 10px rgba(0,0,0,0.05);
    }

    @media (max-width: 900px) {
      h1 {
        font-size: 1.5rem;
        padding: 15px 2vw;
      }
    }
  `]
})
export class App {}
