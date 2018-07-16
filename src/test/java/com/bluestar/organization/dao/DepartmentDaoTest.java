package com.bluestar.organization.dao;

import com.bluestar.organization.common.DepartmentConst;
import com.bluestar.organization.common.util.CodeUtils;
import com.bluestar.organization.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 组织部门 dao 接口测试类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/16 15:10:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml"
})
public class DepartmentDaoTest {

    @Autowired
    DepartmentDao departmentDao = null;

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setDeptId(CodeUtils.getId());
        department.setDeptCode("RA9-002-003-001");
        department.setDeptName("深圳开发部维护小组服务器维护办公室");
        department.setDeptPCode("36097856-9a4d-41ae-9722-2b1be9bf96df");
        department.setDeptLevel("3");
        department.setDeptOrder(1);
        department.setDeptStatus(DepartmentConst.DEPT_STATUS_NORMAL);
        department.setDeptRemark("深圳开发总部下的维护小组的服务器维护办公室....");

        int affect = departmentDao.saveDepartment(department);
        System.out.println(affect);
    }
}
