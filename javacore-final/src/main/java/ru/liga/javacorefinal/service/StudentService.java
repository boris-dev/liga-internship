package ru.liga.javacorefinal.service;

import ru.liga.javacorefinal.domain.Student;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                if (student.getCourse().equals(5)) {
                    resultList.add(student.withFinished(true));
                } else {
                    resultList.add(student.withCourse(student.getCourse() + 1));
                }
            }
        }
        return resultList;
    }


    public List<String> studentsWhoJustFinishedEducation(List<Student> beforeStudents, List<Student> afterStudents) {
        return beforeStudents.stream()
                .filter(student -> containsByFio(afterStudents, student.getFio()))
                .filter(student -> !student.getFinished() && findStudentByFio(afterStudents, student.getFio()).getFinished())
                .map(Student::getFio)
                .collect(Collectors.toList());
    }

    private boolean containsByFio(List<Student> afterStudents, String fio) {
        return afterStudents.stream()
                .anyMatch(s -> s.getFio().equals(fio));
    }

    private Student findStudentByFio(List<Student> afterStudents, String fio) {
        return afterStudents.stream()
                .filter(s -> s.getFio().equals(fio))
                .findFirst()
                .orElse(null);
    }

}
