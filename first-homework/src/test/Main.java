package test;

import models.*;
import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        saveTestData();
    }
    private static void saveTestData() {
        Student student1 = new Student("Buse Zengin", LocalDate.of(1998, Month.SEPTEMBER,11),"İstanbul", "F");
        Student student2 = new Student("Emre KILIÇ", LocalDate.of(1996, Month.NOVEMBER,24),"Ankara", "M");
        Student student3 = new Student("Büşra ŞAHİN", LocalDate.of(1996,Month.OCTOBER,8),"İzmir","F");

        Instructor instructor1 = new PermanentInstructor("Ali Yılmaz","İstanbul","05314562356",150.0);
        Instructor instructor2 = new VisitingResearcher("Veli Oflazoğlu","Ankara","05387566372",170.0);

        Course course1 = new Course("Java Spring Boot", "JAVA101",5);
        Course course2 = new Course("Python", "PYTHON101",4);
        Course course3 = new Course("Android", "ANDROID101",3);

        course1.setInstructor(instructor1);
        course2.setInstructor(instructor1);
        course3.setInstructor(instructor2);

        student1.getCourseList().add(course1);
        student2.getCourseList().add(course1);
        student2.getCourseList().add(course2);
        student3.getCourseList().add(course3);

        instructor1.getCourseList().add(course1);
        instructor1.getCourseList().add(course2);
        instructor2.getCourseList().add(course3);

        course1.getStudentList().add(student1);
        course1.getStudentList().add(student2);
        course2.getStudentList().add(student2);
        course3.getStudentList().add(student3);

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();

            em.persist(course1);
            em.persist(course2);
            em.persist(course3);
            em.persist(instructor1);
            em.persist(instructor2);
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);



            em.getTransaction().commit();

            System.out.println("All data persisted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

}
