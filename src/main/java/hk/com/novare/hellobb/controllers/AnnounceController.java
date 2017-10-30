package hk.com.novare.hellobb.controllers;

import hk.com.novare.hellobb.models.User;
import hk.com.novare.hellobb.models.dao.UserDao;
import hk.com.novare.hellobb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controllers serve as entrypoints (well, they serve endpoints) to
 * your application. AnnounceController simply presents {@link User} objects
 * in various ways (listing them all, or specifically)
 *
 * @author jerieljan
 */
@RestController
@RequestMapping("announce")
public class AnnounceController {


    @Autowired
    private UserService userService;

    /**
     * This is a simple HTTP endpoint. It simply assembles our {@link UserDao}
     * declares its status and calls the {@link UserService} for user information.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<UserDao> announceAll()  {
        UserDao userDao = new UserDao();
        userDao.setStatus("Success.");
        userDao.setUsers(userService.getUsers());

        return ResponseEntity.ok(userDao);
    }
}
