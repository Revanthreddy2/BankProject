import { Component, OnInit } from '@angular/core';
import { Userservice } from '../app/user/services/userservice';
import { UserResponse } from '../app/shared/models/user.model.ts';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-profile',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.css',
})


export class UserProfile implements OnInit{

  user!: UserResponse;

  constructor(private userService: Userservice) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile() {
    const email = localStorage.getItem('email'); // was 'userEmail'
  
    if (!email) {
      alert('User not logged in');
      return;
    }
  
    const token = localStorage.getItem('token'); // add JWT header
    this.userService.getProfile(email).subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (err) => {
        console.error('Profile load failed', err);
        alert('Failed to load profile');
      }
    });
  }
  

  // loadProfile() {
  //   const email = localStorage.getItem('userEmail');

  // if (!email) {
  //   alert('User not logged in');
  //   return;
  // }


  //   this.userService.getProfile(email).subscribe({
  //     next: (data) => {
  //       this.user = data;
  //     },
  //     error: () => {
  //       alert('Failed to load profile');
  //     }
  //   });
  // }

}
