package controller;

import javax.annotation.Resource;

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
	public Object getStaffInfo(Integer infoId) {
		AccountDto accountDto = accountService.getStaffInfoByInfoId(infoId);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "update_staff_info.do", produces = {"application/json;charset=UTF8"})
	public Object updateStaffInfo(Staff staff) {
		AccountDto accountDto = accountService.updateStaffInfoByInfoId(staff);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_customer_info.do", produces = {"application/json;charset=UTF8"})
	public Object getcustomerInfo(Integer infoId) {
		AccountDto accountDto = accountService.getCustomerInfoByInfoId(infoId);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "update_customer_info.do", produces = {"application/json;charset=UTF8"})
	public Object updateCustomerInfo(Customer customer) {
		System.out.println("---------------------------------");
		System.out.println(customer);
		AccountDto accountDto = accountService.updateCustomerInfoByInfoId(customer);
		return accountDto;
	}
}
