package com.idaeo.dropwizard.api;

import com.yammer.dropwizard.json.JsonSnakeCase;
import org.joda.money.Money;
import org.joda.time.DateTime;


/**
 * Created by conordockry on 12/15/13.
 */
@JsonSnakeCase
public class Transaction {
    private DateTime transactionDate;
    private Money amount;
    private TransactionType type;
    private Tenant tenant;

    public DateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(DateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
