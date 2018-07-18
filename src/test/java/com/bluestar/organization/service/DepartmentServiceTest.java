package com.bluestar.organization.service;

import com.bluestar.organization.common.DepartmentConst;
import com.bluestar.organization.entity.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 部门模块业务测试类
 *
 * @author Fish
 * ------> 1149062639@qq.com
 * created by 2018/07/18 15:51:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService = null;

    @Test
    public void testAddANewDepartment() {
        Department department = new Department();
        department.setDeptCode("RA9-003");
        department.setDeptName("福州检测部");
        department.setDeptLevel("1");
        department.setDeptRemark("福州检测部....");

        System.out.println(departmentService.addDepartment(department));
    }

    @Test
    public void testDeleteDepartment() {
        String deptId = "";

        System.out.println(departmentService.deleteDepartment(deptId));
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setDeptId("e1565d7d-efaa-4704-ba96-b10c07365592");
        department.setDeptCode("RA9-003");
        department.setDeptName("福州检测总部");
        department.setDeptLevel("1");
        department.setDeptStatus(DepartmentConst.DEPT_STATUS_ABANDON);
        department.setDeptRemark("福州检测总部....");
        String oldDeptCode = "RA9-003";

        System.out.println(departmentService.updateDepartment(department, oldDeptCode));
    }
}
