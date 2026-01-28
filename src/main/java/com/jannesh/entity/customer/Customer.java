package com.jannesh.entity.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter @Setter
@ToString
public class Customer {

    @Id
    public Long customerId;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false, unique = true)
    public String contact;

    @Column(nullable = false, unique = true)
    public String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public CustomerStatus status;

    @PrePersist
    public void onCreate() {
        this.customerId = ThreadLocalRandom.current().nextLong(1_000_000_000L,10_000_000_000L);
        this.status = CustomerStatus.ACTIVE;
    }
}
