package hu.ulyssys.java.course.database.hibernate;

import hu.ulyssys.java.course.database.hibernate.entity.AbstractPerson;
import hu.ulyssys.java.course.database.hibernate.entity.Student;
import hu.ulyssys.java.course.database.hibernate.entity.Teacher;
import org.hibernate.Session;

import java.util.List;

public class PersonMain {

    public static void main(String[] args) {
        Student student = new Student();
        student.setFriendsNumber(2);
        student.setFirstName("alma");
        student.setLastName("körte");
        insert(student);

        Teacher teacher = new Teacher();
        teacher.setCoursesNumber(5);
        teacher.setFirstName("Tanci");
        teacher.setLastName("Bácsi");
        insert(teacher);

        Teacher persistedTeacher = findTeacherById(teacher.getId());
        System.out.println("Perzisztált teacher: " + persistedTeacher.getId());
        /** findAll(Student.class).forEach(abstractPerson -> {
         c        });
         findAll(Teacher.class).forEach(abstractPerson -> {
         System.out.println("Student: " + abstractPerson.getFirstName() + " " + abstractPerson.getLastName());
         });**/

        //  findAllStudent().forEach(student1 -> System.out.println(student1.getId() + ". Student: " + student1.getFirstName() + " " + student1.getLastName()));
       /* for (Student student43 : findAllStudent()) {
            System.out.println(student.getId() + ". Student: " + student.getFirstName() + " " + student.getLastName());

        }*/

        findAllStudent().forEach(PersonMain::writeOut);

        findAllTeacher().forEach(teacher1 -> System.out.println(teacher1.getId() + ". Teacher: " + teacher1.getFirstName() + " " + teacher1.getLastName()));


    }

    private static Student findStudentById(Long id) {
        return DatabaseSessionProvider.getInstance().getSessionObj().find(Student.class, id);
    }

    private static Teacher findTeacherById(Long id) {
        return DatabaseSessionProvider.getInstance().getSessionObj().find(Teacher.class, id);
    }

    private static void writeOut(Student student) {
        System.out.println(student.getId() + ". Student: " + student.getFirstName() + " " + student.getLastName());
    }

    private static List<Student> findAllStudent() {
        return DatabaseSessionProvider.getInstance().getSessionObj().createQuery("select n from Student n ", Student.class).getResultList();
    }

    private static List<Teacher> findAllTeacher() {
        return DatabaseSessionProvider.getInstance().getSessionObj().createQuery("select n from Teacher n ", Teacher.class).getResultList();
    }

    private static List<AbstractPerson> findAll(Class clazz) {
        return DatabaseSessionProvider.getInstance().getSessionObj().createQuery("select n from " + clazz.getSimpleName() + " n ", clazz).getResultList();
    }

    private static Long insert(Teacher teacher) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
        return teacher.getId();

    }

    private static Long insert(Student student) {
        Session session = DatabaseSessionProvider.getInstance().getSessionObj();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        return student.getId();

    }
}
