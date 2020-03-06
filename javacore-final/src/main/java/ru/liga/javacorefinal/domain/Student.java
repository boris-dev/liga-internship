package ru.liga.javacorefinal.domain;


import lombok.experimental.Wither;

import java.time.LocalDate;
import java.util.Objects;


public class Student {
    private final Integer course;
    @Wither
    private final String fio;
    private final LocalDate birthDate;
    private final Boolean allExamsPassed;
    private final Boolean finished;

    public Student(Integer course, String fio, LocalDate birthDate, Boolean allExamsPassed, Boolean finished) {
        this.course = course;
        this.fio = fio;
        this.birthDate = birthDate;
        this.allExamsPassed = allExamsPassed;
        this.finished = finished;
    }

    public Integer getCourse() {
        return course;
    }

    public Student withFinished(Boolean finished) {
        return new Student(course, fio, birthDate, allExamsPassed, finished);
    }

    public Student withCourse(Integer course) {
        return new Student(course, fio, birthDate, allExamsPassed, finished);
    }


    public String getFio() {
        return fio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }


    public Boolean getAllExamsPassed() {
        return allExamsPassed;
    }


    public Boolean getFinished() {
        return finished;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getCourse(), student.getCourse()) &&
                Objects.equals(getFio(), student.getFio()) &&
                Objects.equals(getBirthDate(), student.getBirthDate()) &&
                Objects.equals(getAllExamsPassed(), student.getAllExamsPassed()) &&
                Objects.equals(getFinished(), student.getFinished());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getFio(), getBirthDate(), getAllExamsPassed(), getFinished());
    }
}
