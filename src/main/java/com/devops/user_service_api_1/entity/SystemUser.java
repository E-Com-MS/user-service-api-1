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

    @Column( name = "email", unique = true, nullable = false )
    private String email;

    @Column( name = "status", columnDefinition = "TINYINT", nullable = false )
    private boolean status;

    @Column( name = "full_name", nullable = false )
    private String fullName;

    @Column( name = "is_account_non_expired", columnDefinition = "TINYINT", nullable = false )
    private boolean isAccountNonExpired;

    @Column( name = "is_credential_non_expired", columnDefinition = "TINYINT", nullable = false )
    private boolean isCredentialNonExpired;

    @Column( name = "is_account_non_locked", columnDefinition = "TINYINT", nullable = false )
    private boolean isAccountNonLocked;

    @Column( name = "is_enabled", columnDefinition = "TINYINT", nullable = false )
    private boolean isEnabled;

    @OneToOne( mappedBy = "systemUser", fetch = FetchType.LAZY )
    private BillingAddress billingAddress;
}
