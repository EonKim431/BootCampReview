package com.gsitm.projectcrm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gsitm.projectcrm.dto.CustomerDto;
import com.gsitm.projectcrm.dto.TextLogDto;

@Mapper
public interface CustomerMapper {

	List<TextLogDto> getTextLogsByCustomerSn(@Param("customerSn") Long customerSn);

	List<CustomerDto> list();

	List<CustomerDto> listFind(String keyword);

	CustomerDto findCustomerBySn(@Param("customerSn") Long customerSn);

	void updateCustomer(@Param("customerDto") CustomerDto customerDTO);

	void registerCustomer(@Param("customerDto") CustomerDto customerDTO);

	void deleteCustomer(@Param("CUST_SN") Long CUST_SN);
}