package com.demo.service;

import com.demo.model.PubAuthorities;
import com.demo.persistence.PubAuthoritiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguomin on 17-9-26.
 */
@Service
public class AuthorityService {

    @Autowired
    public PubAuthoritiesMapper pubAuthoritiesMapper;

    public List<PubAuthorities> getAllAuthoritys(){
        return pubAuthoritiesMapper.getAllAuthorities();
    }
}
