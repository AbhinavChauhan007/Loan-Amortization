import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { LOCALE_ID } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeIn from '@angular/common/locales/en-IN';

// Register Indian locale
registerLocaleData(localeIn);

bootstrapApplication(App, {
  providers: [
    { provide: LOCALE_ID, useValue: 'en-IN' } // globally sets ₹ for currency pipe
  ]
}).catch(err => console.error(err));
