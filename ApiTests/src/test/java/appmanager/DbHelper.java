package appmanager;

import database.model.SmsHistory;
import database.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DbHelper extends HelperBase {

    public DbHelper(ApplicationManager app) {
        super(app);
    }
    private final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public Users getUserByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Users result = session.createQuery(String.format("from database.model.Users where username = '%s'", name), Users.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public SmsHistory getSmsByUUID(String uuid) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        SmsHistory result = session.createQuery(String.format("from database.model.SmsHistory where external_id = '{%s}'", uuid), SmsHistory.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public List<SmsHistory> getAllSmsHistory() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<SmsHistory> result = session.createQuery("from database.model.SmsHistory", SmsHistory.class).list();

        session.getTransaction().commit();
        session.close();

        return result;
    }

}
