package com.vitya.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDto {

    private String name;

    private String surname;

    private List<Double> grades;

    public String getFullName(){
        return name + " " + surname;
    }
}
