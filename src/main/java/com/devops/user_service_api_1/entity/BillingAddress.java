package com.devops.user_service_api_1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity( name = "billing_address" )
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillingAddress {
    @Id
    @Column( name = "address_id", unique = true, nullable = false, length = 20 )
    private String addressId;
    private String address;
    private String city;
    private String country;
    private String postal;

    @OneToOne
    @JoinColumn( name = "user_id")
    private SystemUser systemUser;
}
