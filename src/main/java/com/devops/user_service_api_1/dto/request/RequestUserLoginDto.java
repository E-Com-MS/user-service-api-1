package com.devops.user_service_api_1.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUserLoginDto {
    private String email;
    private String password;
}
