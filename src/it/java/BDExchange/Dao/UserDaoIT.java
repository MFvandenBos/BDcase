package BDExchange.Dao;

import BDExchange.Domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoIT {
    EntityManager em = Persistence.createEntityManagerFactory("IntegrationTestUnit").createEntityManager();
    UserDao userDao = new UserDao(em);

    @Test @DisplayName("Add new row when user is added.")
    void whenNewUserIsInsertedANewRowIsAddedToTheDatabase() {
        // Given
        List<User> userList = userDao.getAll();
        int numberBeforeInsert;
        int numberAfterInsert;

        // When
        numberBeforeInsert = userList.size();
        userDao.insert(new User());
        userList = userDao.getAll();
        numberAfterInsert = userList.size();

        // Then
        assertThat(numberAfterInsert > numberBeforeInsert);
    }

    @Test @DisplayName("")
    void whenNewUserIsInsertedANewIdIsAdded() {
        userDao.insert(new User());
        assertThat(userDao.getAll()).allMatch(e -> e.getId() != 0);
    }
}
