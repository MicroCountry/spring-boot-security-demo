package com.demo.model;

import java.util.Date;
import java.util.List;

public class PubUsers {
    private String userId;

    private String userAccount;

    private String userName;

    private String userPassword;

    private Integer enabled;

    private Integer issys;

    private String userDesc;

    private Date lastPasswordResetDate;

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    private List<PubAuthorities> authorities;

    public List<PubAuthorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<PubAuthorities> authorities) {
        this.authorities = authorities;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getIssys() {
        return issys;
    }

    public void setIssys(Integer issys) {
        this.issys = issys;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc == null ? null : userDesc.trim();
    }
}