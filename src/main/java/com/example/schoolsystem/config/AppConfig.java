package com.example.schoolsystem.config;
import com.example.schoolsystem.interfaces.*;
import com.example.schoolsystem.models.*;
import com.example.schoolsystem.services.School;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IStudent student() {
        return new Student(1L, "John Doe");
    }

    @Bean
    public ITeacher teacher() {
        return new Teacher(1L, "Jane Smith");
    }

    @Bean
    public ICourse course() {
        return new Course(1L, "Mathematics");
    }

    @Bean
    public IEnrollment enrollment() {
        return new Enrollment();
    }

    @Bean
    public ISchool school() {
        return new School();
    }
}
