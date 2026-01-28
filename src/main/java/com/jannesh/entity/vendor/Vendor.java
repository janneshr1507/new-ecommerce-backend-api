package com.jannesh.entity.vendor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter @Setter
public class Vendor {

    @Id
    private Long vendorId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address1;
    private String address2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VendorStatus status;

    @PrePersist
    private void onCreate() {
        this.vendorId = ThreadLocalRandom.current().nextLong(1_000_000_000L,10_000_000_000L);
        this.status = VendorStatus.ACTIVE;
    }
}
