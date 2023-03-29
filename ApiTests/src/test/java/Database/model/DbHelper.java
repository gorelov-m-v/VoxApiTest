package Database.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public Users getUserByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Users result = (Users) session.createQuery(String.format("from Users where username = '%s'", name)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

}