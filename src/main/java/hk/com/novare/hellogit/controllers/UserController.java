package hk.com.novare.hellogit.controllers;

import hk.com.novare.hellogit.models.User;
import hk.com.novare.hellogit.models.dao.UserDao;
import hk.com.novare.hellogit.services.UserFilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controllers serve as entrypoints (well, they serve endpoints) to
 * your application. AnnounceController simply presents {@link User} objects
 * in various ways (listing them all, or specifically)
 *
 * @author jerieljan
 */
@RestController
@RequestMapping("users")
public class UserController {

    Map<String, User> uStore = new HashMap<String, User>();
    public static final String SUCCESS = "Success.";
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserFilesService userFilesService;

    /**
     * This is a simple HTTP endpoint. It simply assembles our {@link UserDao}
     * declares its status and calls the {@link UserFilesService} for user information.
     *
     * @return
     */

    @GetMapping({"", "/"})
    public ResponseEntity<UserDao> listAllUsers() {
        UserDao userDao = new UserDao();
        userDao.setStatus(SUCCESS);
        userDao.setUsers(userFilesService.getUsers());


        return ResponseEntity.ok(userDao);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDao> getSpecificUser(@PathVariable String name) {
        User matchingUser = userFilesService.getUserByName(name.replaceAll("_", " ").replaceAll("%20", " "));
        if (matchingUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            UserDao userDao = new UserDao(matchingUser);
            userDao.setStatus(SUCCESS);
            return ResponseEntity.ok(userDao);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<UserDao> deleteUser(@PathVariable String name) {
        User matchingUser= userFilesService.deleteUserByName(name.replaceAll("_", " ").replaceAll("%20", " "));
        if (matchingUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            UserDao userDao = new UserDao(matchingUser);
            userDao.setStatus(SUCCESS);
            userDao.setMessage("User has been successfully deleted");
            return ResponseEntity.ok(userDao);
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<UserDao> addUser(@RequestBody User user, String name) {
        UserDao userDao = new UserDao();
        userFilesService.addUser(user);
        userDao.setStatus(SUCCESS);
        userDao.setMessage("New user has been added");
        return ResponseEntity.ok(userDao);
    }


    @PutMapping("/{name}")
    public ResponseEntity<UserDao> update(@PathVariable String name, @RequestBody User user) {
        User matchingUser = userFilesService.updateUser(name.replaceAll("_", " ").replaceAll("%20", " "), user);
        if (matchingUser != null) {
            UserDao userDao = new UserDao(matchingUser);
            uStore.remove(name);
            user.setName(name);
            uStore.put(name, user);
            userFilesService.updateUser(name, user);
            userDao.setStatus(SUCCESS);
            userDao.setMessage("User " + name + " has been successfully updated.");
            return ResponseEntity.ok(userDao);
        } else {
            ResponseEntity.notFound().build();
        }
        return null;
    }
}


