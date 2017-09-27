package com.demo.persistence;

import com.demo.model.PubResources;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PubResourcesMapper {
    int deleteByPrimaryKey(String resourceId);

    int insert(PubResources record);

    int insertSelective(PubResources record);

    PubResources selectByPrimaryKey(String resourceId);

    int updateByPrimaryKeySelective(PubResources record);

    int updateByPrimaryKey(PubResources record);

    List<String> getResourcesByAuthName(String authName);
}