package BDExchange.Dao;

import BDExchange.Domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserDaoTest {

    @Mock EntityManager emMock;
    @Mock EntityTransaction entityTransactionMock;
    @InjectMocks UserDao dao = new UserDao();

    @Test @DisplayName("Test if user is persisted.")
    void whenInsertIsCalledTheTransactionBeginsAndIsCommitted() {
        // given
        when(emMock.getTransaction()).thenReturn(entityTransactionMock);
        doNothing().when(entityTransactionMock).begin();
        doNothing().when(entityTransactionMock).commit();

        // when
        dao.insert(new User());

        // then
        verify(emMock).persist(isA(User.class));
        verify(emMock, atLeastOnce()).getTransaction();
        verify(entityTransactionMock).begin();
        verify(entityTransactionMock).commit();
    }

}