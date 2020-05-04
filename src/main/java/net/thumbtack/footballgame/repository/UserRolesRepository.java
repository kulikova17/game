package net.thumbtack.footballgame.repository;

import net.thumbtack.footballgame.entities.UserRole;
import net.thumbtack.footballgame.entities.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, Integer> {
    UserRoles findByRole(UserRole role);
}
