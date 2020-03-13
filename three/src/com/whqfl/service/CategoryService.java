package com.whqfl.service;

import com.whqfl.entity.CateGory;
import com.whqfl.util.BusinessException;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    /**获取所有器材类别
     */

    List<CateGory> getAllCategory();

    Map<String,Object> getAllCategoryInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws BusinessException;




    int updateOrAddCategory(Integer id ,String name,String momo,Integer status)throws Exception;

    /**
     * 删除器材分类
     */
    int delCategory(Integer Id);
    int getAllCategoryCount(String searchId, String searchName);


}
