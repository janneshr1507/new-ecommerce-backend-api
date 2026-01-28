package com.jannesh.entity.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long customerId;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String contact;

    @Column(nullable = false)
    public String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public CustomerStatus status;

    @PrePersist
    public void onCreate() {
        this.status = CustomerStatus.ACTIVE;
    }
}
