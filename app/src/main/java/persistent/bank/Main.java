package persistent.bank;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Referencia: https://dzone.com/articles/hibernate-5-java-configuration-example
 * Plugin: MySQL Manager - Weijan Chen
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        RunningOption selectedOption = RunningOption.CONTINUE;

        UserRepository userRepository = new UserRepository();
        OperationRepository operationRepository = new OperationRepository();

        String userName, userEmail, userPhone;

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

        userRepository.save(user);

        while (RunningOption.CONTINUE.equals(selectedOption)) {

            System.out.printf("Digite o tipo da operacao (SAQUE, TRANSFERENCIA, DEPOSITO): ");
            String type = scanner.nextLine();

            System.out.printf("Digite o valor em centavos: ");
            String value = scanner.nextLine();

            long date = System.currentTimeMillis();

            Operation operation = new Operation();
            operation.setType(type);
            operation.setDate(date);
            operation.setValue(value);

            operationRepository.save(operation);

            System.out.println("Deseja realizar outra operação? S/N");
            String keepInsertingAnswer = scanner.nextLine().toUpperCase();

            if (!"S".equals(keepInsertingAnswer)) {
                selectedOption = RunningOption.FINISH;
            }
        }


        System.out.print("Deseja obter o extrato dos ultimos 10/20/30 segundos: ");
        int secondsAgo = scanner.nextInt();
        long start = System.currentTimeMillis() - (secondsAgo * 1000);
        long end = System.currentTimeMillis();

        List<Operation> operations = operationRepository.findOperationsBetween(start, end);

        Statement statement = new Statement(start, end, operations);

        System.out.printf("\n\n%s\n", statement.toString());

        System.out.println("Bye!");
        
        scanner.nextLine();

        HibernateUtil.shutdown();

        scanner.close();

        System.exit(0);
    }
}
