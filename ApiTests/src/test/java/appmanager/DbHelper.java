package appmanager;

import database.model.Accounts;
import database.model.SmsHistory;
import database.model.Users;
import http.model.accounts.add.AddAccountDataSet;
import http.model.accounts.add.AddAccountResponse;
import http.model.sms.received.ReceivedSmsMQDataSet;
import http.model.sms.send.SendSmsMessageResponse;
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

    public Users getUserByName(AddAccountDataSet addAccountDataSet) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Users result = session.createQuery(String.format("from database.model.Users where username = '%s'", addAccountDataSet.accountName().toLowerCase()), Users.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public SmsHistory getSmsByUUID(ReceivedSmsMQDataSet receivedSmsMQDataSet) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        SmsHistory result = session.createQuery(String.format("from database.model.SmsHistory where external_id = '{%s}'", receivedSmsMQDataSet.getUuid()), SmsHistory.class).uniqueResult();

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

    public SmsHistory getSms(SendSmsMessageResponse sendSmsMessageResponse) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

       SmsHistory result = session.createQuery(String.format("from database.model.SmsHistory where id = '%s'",
                sendSmsMessageResponse.getMessageId()), SmsHistory.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public Accounts getBillingInfo(AddAccountResponse addAccountResponse) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Accounts result = session.createQuery(String.format("from database.model.Accounts where id = '%s'",
                addAccountResponse.billing_account_id()), Accounts.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

}
