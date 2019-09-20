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
     * 获得资源树(资源类型为菜单类型)
     * <p>
     * 通过用户ID判断，他能看到的资源
     *
     * @return
     */
    public List<Menu> departTree(String depart_id);


    /**
     * 获得资源列表
     *
     * @return
     */
    public List<Menu> departMenus(String id);

    /**
     * 添加资源
     *
     * @param resource
     */
    public int add(Resource resource);


    /**
     * 资列表
     *
     * @return
     */
    List<Menu> getResourceTree();

    /**
     * 删除部门资源关系表
     *
     * @param department_id
     * @returns
     */
    int updateDR(String department_id, String resource_id);

    /**
     * 查询部门的资源
     *
     * @param department_id
     * @return
     */
    List<Menu> getResourceByDepartId(String department_id);
}
