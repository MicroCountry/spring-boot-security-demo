package com.demo.persistence;

import com.demo.model.PubAuthoritiesResources;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PubAuthoritiesResourcesMapper {
    int deleteByPrimaryKey(String id);

    int insert(PubAuthoritiesResources record);

    int insertSelective(PubAuthoritiesResources record);

    PubAuthoritiesResources selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PubAuthoritiesResources record);

    int updateByPrimaryKey(PubAuthoritiesResources record);
}