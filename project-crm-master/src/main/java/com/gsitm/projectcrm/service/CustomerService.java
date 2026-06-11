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
		validateCustomerInput(customerDTO);
		customerMapper.updateCustomer(customerDTO);
	}

	public void registerCustomer(CustomerDto customerDTO) {
		validateCustomerInput(customerDTO);
		customerMapper.registerCustomer(customerDTO);
	}

	public void deleteCustomer(Long CUST_SN) {
		customerMapper.deleteCustomer(CUST_SN);
	}

	private void validateCustomerInput(CustomerDto customerDTO) {
		if (customerDTO == null) {
			throw new IllegalArgumentException("Customer data is required.");
		}

		validateMaxLength("CUST_NM", customerDTO.getCUST_NM(), 100);
		validateMaxLength("EML_ADDR", customerDTO.getEML_ADDR(), 200);
		validateMaxLength("HOME_TELNO", customerDTO.getHOME_TELNO(), 11);
		validateMaxLength("MBL_TELNO", customerDTO.getMBL_TELNO(), 11);
		validateMaxLength("PRIDTF_NO", customerDTO.getPRIDTF_NO(), 13);
		validateMaxLength("CR_NM", customerDTO.getCR_NM(), 100);
		validateMaxLength("ROAD_NM_ADDR", customerDTO.getROAD_NM_ADDR(), 200);
	}

	private void validateMaxLength(String fieldName, String value, int maxLength) {
		if (value != null && value.length() > maxLength) {
			throw new IllegalArgumentException(fieldName + " must be " + maxLength + " characters or less.");
		}
	}
}