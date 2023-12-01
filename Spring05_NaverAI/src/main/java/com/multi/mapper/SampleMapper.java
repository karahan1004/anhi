package com.multi.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper { //중요:::pacakege명과 mapper 명(SampleMApper) 가 resource-> mappers 의 SampleMapper.xml namespace와 같아야 함!!!
	
	int getTableCount();
}
