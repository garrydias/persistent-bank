package calindra.programa.bank;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();




            Scanner scanner = new Scanner(System.in);

            RunningOption selectedOption = RunningOption.CONTINUE;

            // List<User> users = new ArrayList<>();

            String userName, userEmail, userPhone;

            while (RunningOption.CONTINUE.equals(selectedOption)) {

                System.out.printf("Nome de Usuario: ");
                userName = scanner.nextLine();

                userEmail = userName.replaceAll("\\s", "_");
                userEmail += "@calindra.com.br";
                System.out.println(MessageFormat.format("Email gerado: {0}", userEmail));

                System.out.printf("Telefone do Usuario: ");
                userPhone = scanner.nextLine();

                User user = new User();
                user.setName(userName);
                user.setEmail(userEmail);
                user.setPhone(userPhone);

                // users.add(user);
                session.save(user);

                System.out.println("Continuar cadastrando Usuarios? S/N");
                String keepInsertingAnswer = scanner.nextLine().toUpperCase();

                if (!"S".equals(keepInsertingAnswer)) {
                    selectedOption = RunningOption.FINISH;
                }
            }

            scanner.close();

            System.out.println("Bye!");



            session.getTransaction().commit();
            HibernateUtil.shutdown();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
