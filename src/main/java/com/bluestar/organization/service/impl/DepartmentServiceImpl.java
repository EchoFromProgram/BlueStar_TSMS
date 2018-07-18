package com.bluestar.organization.service.impl;

import com.bluestar.organization.dao.DepartmentDao;
import com.bluestar.organization.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 部门业务具体实现类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 14:31:43
 */
@Service
public class DepartmentServiceImpl {

    DepartmentDao departmentDao = null;

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }


}
