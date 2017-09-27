package com.demo.persistence;

import com.demo.model.PubUsers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PubUsersMapper {
    int deleteByPrimaryKey(String userId);

    int insert(PubUsers record);

    int insertSelective(PubUsers record);

    PubUsers selectByPrimaryKey(String userId);

    PubUsers selectByUserName(String userName);

    int updateByPrimaryKeySelective(PubUsers record);

    int updateByPrimaryKey(PubUsers record);
}