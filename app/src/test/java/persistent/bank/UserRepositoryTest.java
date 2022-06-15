package persistent.bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    @DisplayName("Teste de fumaca: Deve executar a insercao de um usuario com sucesso")
    @Test
    void successfullInsert() {
        UserRepository repository = new UserRepository();

        User user = new User();
        user.setEmail("garr@bol.com");
        user.setName("garr");
        user.setPhone("1234567890");
        
        User persistentUser = repository.save(user);

        Assertions.assertNotNull(persistentUser);
        Assertions.assertNotNull(persistentUser.getId());
        Assertions.assertEquals("garr@bol.com", persistentUser.getEmail());
        Assertions.assertEquals("garr", persistentUser.getName());
        Assertions.assertEquals("1234567890", persistentUser.getPhone());
    }
}
