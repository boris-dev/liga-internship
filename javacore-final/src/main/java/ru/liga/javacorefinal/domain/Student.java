package ru.liga.javacorefinal.domain;


import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private final Integer course;
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

    /*public void setCourse(Integer course) {
        this.course = course;
    }*/

    public String getFio() {
        return fio;
    }

    /*public void setFio(String fio) {
        this.fio = fio;
    }*/

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /*public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }*/

    public Boolean getAllExamsPassed() {
        return allExamsPassed;
    }

   /* public void setAllExamsPassed(Boolean allExamsPassed) {
        this.allExamsPassed = allExamsPassed;
    }*/

    public Boolean getFinished() {
        return finished;
    }

    /*public void setFinished(Boolean finished) {
        this.finished = finished;
    }*/

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
