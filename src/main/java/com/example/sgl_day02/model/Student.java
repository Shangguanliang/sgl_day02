package com.example.sgl_day02.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 实体类
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    @Id
    @GeneratedValue
    private Integer id;//主键
    @Column
    private String name;//姓名
    @Column
    private Integer age;//年龄
    @Column
    private String className;//班级

}
