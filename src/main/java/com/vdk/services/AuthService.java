package com.vdk.services;

public interface AuthService {
    boolean authenticate(String username, String password);
}
