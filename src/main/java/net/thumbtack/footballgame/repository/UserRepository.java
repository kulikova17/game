package net.thumbtack.footballgame.repository;

import net.thumbtack.footballgame.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String login);

    void deleteByEmail(String email);

    List<User> findAllByOrderByEmailAsc();
}
