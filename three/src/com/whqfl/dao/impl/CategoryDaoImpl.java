package com.whqfl.dao.impl;

import com.whqfl.dao.CategoryDao;
import com.whqfl.entity.CateGory;
import com.whqfl.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {
    private BaseDao baseDao = new BaseDao();
    @Override
    public List<CateGory> getAllCategory(Integer pageNumber, Integer pageSize, String searchId, String searchName) {
        String sql="select * from " +
                " category" +
                " where 1=1 ";
        if (StringUtils.isNotBlank(searchId)) {
            searchId="%"+searchId+"%";
            sql+=" and id like '"+searchId+"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName="'%"+searchName+"%'";
            sql+=" and name like "+searchName;
        }
        sql+=" order by id asc limit ?,? ";
        Object[] pas={pageNumber,pageSize};
        List<CateGory> list=baseDao.queryList(sql,pas,CateGory.class);
        return list;
    }

    @Override
    public List<CateGory> getAllCategory() {
        String sql="select * from " +
                " category" +
                " where 1=1 ";
        Object[] pas={};
        List<CateGory> list=baseDao.queryList(sql,pas,CateGory.class);
        return list;
    }

    @Override
    public int getAllCategoryCount(String searchId, String searchName) {
        String  sql="select count(1) len from " +
                "category " +
                " where 1=1 ";
        if (StringUtils.isNotBlank(searchId)) {
            searchId="%"+searchId+"%";
            sql+=" and  id like '"+searchId+"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            searchName="'%"+searchName+"%'";
            sql+=" and name like "+searchName;
        }
        List<Map<String,Object>> list=baseDao.executeQuery(sql,null);
        if (list != null && list.size()>0) {
            Map<String,Object> map=list.get(0);
            Integer len=Integer.parseInt(map.get("len")+"");
            return len;
        }

        return 0;
    }

    @Override
    public int updateCategory(CateGory cateGory) {
        String sql = "update category set name = ?,momo = ?,status = ? where id =? ";
        Object[] params = {cateGory.getName(),cateGory.getMomo(),cateGory.getStatus(),cateGory.getId()};
        int len = baseDao.executeUpdate(sql,params);
        return len;
    }

    @Override
    public int addCategory(CateGory cateGory) {
        String sql="insert category (id,name ,momo,status) values (?,?,?,?)";
        Object[] params = {cateGory.getId(),cateGory.getName(),cateGory.getMomo(),cateGory.getStatus()};
        int i = baseDao.executeUpdate(sql, params);
        return i;
    }

    @Override
    public int delCategory(Integer Id) {

        String sql = "delete from  category  where Id = ? " ;
        Object[] params = {Id};
        int len = baseDao.executeUpdate(sql, params);
        return len;
    }
}
