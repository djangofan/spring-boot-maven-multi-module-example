package com.example.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class StudentController {

    private static final String findStudent = "Found: %s!";
    private static final String templateAdd = "Added: %s!";
    private static final String templateUpdate = "Updated: %s!";

    @Autowired
    StudentJdbcRepository studentJdbcRepository;

    @GetMapping("/add")
    @ResponseBody
    public String addStudent(@RequestParam(name="id") Long id,
                             @RequestParam(name="name") String name,
                             @RequestParam(name="passportNumber") String passportNumber) {
        Student newStudent = new Student(id, name, passportNumber);
        studentJdbcRepository.insert(newStudent);
        return String.format(templateAdd, newStudent.getName());
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateStudent(@RequestParam(name="student") Student student) {
        studentJdbcRepository.update(student);
        return String.format(templateUpdate, student.getName());
    }

    @GetMapping("/find")
    @ResponseBody
    public String findStudent(@RequestParam(name="id") Long id) {
        Student returned = studentJdbcRepository.findById(id);
        return returned.getName();
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteStudent(@RequestParam(name="id") Long id) {
        String toBeDeleted = findStudent(id);
        studentJdbcRepository.deleteById(id);
        return String.format(findStudent, toBeDeleted);
    }

    @GetMapping("/run")
    @ResponseBody
    public void deleteStudent() {
        log.info("Student id 10001 -> {}", findStudent(10001L));
        log.info("Student id 10002 -> {}", findStudent(10002L));
        log.info("Student id 10003 -> {}", studentJdbcRepository.insert(new Student(10010L, "John", "A1234657")));
        log.info("Student id 10003 -> {}", findStudent(10010L));
        log.info("List All -> {}", findAll());
        Student updated = new Student(10001L, "Name-Updated", "Updated-Passport");
        log.info("Updated id 10001 -> {}", updateStudent(updated));
        log.info("List All -> {}", findAll());
        log.info("Deleted id 10002 -> {}", deleteStudent(10001L));
        log.info("List All -> {}", findAll());
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Student> findAll() {
        return studentJdbcRepository.findAll();
    }

}
