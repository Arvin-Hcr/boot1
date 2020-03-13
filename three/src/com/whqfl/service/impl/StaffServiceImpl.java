package com.whqfl.service.impl;

import com.whqfl.dao.impl.StaffDaoImpl;
import com.whqfl.service.StaffService;
import com.whqfl.dao.StaffDao;
import com.whqfl.util.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao = new StaffDaoImpl();
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
