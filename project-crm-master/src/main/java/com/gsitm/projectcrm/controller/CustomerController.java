package com.gsitm.projectcrm.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsitm.projectcrm.dto.CustomerDto;
import com.gsitm.projectcrm.dto.TextLogDto;
import com.gsitm.projectcrm.service.CustomerService;

@Controller
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("/cust")
	public String customView() {
		return "customerView";
	}

	@GetMapping("/customerSearch")
	public String customerSearch() {
		return "customerSearch";
	}

	@PostMapping("/TextLogAjax")
	@ResponseBody
	public List<TextLogDto> getCustomerTextLogs(@RequestBody Map<String, String> body) {
		String keywordStr = body.get("keyword");
		log.info("TextLogAjax request. keyword={}", keywordStr);

		Long customerSn = Long.parseLong(keywordStr);
		List<TextLogDto> textLogDtos = customerService.getTextLogsByCustomerSn(customerSn);

		log.debug("TextLogAjax response. resultCount={}", textLogDtos.size());
		return textLogDtos;
	}

	@PostMapping("/searchAjax")
	@ResponseBody
	public List<CustomerDto> searchCustomers(@RequestBody Map<String, String> body) {
		String keyword = body.get("keyword");
		log.info("searchAjax request. keyword={}", keyword);

		List<CustomerDto> customers = customerService.listFind(keyword);

		log.debug("searchAjax response. resultCount={}", customers.size());
		return customers;
	}

	@PostMapping("/searchAllAjax")
	@ResponseBody
	public List<CustomerDto> getAllCustomers() {
		List<CustomerDto> customers = customerService.list();

		log.debug("searchAllAjax response. resultCount={}", customers.size());
		return customers;
	}

	@PostMapping("/searchOneAjax")
	@ResponseBody
	public CustomerDto getCustomer(@RequestBody Map<String, String> body) {
		String keywordStr = body.get("keyword");
		log.info("searchOneAjax request. keyword={}", keywordStr);

		Long customerSn = Long.parseLong(keywordStr);
		CustomerDto customer = customerService.findCustomerBySn(customerSn);

		if (customer != null) {
			log.debug("searchOneAjax response. customer found. customerSn={}", customerSn);
		} else {
			log.debug("searchOneAjax response. customer not found. customerSn={}", customerSn);
		}

		return customer;
	}

	@PostMapping("/registerCustomer")
	@ResponseBody
	public void registerCustomer(@ModelAttribute CustomerDto customerDTO) {
		customerService.registerCustomer(customerDTO);

		log.info("registerCustomer completed.");
	}

	@PostMapping("/updateCustomer")
	@ResponseBody
	public void updateCustomer(@ModelAttribute CustomerDto customerDTO) {
		customerService.updateCustomer(customerDTO);

		log.info("updateCustomer completed.");
	}

	@PostMapping("/deleteCustomer")
	@ResponseBody
	public String deleteCustomer(@RequestParam("CUST_SN") Long CUST_SN) {
		customerService.deleteCustomer(CUST_SN);

		log.info("deleteCustomer completed. custSn={}", CUST_SN);
		return "Customer deleted successfully";
	}
}