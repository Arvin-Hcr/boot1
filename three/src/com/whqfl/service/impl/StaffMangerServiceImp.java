package com.whqfl.service.impl;

import com.whqfl.dao.StaffMangerDao;
import com.whqfl.dao.impl.StaffMangerDaoImp;
import com.whqfl.entity.Staff;
import com.whqfl.service.StaffMangerService;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StaffMangerServiceImp implements StaffMangerService {
    private StaffMangerDao staffMangerDao=new StaffMangerDaoImp();
    @Override
    public Map<String,Object> showStaff(Integer pageNumber, Integer pageSize,String staffId,String staffName,String createdTime) throws Exception{
        List<Staff> staffList = staffMangerDao.showStaff(pageNumber, pageSize, staffId, staffName, createdTime);
        Integer countStaff = staffMangerDao.CountStaff(staffId, staffName, createdTime);
        Map<String,Object>map= new HashMap<>();
        map.put("list",staffList);
        map.put("count",countStaff);

        return map;
    }

    @Override
    public int updataStaff(Integer id, Integer staffId, String staffName, String idCard, String phone, String adress, String createdTime, Integer status, Integer releId)throws Exception {
       Staff staff=new Staff();
        if(StringUtils.isBlank(staffName)){
            throw new Exception("输入的名字不能为空");
        }
        if(StringUtils.isBlank(idCard)){
            throw new Exception("身份证号");
        }
        if(StringUtils.isBlank(phone)){
            throw new Exception("电话不能为空");
        }
        if(StringUtils.isBlank(adress)){
            throw new Exception("地址不能为空");
        }
        if(StringUtils.isBlank(phone)){
            throw new Exception("电话不能为空");
        }
        if(StringUtils.isBlank(createdTime)){
            throw new Exception("创建的时间不能为空");
        }
        staff.setStaffName(staffName);
        staff.setIdCard(idCard);
        staff.setAddress(adress);
        staff.setCreatedTime(createdTime);
        staff.setId(id);
        staff.setPhone(phone);
        staff.setRoleid(releId);
        staff.setStaffId(staffId);
        staff.setStatus(status);
        staff.setPassword(staffId+"");
        int res=0;
        if(id==null||id==0){
            List<Map<String, Object>> staffId1 = staffMangerDao.getStaffId();
            Object staffId2 = staffId1.get(0).get("staffId");
            staffId= IntegerUtils.ToInteger(staffId2+"");
            staff.setStaffId(staffId+1);
            staff.setPassword(staffId+1+"");
            res=staffMangerDao.insertStaff(staff);
         }else{
            res=staffMangerDao.updataStaff(staff);
        }
        if(res==0){
            throw new Exception("信息有误");
        }
        return res;
    }

    @Override
    public int updataStaffRoid(String id) throws Exception{
        if (StringUtils.isBlank(id)){
            throw new Exception("ID为空");
        }
        int i = staffMangerDao.updataStaffRoid(id);
        if(i==0){
            throw new Exception("修改有误");
        }
        return i;
    }
}
