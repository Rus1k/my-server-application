package com.rasulov.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private String lastName;

    private String email;

    private int age;

    private String hobby;


}
