package ru.liga;

import org.junit.Test;
import ru.liga.javacorefinal.domain.Student;
import ru.liga.javacorefinal.service.StudentService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
public class StudentServiceTest {


    @Test
    public void transferStudentsToNextCourseTest() {
        List<Student> students = Arrays.asList(
                new Student(1, "Афанасьев Александр Григорьевич", LocalDate.of(1998, Month.APRIL, 12), true, false),
                new Student(2, "Архангельский Иван Дмитриевич", LocalDate.of(1990, Month.JANUARY, 14), false, false),
                new Student(5, "Булатов Алексей Андреевич", LocalDate.of(1995, Month.MAY, 2), true, false)
        );
        List<Student> result = new StudentService().transferStudentsToNextCourse(students);
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(
                new Student(2, "Афанасьев Александр Григорьевич", LocalDate.of(1998, Month.APRIL, 12), true, false),
                new Student(5, "Булатов Алексей Андреевич", LocalDate.of(1995, Month.MAY, 2), true, true)
        );
    }

    /**
     *
     */
    @Test
    public void studentsWhoJustFinishedEducationTest() {
        List<Student> beforeStudents = Arrays.asList(
                new Student(1, "Афанасьев Александр Григорьевич", LocalDate.of(1998, Month.APRIL, 12), true, false),
                new Student(2, "Архангельский Иван Дмитриевич", LocalDate.of(1990, Month.JANUARY, 14), false, false),
                new Student(5, "Булатов Алексей Андреевич", LocalDate.of(1995, Month.MAY, 2), true, false)
        );
        List<Student> afterStudents = new StudentService().transferStudentsToNextCourse(beforeStudents);

        List<String> studentsFioWhoFinishedEducation = new StudentService().studentsWhoJustFinishedEducation(beforeStudents, afterStudents);
        assertThat(studentsFioWhoFinishedEducation).containsExactly("Булатов Алексей Андреевич");
    }


}
