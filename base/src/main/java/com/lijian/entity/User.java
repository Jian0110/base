package com.lijian.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString()
@Data
public class User {
    private String id;
    private String name;
}
