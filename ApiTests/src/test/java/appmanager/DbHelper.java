package appmanager;

import model.database.Accounts;
import model.database.DeferredTransactions;
import model.database.SmsHistory;
import model.database.Users;
import model.http.accounts.add.AddAccountDataSet;
import model.http.accounts.add.AddAccountResponse;
import model.http.sms.received.ReceivedSmsMQDataSet;
import model.http.sms.send.SendSmsMessageResponse;
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
        Users result = session.createQuery(String.format("from model.database.Users where username = '%s'", addAccountDataSet.accountName().toLowerCase()), Users.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public SmsHistory getSmsByUUID(ReceivedSmsMQDataSet receivedSmsMQDataSet) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        SmsHistory result = session.createQuery(String.format("from model.database.SmsHistory where external_id = '{%s}'", receivedSmsMQDataSet.getUuid()), SmsHistory.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public List<SmsHistory> getAllSmsHistory() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<SmsHistory> result = session.createQuery("from model.database.SmsHistory", SmsHistory.class).list();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public SmsHistory getSms(SendSmsMessageResponse sendSmsMessageResponse) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

       SmsHistory result = session.createQuery(String.format("from model.database.SmsHistory where id = '%s'",
                sendSmsMessageResponse.getMessageId()), SmsHistory.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public Accounts getBillingInfo(AddAccountResponse addAccountResponse) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Accounts result = session.createQuery(String.format("from model.database.Accounts where id = '%s'",
                addAccountResponse.billing_account_id()), Accounts.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public List<SmsHistory> getAllSmsId() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<SmsHistory> result = session.createQuery("select id from model.database.SmsHistory", SmsHistory.class).list();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public DeferredTransactions getDeferredTransactions(SmsHistory smsHistory) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        DeferredTransactions result = session.createQuery(String.format
                ("from model.database.DeferredTransactions where transaction_id = '%s'" ,
                        smsHistory.getDeferred_transaction_id()), DeferredTransactions.class).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return result;
    }
}
