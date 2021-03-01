package com.example.starterdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Klass { 

    private String klassCode;
    private List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
