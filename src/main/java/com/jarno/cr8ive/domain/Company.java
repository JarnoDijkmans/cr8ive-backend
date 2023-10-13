package com.jarno.cr8ive.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Information about the company. Used for companyDetailsPage
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Company extends User{
    private long companyId;
    private String name;
    private String logo;
    private String description;
    private String location;
    private String website;
}
