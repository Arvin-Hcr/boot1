package com.whqfl.service.impl;

import com.whqfl.dao.ChangePassDao;
import com.whqfl.dao.impl.ChangePassDapimpl;
import com.whqfl.entity.Staff;
import com.whqfl.service.ChangePassService;
import com.whqfl.util.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ChangePassServiceImpl implements ChangePassService {
    ChangePassDao changePassDao=new ChangePassDapimpl();

    @Override
    public int pass(Integer staffId, String password) throws Exception {
        if (staffId == null || staffId == 0){
            throw new BusinessException("登录成功!");
        }
        return changePassDao.pass(staffId,password);
    }

}
