package net.thumbtack.footballgame.dto.responses;

import java.util.Objects;

public class AddGameTypeDtoResponse {
    private int id;
    private String type;

    public AddGameTypeDtoResponse() {
    }

    public AddGameTypeDtoResponse(int id, String type) {
        setId(id);
        setType(type);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddGameTypeDtoResponse)) return false;
        AddGameTypeDtoResponse that = (AddGameTypeDtoResponse) o;
        return getId() == that.getId() &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }

    @Override
    public String toString() {
        return "AddGameTypeDtoResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
