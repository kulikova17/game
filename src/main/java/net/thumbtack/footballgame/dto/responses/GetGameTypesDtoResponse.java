package net.thumbtack.footballgame.dto.responses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GetGameTypesDtoResponse {
    private List<GetGameTypeDtoResponse> list;

    public GetGameTypesDtoResponse() {
        this(new ArrayList<>());
    }

    public GetGameTypesDtoResponse(List<GetGameTypeDtoResponse> list) {
        setList(list);
    }

    public GetGameTypesDtoResponse(GetGameTypeDtoResponse... responses) {
        list = Arrays.asList(responses);
    }

    public List<GetGameTypeDtoResponse> getList() {
        return list;
    }

    public void setList(List<GetGameTypeDtoResponse> list) {
        this.list = list;
        if(list == null) {
            this.list = new ArrayList<>();
        }
    }

    public void addType(GetGameTypeDtoResponse type) {
        list.add(type);
    }

    public void removeType(GetGameTypeDtoResponse type) {
        list.remove(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetGameTypesDtoResponse)) return false;
        GetGameTypesDtoResponse response = (GetGameTypesDtoResponse) o;
        return Objects.equals(getList(), response.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getList());
    }

    @Override
    public String toString() {
        return "GetGameTypesDtoResponse{" +
                "list=" + list +
                '}';
    }
}
