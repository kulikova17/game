package net.thumbtack.footballgame.dto.responses;

import java.util.Objects;

public class AddAdditionalUserDtoResponse {
    private int id;
    private String email;

    public AddAdditionalUserDtoResponse() {
    }

    public AddAdditionalUserDtoResponse(int id, String email) {
        setEmail(email);
        setId(id);
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
        if (!(o instanceof AddAdditionalUserDtoResponse)) return false;
        AddAdditionalUserDtoResponse that = (AddAdditionalUserDtoResponse) o;
        return getId() == that.getId() &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }

    @Override
    public String toString() {
        return "AddAdditionalUserResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
