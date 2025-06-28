package com.yang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private int sid;
    private String id;
    private String name;
    private int C;
    private int JAVA;
    private int Python;
}
