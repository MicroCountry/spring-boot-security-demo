package com.demo.persistence;

import com.demo.model.PubUsersRoles;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PubUsersRolesMapper {
    int deleteByPrimaryKey(String id);

    int insert(PubUsersRoles record);

    int insertSelective(PubUsersRoles record);

    PubUsersRoles selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PubUsersRoles record);

    int updateByPrimaryKey(PubUsersRoles record);
}