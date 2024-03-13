package com.jtbank.backend.model.helper;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Setter;

@Embeddable
@Data
public class Credentials {
    @Column(unique = true, length = 50, nullable = false)
    private String accountEmail;
    @Column(nullable = false)
    private String accountPassword;
}
