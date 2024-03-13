import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';

const BASE_URL = 'http://localhost:1200/banking/api/v1/accounts'

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  http = inject(HttpClient);

  constructor() { }

    createAccount(account: any) {
      return this.http.post(BASE_URL, account);
    }
}
