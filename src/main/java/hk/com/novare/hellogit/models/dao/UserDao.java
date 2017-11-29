package hk.com.novare.hellogit.models.dao;

import hk.com.novare.hellogit.controllers.UserController;
import hk.com.novare.hellogit.models.User;

import java.util.List;

/**
 * This data access object helps in creating the output response of
 * {@link UserController} when handling requests.
 *
 * @author jerieljan
 */
public class UserDao {

    private String status;
    private List<User> users;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
