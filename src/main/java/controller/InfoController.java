package controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.Customer;
import entity.Staff;
import service.AccountService;

@Controller
public class InfoController {

	@Resource
	private AccountService accountService;
	
	@RequestMapping(path = "customer_info.do", produces = {"application/json;charset=UTF8"})
	public String customerInfo() {
		return "customer_info";
	}
	
	@RequestMapping(path = "staff_info.do", produces = {"application/json;charset=UTF8"})
	public String staffInfo() {
		return "staff_info";
	}
	
	@ResponseBody
	@RequestMapping(path = "get_staff_info.do", produces = {"application/json;charset=UTF8"})
	public Object getStaffInfo(HttpSession session) {
		AccountDto accountDto = accountService.getStaffInfoByInfoId((Integer)((Map)session.getAttribute("user")).get("info_id"));
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "update_staff_info.do", produces = {"application/json;charset=UTF8"})
	public Object updateStaffInfo(Staff staff, HttpSession session) {
		staff.settId((Integer)((Map)session.getAttribute("user")).get("info_id"));
		System.out.println(staff);
		AccountDto accountDto = accountService.updateStaffInfoByInfoId(staff);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_customer_info.do", produces = {"application/json;charset=UTF8"})
	public Object getcustomerInfo(HttpSession session) {
		AccountDto accountDto = accountService.getCustomerInfoByInfoId((Integer)((Map)session.getAttribute("user")).get("info_id"));
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "update_customer_info.do", produces = {"application/json;charset=UTF8"})
	public Object updateCustomerInfo(Customer customer, HttpSession session) {
		customer.setInfoId((Integer)((Map)session.getAttribute("user")).get("info_id"));
		AccountDto accountDto = accountService.updateCustomerInfoByInfoId(customer);
		return accountDto;
	}
}
