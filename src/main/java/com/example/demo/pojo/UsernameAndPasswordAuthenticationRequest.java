package com.example.demo.pojo;

import java.io.Serializable;

public class UsernameAndPasswordAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = 963436562789986344L;

    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
