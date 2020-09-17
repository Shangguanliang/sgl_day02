package com.example.sgl_day02.Repository;

import com.example.sgl_day02.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用JPA进行基于方法名称命名规则查询
 * 标注为Repository
 */
@Repository
public interface StuRepository extends JpaRepository<Student,Integer>, JpaSpecificationExecutor<Student> {
    //根据年龄查询
    public List<Student> findByAge(Integer age);

    //根据名称 模糊查询
    public List<Student> findByNameIsLike(String name);

    //根据 年龄 查询所有学生 按照年龄降序
    public List<Student> findAllByOrderByAgeDesc();

    //根据学生班级查询 并按照年龄降序
    @Query(value = "select * from student where class_name = ?1 order by age desc",nativeQuery = true)
    public List<Student> findByClassNameOrderByAgeDesc(String classname);

    //根据学生的`年龄`进行`范围`查询
    @Query(value = "select * from student where age between ?1 and ?2",nativeQuery = true)
    public List<Student> findByAgeBetween(Integer min,Integer max);
}
