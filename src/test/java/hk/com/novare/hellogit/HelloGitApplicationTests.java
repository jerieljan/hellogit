package hk.com.novare.hellogit;

import hk.com.novare.hellogit.controllers.UserController;
import hk.com.novare.hellogit.models.User;
import hk.com.novare.hellogit.models.dao.UserDao;
import hk.com.novare.hellogit.services.UserFilesService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloGitApplicationTests {


    @Autowired
    private UserFilesService userFilesService;


    @Autowired
    private UserController controller;

    /**
     * This performs a simple test to our user service:
     * Check if it isn't empty (it shouldn't), check the default user and its contents.
     *
     * @throws Exception
     */
    @Test
    public void testUserService() throws Exception {
        //When the application runs, the user service must NOT be empty. (Thanks to our example)
        Assert.assertFalse(userFilesService.getUsers().isEmpty());

        //Retrieve the default use (jerieljan.json) and check its contents.
        Optional<User> defaultUser = userFilesService.getUsers().stream().filter(it -> it.getName().equals("Jeriel")).findFirst();
        Assert.assertTrue(defaultUser.isPresent());
        Assert.assertEquals("Hello, this is your instructor speaking!", defaultUser.get().getGreeting());


    }

    @Test
    public void testSpecificUserLookupWithUnderscore() throws Exception {
        ResponseEntity<UserDao> userQueryWithSpace = controller.getSpecificUser("Mary_Car");
        Assert.assertNotNull(userQueryWithSpace.getBody());
        Assert.assertEquals("Mary Car", userQueryWithSpace.getBody().getUsers().get(0).getName());
    }

    @Test
    public void testSpecificUserLookupWithSpace() throws Exception {
        ResponseEntity<UserDao> userQueryWithSpace = controller.getSpecificUser("Mary Car");
        Assert.assertNotNull(userQueryWithSpace.getBody());
        Assert.assertEquals("Mary Car", userQueryWithSpace.getBody().getUsers().get(0).getName());

        ResponseEntity<UserDao> userQueryWithEncodedSpace = controller.getSpecificUser("Mary%20Car");
        Assert.assertNotNull(userQueryWithEncodedSpace.getBody());
        Assert.assertEquals("Mary Car", userQueryWithEncodedSpace.getBody().getUsers().get(0).getName());
    }

    /*@Test
    public void testDeleteSpecificUser() throws Exception {
        ResponseEntity<UserDao> userQueryDeleteUser = controller.deleteUser("Mary Car");
        Assert.assertNotNull(userQueryDeleteUser.getBody());
        Assert.assertEquals("Mary Car", userQueryDeleteUser.getBody().getUsers().get(0).getName());
    }*/

    /**
     * This performs a  simple test to our controller:
     * Check if it works, it should respond with OK and with content.
     *
     * @throws Exception
     */
    @Test
    public void testAnnounceController() throws Exception {
        Assert.assertNotNull(controller);
        ResponseEntity<UserDao> testCall = controller.listAllUsers();
        Assert.assertEquals(testCall.getStatusCode(), HttpStatus.OK);
        Assert.assertFalse(testCall.getBody().getUsers().isEmpty());
    }
}
