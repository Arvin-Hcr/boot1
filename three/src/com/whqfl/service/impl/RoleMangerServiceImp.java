package com.whqfl.service.impl;

import com.whqfl.dao.RoleMangerDao;
import com.whqfl.dao.impl.RoleMangerDaoImp;
import com.whqfl.entity.MenuTree;
import com.whqfl.entity.Role;
import com.whqfl.service.RoleMangerService;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleMangerServiceImp implements RoleMangerService {
    private RoleMangerDao roleMangerDao=new RoleMangerDaoImp();

    @Override
    public List<Role> querAll() throws Exception {
        List<Role> roles = roleMangerDao.querAll();

        if(roles.isEmpty()){
            throw new Exception("角色查询有误");
        }
        return roles;
    }

    @Override
    public Map<String, Object> showRole(Integer pageNumber, Integer pageSize, String roleName, Integer status,Integer roleId) throws Exception {
        if(pageNumber==null){
            throw new Exception("信息有误");
        }
        pageNumber=(pageNumber-1)*pageSize;

        List<Role> roles = roleMangerDao.showRole(pageNumber, pageSize, roleName, status,roleId);

        int i = roleMangerDao.showRoleCount(roleName, status, roleId);
        if(roles.isEmpty()||i==0){
            throw new Exception("有异常");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("list",roles);
        map.put("count",i);
        return map;
    }

    @Override
    public int updataRole(String id, String roleName, String description, String Status) throws Exception {
        Integer idt=null;
        Integer idStatus=null;
        if(StringUtils.isNotBlank(id)){
          idt = IntegerUtils.ToInteger(id);
        }
        if(StringUtils.isBlank(roleName)){
            throw new Exception("名字不能为空");
        }
        if(StringUtils.isBlank(description)){
            throw new Exception("描述不能为空");
        }
        if(!StringUtils.isBlank(Status)){
            idStatus = IntegerUtils.ToInteger(Status);
        }
        Role role = new Role();
        role.setId(idt);
        role.setRoleName(roleName);
        role.setStatus(idStatus);
        role.setDescription(description);
        int res=0;
        if(idt==null){
            res=roleMangerDao.insertStaff(role);
        }else{
         res=roleMangerDao.updateRole(role);
        }
        if(res==0){
            throw new Exception("操作有误");
        }
        return 0;
    }

    @Override
    public int updataStaffRoid(String id) throws Exception {
        if (StringUtils.isBlank(id)){
            throw new Exception("ID为空");
        }
        int i = roleMangerDao.delRole(id);
        if(i==0){
            throw new Exception("修改有误");
        }
        return i;

    }


    /**
     * 权限管理
     * @return
     */
    @Override
    public List<MenuTree> MenTree(String id) throws Exception{
        List<MenuTree> menuTrees = roleMangerDao.MenTree(id);
        return menuTrees;

    }

    @Override
    public int UpdateTree(String roleId, String treeList) throws Exception{
        System.out.println(treeList);
        if(StringUtils.isBlank(roleId)){
            throw new Exception("操作有误");
        }
        if(StringUtils.isBlank(treeList)){
            throw new Exception("操作有误请重新操作");
        }

        String substring = treeList.substring(1, treeList.length()-1);
        String[] splitTree = substring.split(",");

        for(int i=0;i<splitTree.length;i++){
            System.out.print(splitTree[i]+"++");
        }

        int res=roleMangerDao.updateTree(roleId);
        if(res==0){
            throw new Exception("删除权限失误");
        }
        int res1=roleMangerDao.insertTree(roleId,splitTree);
        if(res1==0){
            System.out.println("增加权限有误");
        }
        return 1;
    }
}
