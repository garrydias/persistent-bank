package persistent.bank;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StatementRepository {

    public Statement save(Statement statement) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(statement);

            session.getTransaction().commit();

            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

}
