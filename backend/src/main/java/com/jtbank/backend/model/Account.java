package com.jtbank.backend.model;

import com.jtbank.backend.model.helper.Auditing;
import com.jtbank.backend.model.helper.Credentials;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity // -> for storing this class members in the table of db
@Table(name = "account_table")
public class Account extends Auditing{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountSlNo;
    @Column(unique = true)
    private long accountNumber;
    @Column(name = "account_name", nullable = false)
    private String accountHolderName;

    @Column(nullable = false)
    private String contactNumber;
    @Lob
    private String aboutCustomer;
    private double accountBalance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Embedded
    private Credentials credentials;
//    @Embedded
//    private Auditing auditing;
}
