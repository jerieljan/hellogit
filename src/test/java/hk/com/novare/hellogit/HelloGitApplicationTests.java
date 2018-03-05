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


    @Test
    public void testUserService(){
        //When the application runs, the user service must NOT be empty. (Thanks to our example)
        Assert.assertFalse(userFilesService.getUsers().isEmpty());


        Optional<User> defaultUser = userFilesService.getUsers().stream().filter(it -> it.getName().equals("Ed")).findFirst();
        Assert.assertTrue(defaultUser.isPresent());
        Assert.assertEquals("How you doin' sir? Its nice to be here", defaultUser.get().getGreeting());
        Assert.assertEquals("February 17, 2018", defaultUser.get().getDate());
        Assert.assertEquals("Bulacan State University - Sarmiento Campus", defaultUser.get().getUniversity());
        Assert.assertEquals("Software Developer/Android Developer", defaultUser.get().getWork());


    }


    @Test
    public void testUsersWithinFolder(){
        Assert.assertFalse(userFilesService.getUsers().isEmpty());

        Optional<User> entryUser = userFilesService.getUsers().stream().filter(it -> it.getName().equals("Ed")).findFirst();
        Assert.assertTrue(entryUser.isPresent());
        Assert.assertEquals("How you doin' sir? Its nice to be here", entryUser.get().getGreeting());

    }

    @Test
    public void testSpecificUserLookupWithUnderscore() {
        ResponseEntity<UserDao> userQueryWithSpace = controller.getSpecificUser("Mary_Car");
        Assert.assertNotNull(userQueryWithSpace.getBody());
        Assert.assertEquals("Mary Car", userQueryWithSpace.getBody().getUsers().get(0).getName());
    }




    @Test
    public void testSpecificUserLookupWithSpace() {
        ResponseEntity<UserDao> userQueryWithSpace = controller.getSpecificUser("Mary Car");
        Assert.assertNotNull(userQueryWithSpace.getBody());
        Assert.assertEquals("Mary Car", userQueryWithSpace.getBody().getUsers().get(0).getName());

        ResponseEntity<UserDao> userQueryWithEncodedSpace = controller.getSpecificUser("Mary%20Car");
        Assert.assertNotNull(userQueryWithEncodedSpace.getBody());
        Assert.assertEquals("Mary Car", userQueryWithEncodedSpace.getBody().getUsers().get(0).getName());
    }



    @Test
    public void testAnnounceController()  {
        Assert.assertNotNull(controller);
        ResponseEntity<UserDao> testCall = controller.listAllUsers();
        Assert.assertEquals(testCall.getStatusCode(), HttpStatus.OK);
        Assert.assertFalse(testCall.getBody().getUsers().isEmpty());
    }
}
