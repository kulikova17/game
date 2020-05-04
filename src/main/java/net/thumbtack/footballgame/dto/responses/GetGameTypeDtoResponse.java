package net.thumbtack.footballgame.dto.responses;

import java.util.Objects;

public class GetGameTypeDtoResponse {
    private int id;
    private String type;

    public GetGameTypeDtoResponse() {
    }


    public GetGameTypeDtoResponse(int id, String type) {
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
        if (!(o instanceof GetGameTypeDtoResponse)) return false;
        GetGameTypeDtoResponse that = (GetGameTypeDtoResponse) o;
        return getId() == that.getId() &&
                Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }

    @Override
    public String toString() {
        return "GetGameTypeDtoResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
