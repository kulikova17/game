package net.thumbtack.footballgame.dto.responses;

public class AddGameDtoResponse {
    private int id;
    private String type;
    private String status;

    public AddGameDtoResponse(Integer id, String type, String status) {
        setId(id);
        setStatus(status);
        setType(type);
    }

    public AddGameDtoResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
