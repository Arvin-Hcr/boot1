package com.whqfl.service.impl;

import com.whqfl.dao.CategoryDao;
import com.whqfl.dao.impl.CategoryDaoImpl;
import com.whqfl.entity.CateGory;
import com.whqfl.service.CategoryService;
import com.whqfl.util.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<CateGory> getAllCategory() {
        List<CateGory> list= categoryDao.getAllCategory();
        return list;
    }

    @Override
    public  Map<String,Object>  getAllCategoryInfo(Integer pageNumber, Integer pageSize, String searchId, String searchName) throws BusinessException {
        if (pageNumber == null || pageNumber == 0 )  {
            throw new BusinessException("当前页数不能为空");
        }
        if (pageSize == null||pageSize==0) {
            throw new BusinessException("每页条数不能为空");
        }
        pageNumber=(pageNumber-1)*pageSize;

        List<CateGory> list= categoryDao.getAllCategory( pageNumber,  pageSize, searchId, searchName);
        int count=categoryDao.getAllCategoryCount(searchId,searchName);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return  map;

    }




    @Override
    public int updateOrAddCategory(Integer id, String name, String momo, Integer status) throws Exception {
        CateGory cateGory = new CateGory();
        cateGory.setName(name);
        cateGory.setMomo(momo);
        cateGory.setStatus(status);
        int len = 0;
        if (id == null || id == 0) {
            len = categoryDao.addCategory(cateGory);
        } else {
            cateGory.setId(id);
            len = categoryDao.updateCategory(cateGory);
        }
        return len;
    }

    @Override
    public int delCategory(Integer Id) {

        int del = 0;

        if (Id != null ){
            del = categoryDao.delCategory(Id);

        }
        return del;
    }

    @Override
    public int getAllCategoryCount(String searchId, String searchName) {
       return categoryDao.getAllCategoryCount(searchId,searchName);
    }


}
