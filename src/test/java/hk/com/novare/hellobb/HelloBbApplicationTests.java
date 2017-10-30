package hk.com.novare.hellobb;

import hk.com.novare.hellobb.controllers.AnnounceController;
import hk.com.novare.hellobb.models.User;
import hk.com.novare.hellobb.models.dao.UserDao;
import hk.com.novare.hellobb.services.UserService;
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
public class HelloBbApplicationTests {



	@Autowired
	private UserService userService;


	@Autowired
	private AnnounceController controller;

	/**
	 * This performs a simple test to our user service:
	 * Check if it isn't empty (it shouldn't), check the default user and its contents.
	 * @throws Exception
	 */
	@Test
	public void testUserService() throws Exception {
		//When the application runs, the user service must NOT be empty. (Thanks to our example)
		Assert.assertFalse(userService.getUsers().isEmpty());

		//Retrieve the default use (jerieljan.json) and check its contents.
		Optional<User> defaultUser = userService.getUsers().stream().filter(it -> it.getName().equals("Jeriel")).findFirst();
		Assert.assertTrue(defaultUser.isPresent());
		Assert.assertEquals("Hello, this is your instructor speaking!", defaultUser.get().getGreeting());
	}

	/**
	 * This performs a  simple test to our controller:
	 * Check if it works, it should respond with OK and with content.
	 * @throws Exception
	 */
	@Test
	public void testAnnounceController() throws Exception {
		Assert.assertNotNull(controller);
		ResponseEntity<UserDao> testCall = controller.announceAll();
		Assert.assertEquals(testCall.getStatusCode(), HttpStatus.OK);
		Assert.assertFalse(testCall.getBody().getUsers().isEmpty());
	}
}
