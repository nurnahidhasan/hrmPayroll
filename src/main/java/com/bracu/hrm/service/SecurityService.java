package com.bracu.hrm.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
