package com.gsitm.projectcrm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gsitm.projectcrm.dto.CustomerDto;
import com.gsitm.projectcrm.dto.TextLogDto;

@Mapper

public interface CustomerMapper {

	List<TextLogDto> getDTOInfo(@Param("keyword")Long keyword);

	List<CustomerDto> list();

	List<CustomerDto> listFind(String keyword);

	CustomerDto searchAdminOne(Long keyword);
	
	void updateCustomer(@Param("customerDto")CustomerDto customerDTO);
	
	void registerCustomer(@Param("customerDto")CustomerDto customerDTO);
	void deleteCustomer(@Param("CUST_SN")Long CUST_SN);
}
