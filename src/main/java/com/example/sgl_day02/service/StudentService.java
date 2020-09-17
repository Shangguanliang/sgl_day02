package com.example.sgl_day02.service;

import com.example.sgl_day02.model.Student;
import org.springframework.data.jpa.domain.Specification;
//接口
public interface StudentService {
    public  Specification<Student> getSpec(Integer age,String keyword);
}
