package com.devops.user_service_api_1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSystemUserDataDto {
    private String firstName;
    private String lastName;
    private String email;
    private ResponseBillingAddressDataDto billingAddress;
}
