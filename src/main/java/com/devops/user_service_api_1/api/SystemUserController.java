package com.devops.user_service_api_1.api;

import com.devops.user_service_api_1.dto.request.RequestSystemUserDto;
import com.devops.user_service_api_1.dto.request.RequestUserLoginDto;
import com.devops.user_service_api_1.service.SystemUserService;
import com.devops.user_service_api_1.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service/api/v1/users")
@RequiredArgsConstructor
public class SystemUserController {
    private final SystemUserService systemUserService;

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> signup(@RequestBody RequestSystemUserDto requestSystemUserDto){
        systemUserService.signup(requestSystemUserDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "user successfully created", null),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<StandardResponse> login(@RequestBody RequestUserLoginDto requestUserLoginDto){
        System.out.println(requestUserLoginDto.getEmail());
        System.out.println(requestUserLoginDto.getPassword());
        return new ResponseEntity<>(
                new StandardResponse(200, "login successful", systemUserService.login(requestUserLoginDto.getEmail(),requestUserLoginDto.getPassword())),
                HttpStatus.OK
        );
    }

}
