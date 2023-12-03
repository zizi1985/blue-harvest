package com.blueharvest.assignment.dto;

import lombok.Data;

/**
 * @author Z.Eskandari
 * created on 11/25/23
 */
@Data
public class CustomerDto {

    private String firstName;
    private String lastName;
    private String nationalId;
    private String phoneNumber;

    @Override
    public String toString() {
        return "CustomerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
