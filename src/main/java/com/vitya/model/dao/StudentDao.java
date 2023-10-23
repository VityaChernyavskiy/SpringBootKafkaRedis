package com.vitya.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@RedisHash("StudentDao")
public class StudentDao implements Serializable {

    private String id;

    private String fullName;

    private Double averageGrade;

}
