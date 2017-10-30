package hk.com.novare.hellobb.models;

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
        Assert.assertEquals("Test", u.getName());
        Assert.assertEquals("Hello", u.getGreeting());
    }
}