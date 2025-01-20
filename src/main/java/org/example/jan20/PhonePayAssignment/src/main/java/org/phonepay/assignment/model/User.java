package org.phonepay.assignment.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/*
Name: Himanshu Bhardwaj
Date: 20-01-2025
*/

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User implements Comparable<User>{
    UUID id;
    String name;
    String phoneNumber;
    String emailId;

    @Override
    public int compareTo(User o) {
        return this.id.compareTo(o.id);
    }
}
