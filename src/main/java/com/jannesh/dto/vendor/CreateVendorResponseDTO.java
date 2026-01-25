package com.jannesh.dto.vendor;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateVendorResponseDTO {

    private Long vendorId;
    private String name;
    private String contact;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String status;
}
