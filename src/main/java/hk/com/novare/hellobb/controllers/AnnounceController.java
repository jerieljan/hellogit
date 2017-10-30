package hk.com.novare.hellobb.controllers;

import hk.com.novare.hellobb.models.User;
import hk.com.novare.hellobb.models.UserDao;
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

    @GetMapping("/")
    public ResponseEntity<UserDao> announceAll()  {
        UserDao userDao = new UserDao();
        userDao.setStatus("Success.");

        return ResponseEntity.ok(userDao);
    }
}
