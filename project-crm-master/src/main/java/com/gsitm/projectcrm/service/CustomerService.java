package com.gsitm.projectcrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsitm.projectcrm.dto.CustomerDto;
import com.gsitm.projectcrm.dto.TextLogDto;
import com.gsitm.projectcrm.mapper.CustomerMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {
	@Autowired 
	CustomerMapper customerMapper;

	public List<TextLogDto> getDTOInfo(Long keyword){
		return customerMapper.getDTOInfo(keyword);
	}

	public List<CustomerDto> list() {
		// TODO Auto-generated method stub
		return customerMapper.list();
	}

	public List<CustomerDto> listFind(String keyword) {
		// TODO Auto-generated method stub
		return customerMapper.listFind(keyword);
	}

	public CustomerDto searchAdminOne(Long keyword) {
		// TODO Auto-generated method stub
		return customerMapper.searchAdminOne(keyword);
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
