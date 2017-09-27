package com.demo.service;

import com.demo.persistence.PubResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguomin on 17-9-26.
 */
@Service
public class ResourceService {
    @Autowired
    private PubResourcesMapper pubResourcesMapper;

    public List<String> getResourcesByAuthName(String authName){
        return pubResourcesMapper.getResourcesByAuthName(authName);
    }
}
