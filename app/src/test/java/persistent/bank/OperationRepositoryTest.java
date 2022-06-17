package persistent.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import persistent.bank.model.entity.Operation;
import persistent.bank.repository.OperationRepository;

public class OperationRepositoryTest {

    @DisplayName("Teste de fumaca: Deve executar a insercao de uma operacao com sucesso")
    @Test
    void successfullInsert() {
        OperationRepository repository = new OperationRepository();

        Operation operation = new Operation();
        operation.setDate(System.currentTimeMillis());
        operation.setType("SAQUE");
        operation.setValue("8000");

        Operation persistentOperation = repository.save(operation);
        Assertions.assertNotNull(persistentOperation);
        Assertions.assertNotNull(persistentOperation.getId());
        Assertions.assertEquals("SAQUE", persistentOperation.getType());
        Assertions.assertEquals("8000", persistentOperation.getValue());
        Assertions.assertTrue(persistentOperation.getDate() > 0);
    }

}
