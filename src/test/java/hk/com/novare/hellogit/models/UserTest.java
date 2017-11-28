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
        u.setDate("November");
        u.setUniversity("STI College Ortigas-Cainta");
        u.setWork("Web Development (current: Backend)");
        Assert.assertEquals("Test", u.getName());
        Assert.assertEquals("Hello", u.getGreeting());
        Assert.assertEquals("November", u.getDate());
        Assert.assertEquals("STI College Ortigas-Cainta", u.getUniversity());
        Assert.assertEquals("Web Development (current: Backend)", u.getWork());


    }
}