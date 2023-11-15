package com.case_product_management.service;

public interface ISecurityService {
    String findLoggedInUsername();

    void autologin(String email, String password);
}
