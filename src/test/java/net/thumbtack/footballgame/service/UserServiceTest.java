package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.FootballGameServer;
import net.thumbtack.footballgame.entities.User;
import net.thumbtack.footballgame.entities.UserRole;
import net.thumbtack.footballgame.entities.UserRoles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = { FootballGameServer.class })
@Transactional
public class UserServiceTest extends BaseServiceTest {
    @Test
    public void addUser() {
        User user = saveUser("user1", "passwd",null, null);
        User userFromDB = userService.findByEmail(user.getEmail());
        assertEquals(userFromDB.getEmail(), user.getEmail());
        assertEquals(userFromDB.getPassword(), user.getPassword());
        assertEquals(userFromDB.getRole(), user.getRole());
    }


    @Test (expected = DataIntegrityViolationException.class)
    public void addExistUser() {
        saveUser("user1", "passwd",null, null);
        saveUser("user1", "passwd2", null, null);
    }

    @Test
    public void findByLogin() {
        User userFromDB = userService.findByEmail("user1");
        assertNull(userFromDB);
    }

    @Test
    public void getAll() {
        List<User> usersFromDB = userService.findAll();

        saveUser("user1", "passwd", null, null);
        List<User> usersFromDB2 = userService.findAll();

        assertNotNull(usersFromDB2);
        assertEquals(usersFromDB.size() + 1, usersFromDB2.size());
    }

    @Test
    public void updateUser() {
        User user = saveUser("user1", "passwd", null, null);

        user.setPassword("new_password");
        User updatedUser = userService.update(user);
        User userFromDB = userService.findByEmail(user.getEmail());

        assertEquals(userFromDB.getEmail(), updatedUser.getEmail());
        assertEquals(userFromDB.getPassword(), updatedUser.getPassword());
        assertEquals(userFromDB.getRole(), updatedUser.getRole());
    }

    @Test
    public void addUserWithRole() {
        UserRoles roles = new UserRoles(UserRole.ROLE_USER);

        User user = saveUser("user1", "passwd", roles, null);
        User userFromDB = userService.findByEmail(user.getEmail());

        assertEquals(userFromDB.getEmail(), user.getEmail());
        assertEquals(userFromDB.getPassword(), user.getPassword());
        assertEquals(userFromDB.getRole(), user.getRole());

        assertEquals(roles, userFromDB.getRole());
        assertNotEquals(0, userFromDB.getRole().getId());
    }


}