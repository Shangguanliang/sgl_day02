package com.example.sgl_day02.service.Impl;

import com.example.sgl_day02.model.Student;
import com.example.sgl_day02.service.StudentService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public Specification<Student> getSpec(Integer age, String keyword) {

        return new Specification<Student>() {
            //重写toPredicate()
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                //进行年龄的比较查询
                if (age != null) {
                    predicate = cb.equal(root.get("age"), age);
                }
                //进行名称和模糊查询
                if (keyword != null) {
                    String key = "%" + keyword + "%";
                    predicate = cb.and(predicate, cb.or(
                            cb.like(root.get("name"), key),
                            cb.like(root.get("className"), key)));
                }
                return predicate;
            }
        };
    }

}
