package ru.liga.javacorefinal.service;

import ru.liga.javacorefinal.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    /**
     * Переводит студентов сдавших экзамен на следующий курс, если студент на 5ом курсе то завершает его обучение
     *
     * @param students студенты, ожидающие перевода
     * @return студенты которых удалось перевести на следующий курс или окончить обучение
     */
    public List<Student> transferStudentsToNextCourse(List<Student> students) {
        List<Student> resultList = new ArrayList<>();
        for (Student student : students) {

            if (student.getAllExamsPassed()) {
                Student st;
                if (student.getCourse().equals(5)) {
                    st=new Student(student.getCourse(), student.getFio(), student.getBirthDate(), student.getAllExamsPassed(), true);

                } else {
                    st=new Student(student.getCourse() + 1, student.getFio(), student.getBirthDate(), student.getAllExamsPassed(), false);

                }
                resultList.add(st);
            }
        }
        return resultList;
    }

}
