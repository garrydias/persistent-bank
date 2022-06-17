package persistent.bank.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import persistent.bank.model.entity.Operation;
public class OperationRepository {
    public Operation save(Operation operation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(operation);

            session.getTransaction().commit();

            return operation;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public List<Operation> findOperationsBetween(long start, long end) {
       
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query query = session.createQuery("SELECT o FROM Operation o WHERE date BETWEEN ?1 AND ?2");
            query.setParameter(1, start);
            query.setParameter(2, end);

            List<Operation> operations = query.list();

            session.getTransaction().commit();

            return operations;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

}
