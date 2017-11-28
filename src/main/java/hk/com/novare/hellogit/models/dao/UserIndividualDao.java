package hk.com.novare.hellogit.models.dao;

import hk.com.novare.hellogit.controllers.AnnounceController;
import hk.com.novare.hellogit.models.User;

public class UserIndividualDao {
    private String status;
    private String user;

    public String getStatus() {
        return status;
    }

    public String getUser() {
        return user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
