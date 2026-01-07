import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Accountservice } from '../app/user/services/accountservice';
import { Userservice } from '../app/user/services/userservice';
import { Transactionservice } from '../app/user/services/transactionservice';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.html',
  standalone:true,
  imports: [
    CommonModule   // ✅ REQUIRED for *ngIf, *ngFor, date pipe
  ],
  styleUrls: ['./user-dashboard.css']
})
export class UserDashboard {

  activeTab: string = 'account';

  user: any;
  account: any;
  transactions: any[] = [];

  constructor(
    private accountService: Accountservice,
    private userService: Userservice,
    private transactionService: Transactionservice,
    private router: Router
  ) {}

  // ✅ LOAD USER FIRST — THIS FIXES YOUR BUG
  ngOnInit(): void {
    const email = localStorage.getItem('email');
    const tok = localStorage.getItem('token');
    console.log(tok);
    if (!email) {
      console.error("No email found, redirecting to login");
      this.router.navigate(['/login']);
      return;
    }

    this.loadUser(email);
  }

  // ✅ Step 1: Load User
  loadUser(email: string) {
    this.userService.getProfile(email).subscribe({
      next: (user) => {
        this.user = user;

        // ✅ Now safe to load account
        this.loadAccount(user.id);
      },
      error: err => {
        console.error('Failed to load user', err);
      }
    });
  }

  // ✅ Step 2: Load Account
  loadAccount(userId: number) {
    if (!userId) {
      console.error('Invalid userId:', userId);
      return;
    }

    this.accountService.getAccount(userId).subscribe({
      next: res => this.account = res,
      error: err => console.error('Account load failed', err)
    });
  }

  // ✅ Transactions
  loadTransactions(userId: number) {
    this.transactionService.getUserTransactions(userId).subscribe({
      next: res => this.transactions = res,
      error: err => console.error(err)
    });
  }

  switchTab(tab: string) {
    this.activeTab = tab;

    if (tab === 'transactions') {
      if (this.user && this.user.id) {
        this.loadTransactions(this.user.id);
      } else {
        console.error('User not loaded yet. Cannot fetch transactions.');
      }
    }
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
