package net.thumbtack.footballgame.dto.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAllUsersDtoResponse {
    private List<GetUserDtoResponse> users;

    public GetAllUsersDtoResponse() {
        setUsers(null);
    }

    public GetAllUsersDtoResponse(List<GetUserDtoResponse> users) {
       setUsers(users);
    }

    public List<GetUserDtoResponse> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserDtoResponse> users) {
        this.users = users;
        if (users == null) {
            this.users = new ArrayList<>();
        }
    }

    public void addUser(GetUserDtoResponse user) {
        users.add(user);
    }

    public void removeUser(GetUserDtoResponse user) {
        users.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetAllUsersDtoResponse)) return false;
        GetAllUsersDtoResponse that = (GetAllUsersDtoResponse) o;
        return Objects.equals(getUsers(), that.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsers());
    }

    @Override
    public String toString() {
        return "GetAllUsersDtoResponse{" +
                "users=" + users +
                '}';
    }
}
