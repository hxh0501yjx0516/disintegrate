package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.DepartmentMapper;
import com.tieshan.disintegrate.pojo.Department;
import com.tieshan.disintegrate.service.IDepartmentService;
import com.tieshan.disintegrate.util.PubMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/4 15:47
 * @version: 1.0
 * @modified By:
 */
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> allDepartment() {
        return departmentMapper.allDepartment();
    }

    @Override
    public Department getDepart(String department_name) {
        return departmentMapper.getDepart(department_name);
    }


    @Override
    public int addDepart(Department department) {
        int num = departmentMapper.addDepart(department);
        return num;
    }

    @Override
    public int updateDepart(Department department) {
        return departmentMapper.updateDepart(department);
    }

    @Override
    public int delDepart(long id) {
        return departmentMapper.delDepart(id);
    }
}
