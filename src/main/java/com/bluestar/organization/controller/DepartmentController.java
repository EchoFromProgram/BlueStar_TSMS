package com.bluestar.organization.controller;

import com.bluestar.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 部门类控制器
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 15:23:27
 */
@Controller("dept")
public class DepartmentController {

    private DepartmentService departmentService = null;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


}
