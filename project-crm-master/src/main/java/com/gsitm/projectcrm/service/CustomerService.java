package com.gsitm.projectcrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsitm.projectcrm.dto.CustomerDto;
import com.gsitm.projectcrm.dto.TextLogDto;
import com.gsitm.projectcrm.mapper.CustomerMapper;

@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	public List<TextLogDto> getTextLogsByCustomerSn(Long customerSn) {
		return customerMapper.getTextLogsByCustomerSn(customerSn);
	}

	public List<CustomerDto> list() {
		return customerMapper.list();
	}

	public List<CustomerDto> listFind(String keyword) {
		return customerMapper.listFind(keyword);
	}

	public CustomerDto findCustomerBySn(Long customerSn) {
		return customerMapper.findCustomerBySn(customerSn);
	}

	public void updateCustomer(CustomerDto customerDTO) {
		customerMapper.updateCustomer(customerDTO);
	}

	public void registerCustomer(CustomerDto customerDTO) {
		customerMapper.registerCustomer(customerDTO);
	}

	public void deleteCustomer(Long CUST_SN) {
		customerMapper.deleteCustomer(CUST_SN);
	}
}