package net.thumbtack.footballgame.repository;

import net.thumbtack.footballgame.entities.AdditionalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalUserRepository extends CrudRepository<AdditionalUser, Integer> {
    AdditionalUser findByEmail(String email);
}
