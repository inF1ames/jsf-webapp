package com.company.jsfwebapp;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class UserService {

    private Map<String, String> users = new HashMap<String, String>() {{
        put("admin@gmail.com", "admin");
    }};

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}
