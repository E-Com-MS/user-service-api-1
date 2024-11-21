package com.devops.user_service_api_1.service;

import com.devops.user_service_api_1.dto.request.RequestSystemUserDto;

public interface SystemUserService {
    public void signUp(RequestSystemUserDto requestSystemUserDto);
    public void login(String username,String password);
}
