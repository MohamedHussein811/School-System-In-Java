package com.example.schoolsystem.config;
import com.example.schoolsystem.interfaces.*;
import com.example.schoolsystem.models.*;
import com.example.schoolsystem.services.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public IEnrollment enrollment() {
        return new Enrollment();
    }

    @Bean
    public ISchool school() {
        return new School();
    }

    @Bean
    public Student student(){
        return new Student();
    }

    @Bean
    public Teacher teacher(){
        return new Teacher();
    }

    @Bean
    public Course course(){
        return new Course();
    }
}
