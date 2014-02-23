package com.idaeo.dropwizard.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;

@Table(name = "Transaction")
@Entity
@NoArgsConstructor
public class Transaction {

    @Id @Column @GeneratedValue
    @Getter @Setter
    Long id;

    @Getter @Setter
    private DateTime transactionDate;

    @Getter @Setter
    private Integer amount;

    @Getter @Setter
    private TransactionType type;

    @Getter @Setter
    private Tenant tenant;


}
