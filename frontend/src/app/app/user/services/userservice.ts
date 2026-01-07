import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserRequest, UserResponse } from '../../shared/models/user.model.ts';

@Injectable({
  providedIn: 'root',
})
export class Userservice {

  private baseUrl = 'http://localhost:5545/api/users';

  private logUrl = 'http://localhost:5545/api/auth';

  constructor(private http: HttpClient) {}

  registerUser(data: UserRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/register`, data);
  }

  getUserByEmail(email: string): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}/${email}`);
  }

  activateUser(email: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/activate/${email}`, {});
  }

  // getProfile(email: string): Observable<UserResponse> {
  //   return this.http.get<UserResponse>(`${this.baseUrl}/profile?email=${email}`);
  // }

  getProfile(email: string): Observable<UserResponse> {
    const token = localStorage.getItem('token');
    return this.http.get<UserResponse>(`${this.baseUrl}/profile?email=${email}`, {
      headers: {
        Authorization: `Bearer ${token || ''}` // add fallback empty string
      }
    });
  }
  

  login(email: string, password: string) {
    return this.http.post<any>(`${this.logUrl}/login`, {
      email,
      password
    });
  }

  saveUserData(data: any) {
    localStorage.setItem('token', data.token);
    localStorage.setItem('email', data.email);
    localStorage.setItem('role', data.role);
    localStorage.setItem('userId', data.userId.toString());
  }

  logout() {
    localStorage.clear();
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
  
}
