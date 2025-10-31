import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanInput } from './loan-input';

describe('LoanInput', () => {
  let component: LoanInput;
  let fixture: ComponentFixture<LoanInput>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoanInput]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoanInput);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
