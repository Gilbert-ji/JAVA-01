package com.example.starterdemo;

import com.example.starterdemo.bean.Klass;
import com.example.starterdemo.bean.School;
import com.example.starterdemo.bean.Student;
import com.example.starterdemo.properties.MyStartProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(MyStartProperties.class)
@ConditionalOnClass({Student.class, Klass.class, School.class})
public class StarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterDemoApplication.class, args);
    }

    @Autowired
    private MyStartProperties properties;

    @Bean
    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
    public Student getStudent(){
        return new Student(properties.getStId(), properties.getStName());
    }

    @Bean
    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
    public Klass getKlass(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(properties.getStId(), properties.getStName()));
        return new Klass(properties.getKlCode(), students);
    }

    @Bean
    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
    public School getSchool(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(properties.getStId(), properties.getStName()));
        List<Klass> klasses = new ArrayList<>();
        klasses.add(new Klass(properties.getKlCode(), students));
        return new School(properties.getScName(), klasses);
    }
}
