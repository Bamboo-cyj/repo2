package com.fengk.service;

public interface MobileValidateCodeService {
    void send4Order(String telephone);

    void send4Login(String telephone);
}
