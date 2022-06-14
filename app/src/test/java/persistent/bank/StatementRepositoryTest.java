package persistent.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatementRepositoryTest {

    @DisplayName("Teste de fumaca: Deve executar a insercao de um usuario com sucesso")
    @Test
    void successfullInsert() {
        StatementRepository repository = new StatementRepository();

        Statement statement = new Statement();
        statement.setStart(System.currentTimeMillis() - 10000);
        statement.setEnd(System.currentTimeMillis());
        
        Statement persistentUser = repository.save(statement);

        Assertions.assertNotNull(persistentUser);
        Assertions.assertNotNull(persistentUser.getId());
    }
}
