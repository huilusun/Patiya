package com.huiju.blackbrin.aspect;

import org.springframework.stereotype.Component;

import java.util.HashMap;


/**
 * Created by xpchen on 17/2/3.
 */
@Component
public class HttpSessionLocal {

    private static final ThreadLocal<HashMap<String,Object>> threadLocal = new ThreadLocal<>();

    private static final String KEY_USER = "user";

    private HashMap<String,Object> get(){
        HashMap<String,Object> map = threadLocal.get();
        if(map == null){
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map;
    }


    public void setUser(String user){
        get().put(KEY_USER,user);
    }

    public String getUser(){
        return (String) get().get(KEY_USER);
    }

    public void deleteUser() {
        get().remove(KEY_USER);
    }

}
