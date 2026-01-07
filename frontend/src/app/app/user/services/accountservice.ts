import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Accountservice {

  private baseUrl = 'http://localhost:5545/api/accounts';

  constructor(private http: HttpClient) {}

  createAccount(userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/create/${userId}`, {});
  }

  getAccount(userId: number): Observable<any> {
    const token = localStorage.getItem('token');
  
    return this.http.get(`${this.baseUrl}/${userId}`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  freezeAccount(userId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/freeze/${userId}`, {});
  }

  unfreezeAccount(userId: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/unfreeze/${userId}`, {});
  }
  
}
