package com.whqfl.service;

import com.whqfl.entity.Staff;

import java.util.List;
import java.util.Map;

public interface StaffMangerService {
    /**
     * 这个是服务端把所有显示出来员工的信息
     * @return
     */
    Map<String,Object> showStaff(Integer pageNumber, Integer pageSize,String staffId,String staffName,String createdTime)throws Exception;

    /**
     * 这个是对于staff的怎改
     * @param Id
     * @param staffId
     * @param staffName
     * @param idCard
     * @param phone
     * @param adress
     * @param createdTime
     * @param status
     * @param releId
     * @return
     * @throws Exception
     */
    int updataStaff(Integer Id,Integer staffId,String staffName,String idCard,String phone,String adress,String createdTime,Integer status,Integer releId)throws Exception;

    /**
     * 这个是操作
     * @param id
     * @return
     */
    int updataStaffRoid(String id)throws Exception;
}
