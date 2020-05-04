package net.thumbtack.footballgame.dto.responses;

import java.util.Objects;

public class GetUserDtoResponse {
    private int id;
    private String email;

    public GetUserDtoResponse() {
    }

    public GetUserDtoResponse(int id, String email) {
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
        if (!(o instanceof GetUserDtoResponse)) return false;
        GetUserDtoResponse that = (GetUserDtoResponse) o;
        return getId() == that.getId() &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
