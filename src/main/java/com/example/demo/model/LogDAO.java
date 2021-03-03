package com.example.demo.model;


import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name = "user_logs")
public class LogDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private Integer id;


    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDAO user;

    @NotNull
    @Column(name = "login_time")
    private String loginTime;

    @NotNull
    @Column(name = "logout_time")
    private String logoutTime;

    @NotNull
    @Column(name = "browser")
    private String browser;

    @NotNull
    @Column(name = "ip")
    private String ip;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

