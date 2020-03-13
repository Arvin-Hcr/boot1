package com.whqfl.dao;

import com.whqfl.entity.Staff;

import java.util.List;
import java.util.Map;

public interface StaffMangerDao {
    /**
     * 这个是对于员工表的管理
     * @param pageNumber
     * @param pageSize
     * @param staffId
     * @param staffName
     * @param createdTime
     * @return
     */
    List<Staff> showStaff(Integer pageNumber, Integer pageSize,String staffId,String staffName,String createdTime);

    /**
     * 查询总条数
     * @param staffId
     * @param staffName
     * @param createdTime
     * @return
     */
    Integer CountStaff(String staffId,String staffName,String createdTime);

    /**
     * 这个是修改
     * @param staff  这个是员工类
     * @return
     */
    int updataStaff(Staff staff);

    /**
     * 这个是添加
     * @param staff
     * @return
     */
    int insertStaff(Staff staff);

    /**
     * 这个是操作
     * @param id
     * @return
     */
    int updataStaffRoid(String id);

    /**
     * 获取最后一个员工ID
     */
    List<Map<String,Object>> getStaffId();
}
