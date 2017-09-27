package com.demo.persistence;

import com.demo.model.PubRolesAuthorities;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PubRolesAuthoritiesMapper {
    int deleteByPrimaryKey(String id);

    int insert(PubRolesAuthorities record);

    int insertSelective(PubRolesAuthorities record);

    PubRolesAuthorities selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PubRolesAuthorities record);

    int updateByPrimaryKey(PubRolesAuthorities record);
}