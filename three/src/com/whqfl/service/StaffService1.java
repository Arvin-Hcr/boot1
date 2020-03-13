package com.whqfl.service;

import java.util.Map;

public interface StaffService1 {
    Map<String,Object> login(Integer staffId, String password) throws Exception;
}
