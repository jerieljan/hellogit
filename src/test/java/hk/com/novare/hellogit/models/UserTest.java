package hk.com.novare.hellogit.models;

import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 10/30/17
 * Time: 11:27 AM
 *
 * @author jerieljan
 */
public class UserTest {

    @Test
    public void simpleTest() throws Exception {
        User u = new User();
        u.setName("Test");
        u.setGreeting("Hello");
        u.setDate("Date");
        u.setUniversity("University");
        u.setWork("Work");
        Assert.assertEquals("Test", u.getName());
        Assert.assertEquals("Hello", u.getGreeting());
        Assert.assertEquals("Date", u.getDate());
        Assert.assertEquals("University", u.getUniversity());
        Assert.assertEquals("Work", u.getWork());


    }
}