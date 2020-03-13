package com.whqfl.dao.impl;

import com.whqfl.dao.StaffMangerDao;
import com.whqfl.entity.Staff;
import com.whqfl.util.BaseDao;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class StaffMangerDaoImp implements StaffMangerDao {
    private BaseDao<Staff>baseDao=new BaseDao<>();
    @Override
    public List<Staff> showStaff(Integer pageNumber, Integer pageSize, String status, String staffName,String createdTime) {
        String sql="select s.* ,r.roleName roleName from staff s,role r where s.roleId=r.id ";

        if(!StringUtils.isBlank(status)){
            Integer integerStatus = IntegerUtils.ToInteger(status);
            if(integerStatus!=-1){
            sql +=" and s.status='"+status+"' ";
            }
        }
        if(!StringUtils.isBlank(staffName)){
            sql +=" and s.staffName like '%"+staffName+"%' ";
        }

        if(!StringUtils.isBlank(createdTime)){
            sql +=" and s.createdTime='"+createdTime+"' ";
        }
        sql +=" order by id desc limit ?,?";
        Object[]objects={(pageNumber-1)*pageSize,pageSize};
        List<Staff> staff = baseDao.queryList(sql, objects, Staff.class);
        return staff;
    }


    @Override
    public Integer CountStaff(String status, String staffName, String createdTime) {
        String sql="select count(*) len from staff s,role r where s.roleId=r.id ";
        if(!StringUtils.isBlank(status)){
            Integer integerStatus = IntegerUtils.ToInteger(status);
            if(integerStatus!=-1){
                sql +=" and s.status='"+status+"' ";
            }
        }
        if(!StringUtils.isBlank(staffName)){
            sql +=" and s.staffName like '%"+staffName+"%' ";
        }

        if(!StringUtils.isBlank(createdTime)){
            sql +=" and s.createdTime='"+createdTime+"' ";
        }
        List<Map<String,Object>> staff = baseDao.executeQuery(sql,null);
//        System.out.println(staff.get(0).get("len"));
        if(!staff.isEmpty()){
            Integer integer = IntegerUtils.ToInteger((String) staff.get(0).get("len"));
            return integer;
        }

        return 0;
    }

    @Override
    public int updataStaff(Staff staff) {
        System.out.println(staff.getStaffName());
        String sql="update staff set staffId = ?,staffName=?," +
                "idCard=? ,phone=?,address=?,createdTime=?,status=?,roleId=? WHERE id=?";
        Object[]objects={staff.getStaffId(),staff.getStaffName(),staff.getIdCard(),staff.getPhone(),
        staff.getAddress(),staff.getCreatedTime(),staff.getStatus(),staff.getRoleid(),staff.getId()};
       return baseDao.executeUpdate(sql,objects);

    }

    @Override
    public int insertStaff(Staff staff) {
        String sql="insert into staff (staffId,staffName,idCard,phone,address,createdTime,status,roleId,password) values (?,?,?,?,?,?,?,?,?)";
        Object[]objects={staff.getStaffId(),staff.getStaffName(),staff.getIdCard(),staff.getPhone(),
                staff.getAddress(),staff.getCreatedTime(),staff.getStatus(),staff.getRoleid(),staff.getPassword()};

        return baseDao.executeUpdate(sql,objects);
    }

    @Override
    public int updataStaffRoid(String id) {
        String sql="select status from staff where id=?";
        Object[]objects={id};
        List<Map<String, Object>> list = baseDao.executeQuery(sql, objects);
        if(!list.isEmpty()){
        Integer status = IntegerUtils.ToInteger(list.get(0).get("status")+"");
        status =status==1?2:1;
         sql="update staff set status=? where id = ?";
         Object[]objects1={status,id};
         return baseDao.executeUpdate(sql,objects1);
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> getStaffId() {
        String sal="select staffId from staff ORDER BY id desc limit 0,1";
        return baseDao.executeQuery(sal,null);
    }


}
