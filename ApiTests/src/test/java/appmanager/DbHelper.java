package appmanager;

import database.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbHelper extends HelperBase {

    public DbHelper(ApplicationManager app) {
        super(app);
    }
    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public Users getUserByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Users result = (Users) session.createQuery(String.format("from database.model.Users where username = '%s'", name)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

}
