package com.intehel.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.intehel.dao.admin.SysResourceMapper;
import com.intehel.dao.admin.SysRoleMapper;
import com.intehel.model.admin.SysResource;
import com.intehel.model.admin.SysRole;
import com.intehel.service.admin.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@Description 角色管理service实现类
*@Author 侯森林
*@Date
*@Time
*/
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;
    /**
     *@Description 获取角色列表
     *@Param role：角色信息 pageNumber :页数 pageSize：每页大小
     *@Return 角色列表
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public List<SysRole> findRole(SysRole role, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        return sysRoleMapper.findRole(role);
    }
    /**
     *@Description 获取角色数量
     *@Param sysRole 角色信息
     *@Return Integer符合条件的角色数量
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public Integer countRoleList(SysRole sysRole) {
        return sysRoleMapper.findRoleCount(sysRole);
    }

    /**
     *@Description 新增角色
     *@Param sysrole 角色信息
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public void insertRole(SysRole sysRole) {
        sysRoleMapper.insertSelective(sysRole);
    }

    /**
     *@Description 更新角色
     *@Param sysRole 角色信息
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public void updateRoleById(SysRole sysRole) {
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);

    }

    /**
     *@Description 根据角色名称获取角色信息
     *@Param role 角色信息
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public SysRole findRoleByRoleName(SysRole role) {
        return sysRoleMapper.findRoleByName(role);
    }

    /**
     *@Description 角色授权
     *@Param
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public void grandResource(Integer roleId, List<Integer> list) {
        sysRoleMapper.grantResource(roleId,list);
    }

    /**
     *@Description 解除角色权限
     *@Param roleId　角色id
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public void deleteResourceByRoleId(Integer roleId) {
       sysRoleMapper.deleteResourceByRoleId(roleId);
    }

    /**
     *@Description 根据角色id获取菜单
     *@Param roleId 角色id
     *@Return
     *@Author 侯森林
     *@Date
     *@Time
     */
    @Override
    public Object findResourceByRoleId(Integer roleId) {
        Map<String,Object> map=new HashMap<>();
        List<SysResource> sysResources=sysResourceMapper.findResourceByRoleId(roleId);
        List<SysResource> allResource=sysResourceMapper.findAllResource();
        List<Integer> list=new ArrayList<>();
        //解决layui树形控件勾选问题，在后台对数据进行处理
        for(SysResource sysResource:sysResources){
            if(sysResource.getLevel()==4){
                list.add(sysResource.getResourceId());
            }
            if(sysResource.getLevel()==3){
                boolean flag=true;
                for(SysResource s:allResource){
                    if(sysResource.getResourceId().equals(s.getParentId())){
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    list.add(sysResource.getResourceId());
                }
            }
        }
        map.put("treeNode",getResourceTree(allResource));
        map.put("list",list);
            return map;

    }

    /**
    *@Description 删除角色
    *@Param
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(SysRole role) {
        role.setIsDel(1);
        sysRoleMapper.updateByPrimaryKeySelective(role);
        sysRoleMapper.deleteResourceByRoleId(role.getRoleId());
    }

    /**
    *@Description 获取权限树
    *@Param resources 系统权限
    *@Return
    *@Author 侯森林
    *@Date
    *@Time
    */
    public  List <Map<String,Object>> getResourceTree(List<SysResource> resources) {
        List<Map<String,Object>>childList=new ArrayList<>();
        for(SysResource menu:resources){
            if(menu.getParentId()==1){
                Map<String,Object> chidMap=new HashMap();
                chidMap.put("id",menu.getResourceId());
                chidMap.put("title",menu.getMenuName());
                chidMap.put("spread", true);
                List<Map>grandSonList=new ArrayList<>();
                for(SysResource grandsonMenu:resources){
                    if(grandsonMenu.getParentId()==(int)menu.getResourceId()) {
                        Map<String,Object> grandSon = new HashMap();
                        grandSon.put("id", grandsonMenu.getResourceId());
                        grandSon.put("title", grandsonMenu.getMenuName());
                        grandSon.put("spread", true);
                        List<Map>sonList=new ArrayList<>();
                        for(SysResource sonMenu:resources){
                            if(sonMenu.getParentId()==(int)grandsonMenu.getResourceId()){
                                Map<String,Object> son = new HashMap();
                                son.put("id", sonMenu.getResourceId());
                                son.put("title", sonMenu.getMenuName());
                                son.put("spread", true);
                                sonList.add(son);
                            }
                        }
                        grandSon.put("children",sonList);
                        grandSonList.add(grandSon);
                    }
                }
                chidMap.put("children",grandSonList);
                childList.add(chidMap);
            }

        }
        return  childList;
    }
}
