package persistent.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OperationRepositoryTest {

    @DisplayName("Teste de fumaca: Deve executar a insercao de uma operacao com sucesso")
    @Test
    void successfullInsert() {
        OperationRepository repository = new OperationRepository();
        Operation persistentOperation = repository.save(new Operation());
        Assertions.assertNotNull(persistentOperation);
        Assertions.assertNotNull(persistentOperation.getId());
    }

}
