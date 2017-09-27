package com.demo.service;

import com.demo.model.PubUsers;
import com.demo.persistence.PubUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangguomin on 17-9-26.
 */
@Service
public class PubUsersService {
    @Autowired
    private PubUsersMapper pubUsersMapper;

    public PubUsers selectByUserName(String userName){
        return pubUsersMapper.selectByUserName(userName);
    }
}
