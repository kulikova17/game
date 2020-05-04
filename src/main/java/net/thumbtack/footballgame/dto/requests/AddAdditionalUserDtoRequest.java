package net.thumbtack.footballgame.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddAdditionalUserDtoRequest {
    @NotNull
    @Email
    private String email;

    public AddAdditionalUserDtoRequest() {
    }

    public AddAdditionalUserDtoRequest(@NotNull @Email String email) {
        setEmail(email);
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
        if (!(o instanceof AddAdditionalUserDtoRequest)) return false;
        AddAdditionalUserDtoRequest that = (AddAdditionalUserDtoRequest) o;
        return Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "AddAdditionalUserRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
