package net.thumbtack.footballgame.dto.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AddUserDtoRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

    private boolean hasCar;

    public AddUserDtoRequest() {
    }

    public AddUserDtoRequest(String email, String password) {
        this(email, password, false);
    }

    public AddUserDtoRequest(@NotNull @Email String email, @NotNull String password, boolean hasCar) {
        setEmail(email);
        setPassword(password);
        setHasCar(hasCar);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }
}
