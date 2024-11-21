package com.devops.user_service_api_1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity( name = "system_user" )
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
    @Id
    @Column( name = "user_id", unique = true, nullable = false )
    private String userId;
    @Column( unique = true, nullable = false )
    private String email;
    @Column( columnDefinition = "TINYINT", nullable = false )
    private boolean status;

    @OneToOne( mappedBy = "systemUser", fetch = FetchType.LAZY )
    private BillingAddress billingAddress;
}
