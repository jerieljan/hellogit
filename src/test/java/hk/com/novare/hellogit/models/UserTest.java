package hk.com.novare.hellogit.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void simpleTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

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
        u.setUniversity("PUP");

        u.setWork("Software Engineer");
        Assert.assertEquals("Test", u.getName());
        Assert.assertEquals("Hello", u.getGreeting());
        Assert.assertEquals("November", u.getDate());
        Assert.assertEquals("PUP", u.getUniversity());
        Assert.assertEquals("Software Engineer", u.getWork());

    }
}