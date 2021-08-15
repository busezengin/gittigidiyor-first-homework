package sevice;

import models.Student;
import utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentService {


    public class StudentService implements CrudRepository<Student> {


        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        @Override
        public List<Student> findAll() {

            return em.createQuery("select s from Student s",Student.class).getResultList();
        }


        @Override
        public Student findById(int id) {

            return em.find(Student.class,id);
        }

        @Override
        public void saveToDatabase(Student student) {
            try{
                em.getTransaction().begin();
                em.persist(student);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }

        }

        @Override
        public void deleteFromDatabase(int id) {
            try{
                em.getTransaction().begin();
                Student student= em.createQuery("from Student s where s.id =:id ",Student.class).setParameter("id",id).getSingleResult();
                em.remove(student);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }

        }

        @Override
        public void updateOnDatabase(Student student, int id) {
            try{

                em.getTransaction().begin();
                Student student1= em.createQuery("from Student s where s.id =:id ",Student.class).setParameter("id",id).getSingleResult();
                student1.setAddress(student.getAddress());
                student1.setName(student.getName());
                student1.setBirthDate(student.getBirthDate());
                student1.setGender(student.getGender());
                em.persist(student1);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }

        }
    }
}
