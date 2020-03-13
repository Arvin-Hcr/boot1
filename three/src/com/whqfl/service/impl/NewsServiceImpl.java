package com.whqfl.service.impl;

import com.whqfl.dao.NewsDao;
import com.whqfl.dao.impl.NewsDaoImpl;
import com.whqfl.entity.News;

import com.whqfl.entity.RechargeRule;
import com.whqfl.service.NewsService;
import com.whqfl.util.BusinessException;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsServiceImpl implements NewsService {

private NewsDao newsDao=new NewsDaoImpl();

    @Override
    public Map<String, Object> getNewsList(Integer pageNumber, Integer pageSize, String searchStartTime, String searchEndTime, String searchName, Integer searchStatus) throws Exception {
        pageNumber = (pageNumber - 1) * pageSize;
        List<News> newsList = newsDao.getNewsList(pageNumber, pageSize, searchStartTime, searchEndTime, searchName, searchStatus);
        int len = newsDao.getNewsListCount(searchStartTime,searchEndTime,searchName,searchStatus);
        Map<String,Object> map = new HashMap<>();
        map.put("list",newsList);
        map.put("count",len);
        return map;
    }

    @Override
    public int updateNewsId(String id, String ruleId, String ruleName, String ruleCoff, String ruleStatus, String ruleCreatedDate, String ruleEndDate, String ruleAmount) throws Exception {
        if (StringUtils.isBlank(ruleName)) {
            throw new BusinessException("ruleName不能为空");
        }
        if (StringUtils.isBlank(ruleCoff)) {
            throw new BusinessException("ruleCoff不能为空");
        }
        if (StringUtils.isBlank(ruleStatus)) {
            throw new BusinessException("ruleStatus不能为空");
        }
        if (StringUtils.isBlank(ruleEndDate)) {
            throw new BusinessException("ruleEndDate不能为空");
        }
        if (StringUtils.isBlank(ruleAmount)) {
            throw new BusinessException("ruleAmount不能为空");
        };
        News news=new News();
        news.setId(IntegerUtils.ToInteger(ruleId));
        news.setStatus(IntegerUtils.ToInteger(ruleStatus));
        news.setStaffId((int) Double.parseDouble(ruleCoff));
        news.setTitle(ruleName);
        news.setContent(ruleAmount);
        news.setEndTime(ruleEndDate);
        news.setCreatedTime(ruleCreatedDate);
        int i=0;
        if (StringUtils.isBlank(ruleId)){

            i = newsDao.insertNews(news);
        }else {
            i=newsDao.updateNwesId(news);
        }
        return i;
    }

    @Override
    public int delNews(Integer id) {
        return newsDao.delNews(id);
    }


}
