package com.demo.persistence;

import com.demo.model.PubAuthorities;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PubAuthoritiesMapper {
    int deleteByPrimaryKey(String authorityId);

    int insert(PubAuthorities record);

    int insertSelective(PubAuthorities record);

    PubAuthorities selectByPrimaryKey(String authorityId);

    int updateByPrimaryKeySelective(PubAuthorities record);

    int updateByPrimaryKey(PubAuthorities record);

    List<PubAuthorities> selectAuthoritiesByUserName(String userName);
    List<PubAuthorities> getAllAuthorities();
}