export interface UserResponse {
    id: number;
    fullName: string;
    email: string;
    mobile: string;
    role: string;
    status: string;
    verified: boolean;
    createdAt: string;
  }
  
  export interface UserRequest {
    fullName: string;
    email: string;
    mobile: string;
    password: string;
    role: string;
  }
  