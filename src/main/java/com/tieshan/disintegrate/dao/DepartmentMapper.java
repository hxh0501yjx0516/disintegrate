package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.Department;
import com.tieshan.disintegrate.pojo.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/30 17:09
 * @version: 1.0
 * @modified By:
 */
public interface DepartmentMapper {
    List<Department> allDepartment(@Param("company_code") String company_code);

    Department getDepart(@Param("department_name") String department_name);

    int addDepart(@Param("depart") Department department);

    int updateDepart(@Param("depart") Department department);

    int delDepart(@Param("id") long id);

    Map<String, Object> getDepartById(@Param("id") String id);

}
