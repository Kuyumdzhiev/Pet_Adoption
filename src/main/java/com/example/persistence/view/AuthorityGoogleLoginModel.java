package com.example.persistence.view;

import com.example.persistence.entities.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthorityGoogleLoginModel {
    private long providerId;
    private UserEntity userId;
    private String providerName;
    private String accessToken;
}
