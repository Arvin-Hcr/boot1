package com.whqfl.dao;

import com.whqfl.entity.Card;
import com.whqfl.entity.User;

import java.util.List;
public interface UserDao {
    /**
     * 按照用户 id，名字，查询所有员工的信息
     * @param pageSize
     * @param pageNumber
     * @param searchId
     * @param searchName
     * @return
     */
    List<User> getAllUser(Integer pageSize, Integer pageNumber, String searchId, String searchName,String cardId);

    /**
     * 按照用户ID，名字查询总条数
     * @param searchId
     * @param searchName
     * @return
     */
    int getAllUsercount(String searchId, String searchName);

    /**
     * 通过会员ID修改会员
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 通过会员ID规则删除
     * @return
     */
    int delUser(User user);

    /**
     * 新增会员 新增会员的时候创建一张卡
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 新增卡
     * @param cardId
     * @param userId
     * @param amount
     * @param credit
     * @param status
     * @param staffId
     * @param leavelId
     * @return
     */
    int insertCard(Integer cardId,Integer userId,Double amount,Integer credit,Integer status,Integer staffId,Integer leavelId);
    /**
     * 获得用户的最后一个用户id
     * @return
     */
    Integer getUserLastUserID();

    /**
     * 获得最后一张卡的卡号
     * @return
     */
    Integer getUserLastCardId();


}
