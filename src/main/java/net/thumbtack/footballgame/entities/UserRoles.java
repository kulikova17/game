package net.thumbtack.footballgame.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class UserRoles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRoles() {
    }

    public UserRoles(UserRole role) {
        setRole(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoles)) return false;
        UserRoles userRoles = (UserRoles) o;
        return getRole() == userRoles.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
