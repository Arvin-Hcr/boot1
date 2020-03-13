package com.whqfl.dao;

import com.whqfl.entity.News;
import com.whqfl.entity.RechargeRule;

import java.util.List;
import java.util.Map;

public interface NewsDao {
    /**
     *
     * @param pageNumber
     * @param pageSize
     * @param searchStartTime
     * @param searchEndTime
     * @param searchName
     * @param searchStatus
     * @return
     */
    List<News> getNewsList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus);

    int getNewsListCount(String searchStartTime, String searchEndTime, String searchName, Integer searchStatus);
    int updateNwesId(News news);
    int insertNews(News news);
    int delNews(Integer id);


}
