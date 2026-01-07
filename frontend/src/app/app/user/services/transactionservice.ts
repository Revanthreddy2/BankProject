import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface TransactionResponse {
  id: number;
  amount: number;
  type: 'CREDIT' | 'DEBIT';
  status: 'SUCCESS' | 'FAILED';
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class Transactionservice {

  private baseUrl = 'http://localhost:5545/api/transactions';

  constructor(private http: HttpClient) {}

  // -------- CREDIT --------
  credit(userId: number, amount: number): Observable<TransactionResponse> {
    const params = new HttpParams().set('amount', amount);
    return this.http.post<TransactionResponse>(
      `${this.baseUrl}/credit/${userId}`,
      null,
      { params }
    );
  }

  // -------- DEBIT --------
  debit(userId: number, amount: number): Observable<TransactionResponse> {
    const params = new HttpParams().set('amount', amount);
    return this.http.post<TransactionResponse>(
      `${this.baseUrl}/debit/${userId}`,
      null,
      { params }
    );
  }

  // -------- GET USER TRANSACTIONS --------
  getUserTransactions(userId: number): Observable<TransactionResponse[]> {

    const token = localStorage.getItem('token');
    return this.http.get<TransactionResponse[]>(
      `${this.baseUrl}/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );
  }
}

