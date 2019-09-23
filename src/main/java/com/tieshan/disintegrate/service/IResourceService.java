package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/30 18:11
 * @version: 1.0
 * @modified By:
 */
public interface IResourceService {
 /*   public List<Menu> menus();
    public List<Resource> treeList();*/

    /**
     * 查询部门右测的资源
     * @param depart_id
     * @return
     */
    public List<Menu> departTree(String depart_id);


    /**
     * 查询登录人相关左侧菜单
     * @param depart_id
     * @return
     */
    public List<Menu> departMenus(String depart_id);

    /**
     * 添加资源
     *
     * @param resource
     */
    public int add(Resource resource);


    /**
     * 所有资源列表
     *
     * @return
     */
    List<Menu> getResourceTree();

    /**
     * 更新部门和资源的关系
     *
     * @param department_id
     * @returns
     */
    int updateDR(String department_id, String resource_id);

//    /**
//     * 查询部门的资源
//     *
//     * @param department_id
//     * @return
//     */
//    List<Menu> getResourceByDepartId(String department_id);
}
