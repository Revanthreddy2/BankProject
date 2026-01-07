import { Component } from '@angular/core';
import { UserRequest } from '../app/shared/models/user.model.ts';
import { Userservice } from '../app/user/services/userservice.js';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  imports: [CommonModule, FormsModule],
  templateUrl: './users.html',
  styleUrl: './users.css',
})


export class Users {

  user: UserRequest = {
    fullName: '',
    email: '',
    mobile: '',
    password: '',
    role: 'USER'
  };
  

  constructor(private userService: Userservice, private router: Router) {}

  register() {
    this.userService.registerUser(this.user).subscribe({
      next: (response) => {        
        alert('Registration successful!');

        // ✅ STORE USER INFO LOCALLY
        localStorage.setItem('userEmail', response.email);
        localStorage.setItem('userId', response.id.toString());

      // Optional: store full user (not recommended for sensitive apps)
        localStorage.setItem('user', JSON.stringify(response));

        this.router.navigate(['/users/profile']);
      },
      error: () => alert('Registration failed')
    });
  }


  goToLogin() {
    // Navigate to login page
    this.router.navigate(['/login']);
  }

}
