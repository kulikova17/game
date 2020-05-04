package net.thumbtack.footballgame.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permitted_list")
public class AdditionalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    public AdditionalUser() {
    }

    public AdditionalUser(String email) {
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalUser)) return false;
        AdditionalUser that = (AdditionalUser) o;
        return getId() == that.getId() &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    @Override
    public String toString() {
        return "AdditionalUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
