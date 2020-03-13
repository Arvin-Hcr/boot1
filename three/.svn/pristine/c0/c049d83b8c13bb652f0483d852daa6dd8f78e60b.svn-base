package com.whqfl.dao.impl;

import com.whqfl.dao.ChangePassDao;
import com.whqfl.util.BaseDao;

public class ChangePassDapimpl implements ChangePassDao {
    private BaseDao baseDao=new BaseDao();
    @Override
    public int pass(Integer staffId, String password) {
        String sql="update staff set password=? where staffId=?";
        Object[] params={password,staffId};
        int i = baseDao.executeUpdate(sql, params);
        return i;

    }

}
