package net.thumbtack.footballgame.service;

import net.thumbtack.footballgame.entities.User;
import net.thumbtack.footballgame.entities.UserRole;
import net.thumbtack.footballgame.entities.UserRoles;
import net.thumbtack.footballgame.repository.UserRepository;
import net.thumbtack.footballgame.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserRepository repository;
    private UserRolesRepository rolesRepository;
    @Autowired
    public UserService(UserRepository repository, UserRolesRepository rolesRepository) {
        this.repository = repository;
        this.rolesRepository = rolesRepository;
    }

    public User save(User user) {
        LOGGER.debug("Insert user {}", user);
        if (user.getRole() == null) {
            user.setRole(new UserRoles(UserRole.ROLE_USER));
        }
        UserRoles roles = rolesRepository.findByRole(user.getRole().getRole());
        if (roles == null) {
            roles = rolesRepository.save(user.getRole());
            System.out.println(roles);
        }
        user.setRole(roles);

        User savedUser;
        try {
            savedUser = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Can`t insert user {}, {}", user, e);
            throw e;
        }

        return savedUser;
    }

    public User update(User user) {
        LOGGER.debug("Update user {}", user);
        return save(user);
    }

    public User findByEmail(String email) {
        LOGGER.debug("Find user by email {}", email);
        return repository.findByEmail(email);
    }

    public User findById(Integer id) {
        LOGGER.debug("Find user by id {}", id);
        return repository.findById(id).orElse(null);
    }


    public List<User> findAll() {
        LOGGER.debug("Find all users");
        return repository.findAll();
    }

    public List<User> findAllSortedByEmail() {
        return repository.findAllByOrderByEmailAsc();
    }


    public void deleteByEmail(String email) {
        LOGGER.debug("Delete user by email {}", email);
        repository.deleteByEmail(email);
    }
    public void deleteById(Integer id) {
        LOGGER.debug("Delete user by id {}", id);
        repository.deleteById(id);
    }




    public void clear() {
        LOGGER.debug("Delete all users");
        repository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: " +  email);
        }
        return getUserDetails(user);
    }

    private UserDetails getUserDetails(User user) {
        Set<GrantedAuthority> roles = new HashSet();
        if (user.getRole() != null) {
            roles.add(new SimpleGrantedAuthority(user.getRole().getRole().name()));
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword().toLowerCase(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                roles);
    }
}
