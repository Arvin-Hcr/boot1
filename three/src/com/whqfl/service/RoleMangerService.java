package com.whqfl.service;

import com.whqfl.entity.MenuTree;
import com.whqfl.entity.Role;

import java.util.List;
import java.util.Map;


public interface RoleMangerService {
    /**
     *
     * @return
     * @throws Exception
     */
    List<Role> querAll()throws Exception;
    /**
     * 这个角色显示
     * @param pageNumber
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     * @throws Exception
     */
    Map<String,Object> showRole(Integer pageNumber, Integer pageSize, String roleName , Integer status,Integer roleId)throws Exception;

    /**
     * 这个是添加修改role数据
     * @param id
     * @param roleName
     * @param description
     * @param Status
     * @return
     * @throws Exception
     */
    int updataRole(String id,String roleName,String description,String Status)throws Exception;

    /**
     * 这个是操作
     * @param id
     * @return
     */
    int updataStaffRoid(String id)throws Exception;


    /**
     * 这个是权限管理
     * @return
     */
    List<MenuTree> MenTree(String id)throws Exception;

    /**
     * 修改权限
     * @param roleId 这个是角色id
     * @param treeList 这个是权限id
     * @return
     */
    int UpdateTree(String roleId,String treeList ) throws Exception;
}
