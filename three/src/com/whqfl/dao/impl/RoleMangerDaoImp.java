package com.whqfl.dao.impl;

import com.whqfl.dao.RoleMangerDao;
import com.whqfl.entity.MenuTree;
import com.whqfl.entity.Role;
import com.whqfl.util.BaseDao;
import com.whqfl.util.IntegerUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class RoleMangerDaoImp implements RoleMangerDao {
    private BaseDao<Role>baseDao=new BaseDao<>();

    @Override
    public List<Role> querAll() throws Exception {
        String sql="select * from role where status=1";
        return baseDao.queryList(sql,null,Role.class);
    }

    @Override
    public List<Role>showRole(Integer pageNumber, Integer pageSize,String roleName ,Integer status,Integer roleId) {
        String sql="select * from role where 1=1 and grade>? ";
        if(!StringUtils.isBlank(roleName)){
            sql +=" and roleName like '%"+roleName+"%'";
        }
        if(status!=0){
            sql +=" and status= '"+status+"' ";
        }
        sql +=" limit ?,?";
        Object[]objects={roleId,pageNumber,pageSize};
        return baseDao.queryList(sql,objects,Role.class);
    }

    @Override
    public int showRoleCount(String roleName, Integer status,Integer roleId) {
        String sql="select count(*) len from role where 1=1 and grade>?";
        if(!StringUtils.isBlank(roleName)){
            sql +=" and roleName= '"+roleName+"'";
        }
        if(status!=0){
            sql +=" and status= '"+status+"' ";
        }
        Object []objects={roleId};
        List<Map<String,Object>> list = baseDao.executeQuery(sql, objects);
        if (list!=null&&list.size()>0){
            return Integer.parseInt(list.get(0).get("len")+"");
        }
        return 0;
    }

    @Override
    public int updateRole(Role role) {
        String sql="update role set roleName=?,description=?,status=? where id=?";
        Object[]objects={role.getRoleName(),role.getDescription(),role.getStatus(),role.getId()};
        int i = baseDao.executeUpdate(sql, objects);
        return i;
    }

    @Override
    public int insertStaff(Role role) {
        String sql="insert into role (roleName,description,status) values (?,?,?)";
        Object []objects={role.getRoleName(),role.getDescription(),role.getStatus()};
        int i = baseDao.executeUpdate(sql, objects);
        return i;
    }

    @Override
    public int delRole(String id) {

        String sql="select status from role where id=?";
        Object[]objects={id};
        List<Map<String, Object>> list = baseDao.executeQuery(sql, objects);
        System.out.println(list.get(0).get("status"));
        if(!list.isEmpty()){
            Integer status = IntegerUtils.ToInteger(list.get(0).get("status")+"");
            status =status==1?2:1;
            sql="update role set status=? where id = ?";
            Object[]objects1={status,id};
            return baseDao.executeUpdate(sql,objects1);
        }
        return 0;
    }



    @Override
    public List<MenuTree> MenTree(String id) {
        BaseDao<MenuTree>baseDao1=new BaseDao<>();
        String sql="select id nodeid,resource_name text,pid from resource  where pid=0 and status=1 ";
        List<MenuTree> menuTrees = baseDao1.queryList(sql, null, MenuTree.class);

        /**
         * 设置一级节点是否被选中
         */
        if(menuTrees!=null||menuTrees.isEmpty()){
            for(int i=0;i<menuTrees.size();i++){
                MenuTree tree = menuTrees.get(i);
                int treeid = tree.getNodeid();
                String sqll="select * from resource_role where role_id = ? and resource_id = ?";
                Object[]objects1={id,tree.getNodeid()};
                List<Map<String, Object>> list1 = baseDao1.executeQuery(sqll, objects1);
                if(list1!=null&&list1.size()>0){
                    tree.setState("true");
                }else{
                    tree.setState("");
                }

                /**
                 * 这个是二级节点
                 */
                String sql1="select id nodeid,resource_name text,pid from resource where pid="+treeid+" and status=1";
                List<MenuTree> menuTrees1 = baseDao1.queryList(sql1, null, MenuTree.class);

                tree.setTreeMenuList(menuTrees1);
                for(int j=0;j<menuTrees1.size();j++){
                    MenuTree tree1 = menuTrees1.get(j);
                    String sql2="select * from resource_role where role_id = ? and resource_id = ?";
                    Object[]objects={id,tree1.getNodeid()};
                    List<Map<String, Object>> list = baseDao1.executeQuery(sql2, objects);
                    if(list!=null&&list.size()>0){
                        tree1.setState("true");
                    }else{
                        tree1.setState("");
                    }

                }
            }
        }

        return menuTrees;
    }

    @Override
    public int updateTree(String role) {
        String sql1="select * from resource_role where role_id=?";
        String sql="delete from resource_role where role_id=?";
        Object[]objects={role};
        if(baseDao.executeQuery(sql1,objects)==null||baseDao.executeQuery(sql1,objects).size()==0){
            return 1;
        }
        return baseDao.executeUpdate(sql,objects);

    }

    @Override
    public int insertTree(String role, String[] treeList) {
        int res=0;
        for(int i=0;i<treeList.length;i++){

            String sql="insert into resource_role (role_id,resource_id) value(?,?)";
            Object []objects={role,treeList[i]};
            res=baseDao.executeUpdate(sql,objects);
            if(res==0){
                break;
            }

        }
        return res;
    }
}
