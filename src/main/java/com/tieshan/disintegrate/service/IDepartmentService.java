package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.Department;

import java.util.List;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/4 15:47
 * @version: 1.0
 * @modified By:
 */
public interface IDepartmentService {
    /**
     * 获取所有部门
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<Department> allDepartment(int page, int pageSize, String company_code);

    /**
     * 获取所有部门
     *
     * @param company_code
     * @return
     */
    List<Department> getAllDepart(String company_code);

    /**
     * 校验部门是否存在
     *
     * @param department_name
     * @return
     */
    Department getDepart(String department_name);

    /**
     * 添加部门
     *
     * @param department
     * @return
     */
    int addDepart(Department department);

    /**
     * 修改部门
     *
     * @param department
     * @return
     */
    int updateDepart(Department department);

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    int delDepart(long id);
}
