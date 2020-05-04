package net.thumbtack.footballgame.dto.responses;

public class AddUserDtoResponse {
    private int id;
    private String email;


    public AddUserDtoResponse() {
    }


    public AddUserDtoResponse(int id, String email) {
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
    public String toString() {
        return "AddUserDtoResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
