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
    List<Department> allDepartment();

    Department getDepart(String department_name);

    int addDepart(Department department);

    int updateDepart(Department department);

    int delDepart(long id);
}
