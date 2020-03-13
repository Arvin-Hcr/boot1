package com.whqfl.dao.impl;

import com.whqfl.dao.NewsDao;
import com.whqfl.entity.News;
import com.whqfl.entity.RechargeRule;
import com.whqfl.util.BaseDao;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class NewsDaoImpl implements NewsDao {

    BaseDao baseDao=new BaseDao();
    @Override
    public List<News> getNewsList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {

        String sql = "select * from news where 1=1 ";
        if (StringUtils.isNotBlank(searchStartTime) && StringUtils.isNotBlank(searchEndTime)) {
            sql += " and createdTime between '" + searchStartTime + "' and '" + searchEndTime + "'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and title like '%" + searchName + "%'";
        }
        if (searchStatus != null && searchStatus != -1){
            sql += " and status = " + searchStatus;
        }
        sql += " order by createdTime desc limit ?,?";
        Object[] params = {pageNumber, pageSize};
        return baseDao.queryList(sql, params, News.class);
    }

    @Override
    public int getNewsListCount(String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) {
        String sql = "select count(1) len from news where 1=1";
        if (StringUtils.isNotBlank(searchStartTime) && StringUtils.isNotBlank(searchEndTime)) {
            sql += " and createdTime between '" + searchStartTime + "' and '" + searchEndTime +"'";
        }
        if (StringUtils.isNotBlank(searchName)) {
            sql += " and title like '%" + searchName + "%'";
        }
        if (searchStatus != null && searchStatus != -1){
            sql += " and status = " + searchStatus;
        }
        List<Map<String, Object>> mapList = baseDao.executeQuery(sql, null);
        if (mapList != null && mapList.size() > 0) {
            return Integer.parseInt(mapList.get(0).get("len") + "");
        }
        return 0;
    }

    @Override
    public int updateNwesId(News news) {
        String sql="update news set title=?,content=?,staffId=?,endTime=?,status=?,createdTime=? where id=?";
        Object[] parames={news.getTitle(),news.getContent(),news.getStaffId(),news.getEndTime(),news.getStatus(), news.getCreatedTime(),news.getId()};
        int update = baseDao.executeUpdate(sql, parames);
        return update;
    }

    @Override
    public int insertNews(News news) {
        String sql="insert news (title,content,status,staffId,createdTime,endTime) values (?,?,?,?,?,?)";
        Object[] params = {news.getTitle(),news.getContent(),news.getStatus(),news.getStaffId(),news.getCreatedTime(),news.getEndTime()};
        int i = baseDao.executeUpdate(sql, params);
        return i;
    }


    @Override
    public int delNews(Integer id) {
        String sql="select status from news where id=?";
        Object[] params={id};
        List<Map<String,Object>> list = baseDao.executeQuery(sql, params);
        int status=0;
        if (list!=null&&list.size()>0){
            status=Integer.parseInt(list.get(0).get("status")+"");
        }
        sql="update news set status=? where id=?";
        status= status ==0  ? 1 : 0;
        Object[] params1={status,id};
        int i = baseDao.executeUpdate(sql, params1);
        return i;
    }


}
