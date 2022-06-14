package persistent.bank;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatementRepositoryTest {

    @DisplayName("Teste de fumaca: Deve buscar o extrato de operacoes realizadas ha 1 minuto atras")
    @Test
    void retrieveLastMinuteStatements() {

        // SETUP 
       
        User user = new User();
        user.setEmail("garr@bol.com");
        user.setName("garr");
        user.setPhone("1234567890");
        UserRepository userRepository = new UserRepository();
        userRepository.save(user);
        

        Operation operation1 = new Operation();
        operation1.setDate(System.currentTimeMillis());
        operation1.setType("SAQUE");

        Operation operation2 = new Operation();
        operation2.setDate(System.currentTimeMillis());
        operation2.setType("TRANSFERENCIA");

        OperationRepository operationRepository = new OperationRepository();
        operationRepository.save(operation1);
        operationRepository.save(operation2);
       

        // RUN
        long userDesiredStart = System.currentTimeMillis() - 60000;
        long userDesiredEnd = System.currentTimeMillis();

        List<Operation> operations = operationRepository.findOperationsBetween(userDesiredStart, userDesiredEnd);

        Statement statement = new Statement(userDesiredStart, userDesiredEnd, operations);  

        // CHECK
        Assertions.assertNotNull(statement.start());
        Assertions.assertNotNull(statement.end());
        Assertions.assertEquals(2, statement.operations().size());
    }
}
