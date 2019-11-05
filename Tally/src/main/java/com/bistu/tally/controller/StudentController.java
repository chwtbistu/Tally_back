package com.bistu.tally.controller;

import com.bistu.cwt.studentmanagement.bean.ResultInfo;
import com.bistu.cwt.studentmanagement.dao.entity.StudentEntity;
import com.bistu.cwt.studentmanagement.helper.StudentBean;
import com.bistu.cwt.studentmanagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping({"/students", "/student/all"})
    public ResultInfo getAllStudents() {
        log.info("get request...");
        ResultInfo resultInfo = ResultInfo.success();
        List<StudentEntity> students = studentService.getAll();
        resultInfo.setData(students);
        log.info("students:{}", students);
        return resultInfo;
    }

    @PostMapping("/students")
    public ResultInfo createStudent(@RequestBody StudentBean bean) {
        log.info("incoming bean:{}", bean);
        if (Objects.isNull(bean) || StringUtils.isEmpty(bean.getName())) {
            log.error("Request student:{}", bean);

            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg(String.format("Request Student:%s",
                    ReflectionToStringBuilder.toString(bean, ToStringStyle.MULTI_LINE_STYLE)));
            return ResultInfo.failure();
        }
        ResultInfo resultInfo = ResultInfo.success();
        StudentEntity entity = studentService.create(bean);

        resultInfo.setData(entity);
        return resultInfo;
    }

    @PutMapping("/students")
    public ResultInfo updateStudent(@RequestBody StudentBean bean) {
        log.info("request bean:{}", bean);
        if (Objects.isNull(bean) || StringUtils.isEmpty(bean.getName())
                || Objects.isNull(bean.getId())) {
            log.error("Update Request Student:{}", bean);
            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg(String.format("Attribute missing,Request Student:%s",
                    ReflectionToStringBuilder.toString(bean, ToStringStyle.MULTI_LINE_STYLE)));
            return ResultInfo.failure();
        }

        StudentEntity entity = studentService.edit(bean);
        ResultInfo resultInfo = ResultInfo.success();
        resultInfo.setData(entity);
        return resultInfo;
    }

    @Transactional
    @DeleteMapping("/students/{sid}")
    public ResultInfo deleteStudent(@PathVariable("sid") Long id) {
        log.info("ready to delete student id:{}", id);
        if (Objects.isNull(id)) {
            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg("SID is missing");
            log.error("SID is required,....");
            return resultInfo;
        }

        boolean result = studentService.delete(id);
        if(!result) {
            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setData("Delete fail");
            log.error("Delete fail");
            return resultInfo;
        }
        else {
            ResultInfo resultInfo = ResultInfo.success();
            resultInfo.setMesg(id + " is deleted");
            log.info("Student:{} is deleted....", id);
            return resultInfo;
        }
    }

    @GetMapping("/students/{sid}")
    public ResultInfo getStudentById(@PathVariable("sid") Long id) {
        log.info("ready to get student id:{}", id);
        if (Objects.isNull(id)) {
            ResultInfo resultInfo = ResultInfo.failure();
            resultInfo.setMesg("SID is missing");
            log.error("SID is required,....");
            return resultInfo;
        }

        StudentBean bean = studentService.getOne(id);
        ResultInfo resultInfo = ResultInfo.success();
        resultInfo.setData(bean);
        return resultInfo;
    }

}
