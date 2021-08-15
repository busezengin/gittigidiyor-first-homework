package repository;
import java.util.List;
public class CrudRepository<T> {
    List<T> findAll();
    T findById(int id);
    void saveToDatabase(T object);
    void deleteFromDatabase(int id);
    void updateOnDatabase(T object, int id);
}
