package com.demo.rest;

import com.demo.model.PubUsers;
import com.demo.persistence.PubUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestService {

    @Autowired
    private PubUsersMapper pubUsersMapper;

    @RequestMapping(path = "/persons/{name}", method = RequestMethod.GET)
    public PubUsers getPerson(@PathVariable("name") String name) {
        return pubUsersMapper.selectByUserName(name);
    }
}
