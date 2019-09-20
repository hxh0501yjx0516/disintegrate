package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/30 17:09
 * @version: 1.0
 * @modified By:
 */
public interface ResourceMapper {
   /* *//**
     * 获取资源列表
     *
     * @param id 参数
     * @return
     *//*
    List<Resource> departMenus(String id);*/

    /**
     * 获取当前用户的菜单
     *
     * @param depart_id 参数
     * @return
     */
    List<Menu> departTree(@Param("depart_id") String depart_id);

    /**
     * 获取所有资源
     *
     * @return
     */
    List<Menu> getAllResource();

    /**
     * 增加资源
     *
     * @param resource
     */
    int save(@Param("resource") Resource resource);


    /**
     * 删除部门资源关系
     *
     * @param department_id
     * @return
     */
    int delDepartment_Resource(@Param("department_id") String department_id);

    /**
     * 插入资源部门关系
     *
     * @param list
     * @return
     */
    int insertDepartment_Resource(@Param("list") List<Map<String, Object>> list);

    /**
     * 查询部门的的资源
     *
     * @param department_id
     * @return
     */
    List<Map<String, Object>> getDepartment_Resource(@Param("department_id") String department_id);

}
