package com.example.starterdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School implements ISchool {
    
    private String name;
    private List<Klass> klassList;
    
    @Override
    public void ding(){
    
//        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
        
    }
    
}
