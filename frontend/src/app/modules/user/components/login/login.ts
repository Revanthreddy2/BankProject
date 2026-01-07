import { Component } from '@angular/core';
import { Userservice } from '../../../../app/user/services/userservice';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css'], // fixed typo from styleUrl
})

export class Login {

  email = '';
  password = '';

  constructor(
    private userService: Userservice,
    private router: Router
  ) {}

  login() {
    // Hard-coded admin credentials
    const hardcodedAdmin = {
      email: 'admin@example.com',
      password: 'Admin@123',
      role: 'ADMIN'
    };

    if (this.email === hardcodedAdmin.email && this.password === hardcodedAdmin.password) {
      alert('Login successful as Admin');
      // Optionally save admin data in localStorage
      localStorage.setItem('userRole', hardcodedAdmin.role);
      this.router.navigate(['/admin']);
      return;
    }

    // Otherwise, call the backend for normal users
    this.userService.login(this.email, this.password).subscribe({
      next: (res) => {
        this.userService.saveUserData(res);
        alert('Login successful');
        if (res.role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else if (res.role === 'USER') {
          this.router.navigate(['/user']);
        }
      },
      error: () => alert('Invalid credentials')
    });
  }

  goToRegister() {
    this.router.navigate(['/register']);
  }

}
