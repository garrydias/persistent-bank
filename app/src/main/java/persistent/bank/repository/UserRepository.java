package persistent.bank.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistent.bank.model.entity.User;

public class UserRepository {

    public User save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();

            return user;
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
