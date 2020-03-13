package com.whqfl.dao.impl;

import com.whqfl.dao.MenuDao1;
import com.whqfl.entity.Menu;
import com.whqfl.util.BaseDao;

import java.util.List;

public class MenuDaoImpl1 implements MenuDao1 {
    private BaseDao baseDao = new BaseDao();
    /*@Override
    public List<Menu> getMenuListByRoleId(Integer roleId) {
        //父级菜单
        String sql = "select a.resource_name resourceName,a.* from resource1 a"+
        "where a.pid = 0 and a.status = 1 order by a.sort asc";
        Object[] params = {roleId};
        List<Menu> menuList =  baseDao.queryList(sql,params,Menu.class);
        if(menuList != null && menuList.size() > 0){
            for (int i = 0; i < menuList.size(); i++) {
                //循环遍历查询，从零开始依次得到的
                Menu menu = menuList.get(i);
                //子菜单
                String subSql = "select r.resource_name resourceName,r.*"+
                "from resource1 r"+
               " where r.pid = ? and r.status = 1"+
                "order by r.sort asc";
                Object[] params1 = {roleId,menu.getId()};
                //通过对象实例方法获取对象
                List<Menu> subMenuList =  baseDao.queryList(subSql,params1,Menu.class);
                menu.setMenuList(subMenuList);
            }
        }
        return menuList;
    }*/
    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) {
        //父级菜单
        String sql = "select r.resource_name resourceName,r.* from resource1 r,(select * from resource_role where role_id = ?) a " +
                "where r.id = a.resource_id and r.pid = 0 and r.status = 1 order by r.sort asc";
        Object[] params = {roleId};
        List<Menu> menuList =  baseDao.queryList(sql,params,Menu.class);
        if(menuList != null && menuList.size() > 0){
            for (int i = 0; i < menuList.size(); i++) {
                //循环遍历查询，从零开始依次得到的
                Menu menu = menuList.get(i);
                //子菜单
                String subSql = "select r.resource_name resourceName,r.* " +
                        "from resource1 r join (select resource_id from resource_role where role_id=?) re on re.resource_id=r.id " +
                        "where r.pid = ? and r.status = 1 " +
                        "order by r.sort asc";
                Object[] params1 = {roleId,menu.getId()};
                //通过对象实例方法获取对象
                List<Menu> subMenuList =  baseDao.queryList(subSql,params1,Menu.class);
                menu.setMenuList(subMenuList);
            }
        }
        return menuList;
    }

}
