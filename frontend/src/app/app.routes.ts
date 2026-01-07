import { Routes } from '@angular/router';
import { Users } from './user-register/users';
import { UserProfile } from './user-profile/user-profile';
import { Login } from './modules/user/components/login/login';
import { UserDashboard } from './user-dashboard/user-dashboard';
import { AdminDashboard } from './admin-dashboard/admin-dashboard';

export const routes: Routes = [

  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: 'register', component: Users},

  { path: 'login', component: Login },

  // USER DASHBOARD
  { path: 'user', component: UserDashboard },

  // ADMIN DASHBOARD
  { path: 'admin', component: AdminDashboard },

  { path: '**', redirectTo: 'login' }
];

