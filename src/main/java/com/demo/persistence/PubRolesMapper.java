package com.demo.persistence;

import com.demo.model.PubRoles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PubRolesMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(PubRoles record);

    int insertSelective(PubRoles record);

    PubRoles selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(PubRoles record);

    int updateByPrimaryKey(PubRoles record);
}