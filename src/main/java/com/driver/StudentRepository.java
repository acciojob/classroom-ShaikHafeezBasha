package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student>studentMap;
    private HashMap<String,Teacher>teacherMap;
    private HashMap<String,List<String>>studentTeachermapping;

    public StudentRepository() {
        this.studentMap=new HashMap<String,Student>();
        this.teacherMap=new HashMap<String,Teacher>();
        this.studentTeachermapping=new HashMap<String,List<String>>();
    }

    public void saveStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            studentMap.put(student,studentMap.get(student));
            teacherMap.put(teacher,teacherMap.get(teacher));
            List<String>stu=new ArrayList<String>();
            if(studentTeachermapping.containsKey(teacher)){
                stu=studentTeachermapping.get(teacher);
            }
            stu.add(student);
            studentTeachermapping.put(teacher,stu);
        }
    }

    public Student findStudent(String name) {
        return studentMap.get(name);
    }

    public Teacher findTeacher(String name) {
        return teacherMap.get(name);
    }

    public List<String> findStudentByTeacher(String teacher) {
        List<String>stuList=new ArrayList<String>();
        if(studentTeachermapping.containsKey(teacher)){
            stuList=studentTeachermapping.get(teacher);
        }
        return stuList;
    }

    public List<String> findAllStudents() {
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher) {
        List<String>students=new ArrayList<String>();
        if(studentTeachermapping.containsKey(teacher)){
            students=studentTeachermapping.get(teacher);
            for(String student:students){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }
            studentTeachermapping.remove(teacher);
        }
        if(teacherMap.containsKey(teacher)){
            teacherMap.remove(teacher);
        }
    }
    public void deleteAllTeachers() {
        HashSet<String>stuSet=new HashSet<String>();

        for(String teacher:studentTeachermapping.keySet()){
            for(String student:studentTeachermapping.get(teacher)){
                stuSet.add(student);
            }
        }

        for(String student:stuSet){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }
    }
}
