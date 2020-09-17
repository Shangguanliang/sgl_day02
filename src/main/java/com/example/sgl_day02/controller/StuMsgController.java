package com.example.sgl_day02.controller;

import com.example.sgl_day02.Repository.StuRepository;
import com.example.sgl_day02.model.Student;
import com.example.sgl_day02.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StuMsgController {
    @Autowired
    StuRepository stuRepository;
    @Autowired
    StudentService studentService;

    //全查询
    @GetMapping("/geek")
    public List<Student> findAll(){
        List<Student> list = stuRepository.findAll();
        return list;
    }
    //使用年龄查询

    /**
     *
     * @param age 学生年龄
     * @return
     */
    @GetMapping("/geek/age/{age}")
    public String findAge(@PathVariable Integer age) {
        return stuRepository.findByAge(age).toString();
    }

    //根据学生名称 模糊查询

    /**
     *
     * @param name 学生名字
     * @return
     */
    @GetMapping("/geek/name/{name}")
    public List<Student> findName(@PathVariable String name) {
        return stuRepository.findByNameIsLike("%" + name + "%");
    }

    //查询所有学生 按照年龄降序

    /**
     * 查询所有学生 并按照年龄的降序进行排序
     * @return
     */
    @GetMapping("/geek/age/desc")
    public List<Student> findStuAgeDesc() {
        return stuRepository.findAllByOrderByAgeDesc();
    }


    /**
     * 用以统计所有学生人数
     * @return
     */
    @GetMapping("/geek/count")
    public String countAll() {
        return "学生总人数为："+stuRepository.count();
    }

    //根据学生的`班级`查询，并按照年龄降序

    /**
     *通过班级名称查询，并按照年龄降序排序
     * @param className 班级名称
     * @return
     */
    @GetMapping("/geek/classname/{className}")
    public List<Student> findClassName(@PathVariable String className) {
        return stuRepository.findByClassNameOrderByAgeDesc(className);
    }

    //根据学生的`年龄`进行`范围`查询

    /**
     *
     * @param min 最小年龄
     * @param max 最大年龄
     * @return
     */
    @GetMapping("/geek/age/min={min}/max={max}")
    public List<Student> findAgeBetween(@PathVariable Integer min, @PathVariable Integer max) {
        return stuRepository.findByAgeBetween(min, max);
    }

    //实现分页

    /**
     *
     * @param pageable 页码
     * page:从前端发送的页码 size:一页显示两条数据
     *
     * @return
     */
    @GetMapping("/geek/age/page")
    public Page<Student> page(@PageableDefault(page = 1, size = 2) Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        return stuRepository.findAll(pageRequest);

    }

    //根据学生`年龄`比较查询和`名称`、`班级`的模糊查询，并进行分页。

    /**
     *
     * @param pageable 页数 page:从前端发送的页码 size:一页显示两条数据
     * @param age 年龄
     * @param keyword 名称和班级
     * @return
     */
    @GetMapping("/geek/age/pageQuery")
    public Page<Student> queryList(@PageableDefault(page = 1, size = 2) Pageable pageable,
                                   @RequestParam(required = false) Integer age, @RequestParam(required = false) String keyword) {
        //获取页数和页面的数据
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        //查询条件在该对象中
        Specification<Student> spec = studentService.getSpec(age,keyword);

        return stuRepository.findAll(spec,pageRequest);

    }
}
