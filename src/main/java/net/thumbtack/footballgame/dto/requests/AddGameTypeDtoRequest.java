package net.thumbtack.footballgame.dto.requests;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddGameTypeDtoRequest {
    @NotNull
    private String type;

    public AddGameTypeDtoRequest(String type) {
        setType(type);
    }

    public AddGameTypeDtoRequest() {
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
        if (!(o instanceof AddGameTypeDtoRequest)) return false;
        AddGameTypeDtoRequest that = (AddGameTypeDtoRequest) o;
        return Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    @Override
    public String toString() {
        return "AddGameTypeDtoRequest{" +
                "type='" + type + '\'' +
                '}';
    }
}
