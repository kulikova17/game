package net.thumbtack.footballgame.dto.requests;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddGameDtoRequest {
    @NotNull
    private int typeId;

    public AddGameDtoRequest() {
    }

    public AddGameDtoRequest(int typeId) {
        setTypeId(typeId);
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddGameDtoRequest)) return false;
        AddGameDtoRequest that = (AddGameDtoRequest) o;
        return getTypeId() == that.getTypeId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTypeId());
    }

    @Override
    public String toString() {
        return "AddGameDtoRequest{" +
                "typeId=" + typeId +
                '}';
    }
}
