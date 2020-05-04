package net.thumbtack.footballgame.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    @Column(name = "has_car")
    private boolean hasCar;

    @OneToOne
    @JoinColumn(name = "role_id")
    private UserRoles role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserGame> games;


    public User(String email, String password, UserRoles role, List<UserGame> games) {
        this(email, password, false, role, null);
    }

    public User(String email, String password, boolean hasCar, UserRoles role, List<UserGame> games) {
        setEmail(email);
        setPassword(password);
        setRole(role);
        setGames(games);
        setHasCar(hasCar);
    }

    public User() {
    }

    public User(String email, String password, UserRoles role) {
        this(email, password, role, null);
    }

    public User(String email, String password) {
        this(email, password, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserGame> getGames() {
        return games;
    }

    public void setGames(List<UserGame> games) {
        this.games = games;
        if (games == null) {
            this.games = new ArrayList<>();
        }
    }

    public void addGame(UserGame game) {
        games.add(game);
    }

    public void removeGame(UserGame game) {
        games.remove(game);
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles userRole) {
        this.role = userRole;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                isHasCar() == user.isHasCar() &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRole(), user.getRole()) &&
                Objects.equals(getGames(), user.getGames());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), isHasCar(), getRole(), getGames());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", hasCar=" + hasCar +
                ", role=" + role +
                ", games=" + games +
                '}';
    }
}
