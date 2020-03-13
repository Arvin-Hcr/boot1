package com.whqfl.dao.impl;

import com.whqfl.dao.StaffDao1;
import com.whqfl.util.BaseDao;

import java.util.List;
import java.util.Map;

public class StaffDaoImpl1 implements StaffDao1 {
    private BaseDao baseDao = new BaseDao();

    @Override
    public Map<String, Object> login(Integer staffId, String password) {
        String sql = "select * from user where userId = ? and phone = ?";
        Object[] params = {staffId,password};
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql,params);
        if (mapList != null && mapList.size() > 0){
            return mapList.get(0);
        }
        return null;
    }
}
