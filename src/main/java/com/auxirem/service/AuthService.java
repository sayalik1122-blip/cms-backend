package com.auxirem.service;

import com.auxirem.payload.request.LoginRequest;
import com.auxirem.payload.request.RegisterRequest;
import com.auxirem.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    void registerAdmin(RegisterRequest request);
}
