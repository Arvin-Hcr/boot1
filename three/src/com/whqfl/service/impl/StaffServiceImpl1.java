package com.whqfl.service.impl;

import com.whqfl.dao.StaffDao;
import com.whqfl.dao.StaffDao1;
import com.whqfl.dao.impl.StaffDaoImpl;
import com.whqfl.dao.impl.StaffDaoImpl1;
import com.whqfl.service.StaffService1;
import com.whqfl.util.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class StaffServiceImpl1 implements StaffService1 {
    private StaffDao1 staffDao = new StaffDaoImpl1();
    @Override
    public Map<String, Object> login(Integer staffId, String password) throws BusinessException {
        if (staffId == null || staffId == 0){
            throw new BusinessException("账户不能为空!");
        }
        if (StringUtils.isBlank(password)){
            throw new BusinessException("密码不能为空!");
        }
        return staffDao.login(staffId,password);
    }
}
