package com.whqfl.dao;

import com.whqfl.entity.MenuTree;
import com.whqfl.entity.Role;

import java.util.List;

public interface RoleMangerDao {
    /**
     *
     * @return
     * @throws Exception
     */
    List<Role> querAll()throws Exception;
    /**
     * 角色信息
     * @param pageNumber
     * @param pageSize
     * @param roleName
     * @param status
     * @return
     */
    List<Role>showRole(Integer pageNumber, Integer pageSize,String roleName,Integer status,Integer roleId);

    /**
     * 查询总条数
     * @param roleName
     * @param status
     * @return
     */
    int showRoleCount(String roleName ,Integer status,Integer roleId);

    /**
     * 这个是修改角色
     * @param role
     * @return
     */
    int updateRole(Role role);

    /**
     * 这个是添加角色
     * @param role
     * @return
     */
    int insertStaff(Role role);

    /**
     * 这个是shanchu
     * @param id
     * @return
     */
    int delRole(String id);

    /**
     * 这个是权限管理
     * @return
     */
    List<MenuTree> MenTree(String id);

    /**
     *
     * 这个是先删除所有的权限在进行添加
     * @param role
     * @return
     */
    int updateTree(String role);

    /**
     * 删除完了后添加的权限树
     * @param role
     * @return
     */
    int insertTree(String role,String[]TreeList);
}
