package com.company.user.impl;

import com.company.api.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Override
    public String greet(String name)
    {
        return "Hello, " + name + " from module-user";
    }

    @Override
    public String moduleName()
    {
        return "module-user";
    }
}
