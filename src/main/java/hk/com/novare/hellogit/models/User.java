package hk.com.novare.hellogit.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import hk.com.novare.hellogit.controllers.UserController;

/**
 * A user is an entry in a file that contains
 * a name and his/her greeting.
 * <p>
 * This class is used by the {@link UserController}
 * during announcements.
 *
 * @author jerieljan
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(Include.NON_EMPTY)
public class User {

    private String name;
    private String greeting;
    private String date;
    private String university;
    private String work;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

}
