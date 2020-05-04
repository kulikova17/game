package net.thumbtack.footballgame.dto.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAdditionalUsersResponse {
    private List<GetAdditionalUserResponse> list;

    public GetAdditionalUsersResponse() {
        list = new ArrayList<>();
    }

    public GetAdditionalUsersResponse(List<GetAdditionalUserResponse> list) {
        setList(list);
    }

    public List<GetAdditionalUserResponse> getList() {
        return list;
    }

    public void setList(List<GetAdditionalUserResponse> list) {
        this.list = list;
        if(list == null) {
            this.list = new ArrayList<>();
        }
    }

    public void addUser(GetAdditionalUserResponse user) {
        this.list.add(user);
    }

    public void removeUser(GetAdditionalUserResponse user) {
        this.list.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetAdditionalUsersResponse)) return false;
        GetAdditionalUsersResponse that = (GetAdditionalUsersResponse) o;
        return Objects.equals(getList(), that.getList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getList());
    }

    @Override
    public String toString() {
        return "GetAdditionalUsersResponse{" +
                "list=" + list +
                '}';
    }
}
