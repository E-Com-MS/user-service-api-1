package com.devops.user_service_api_1.service.impl;

import com.devops.user_service_api_1.dto.request.RequestSystemUserDto;
import com.devops.user_service_api_1.service.SystemUserService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Override
    public void signUp(RequestSystemUserDto requestSystemUserDto) {

    }

    @Override
    public void login(String username, String password) {

    }
}
