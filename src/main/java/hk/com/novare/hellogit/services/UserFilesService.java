package hk.com.novare.hellogit.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import hk.com.novare.hellogit.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The UserFilesService handles User-related processes. In this project, we're
 * not exactly using a conventional database and a Repository -- we're just reading
 * off files on the system's classpath.
 * <p>
 * So for now, that's UserService's job -- read flat files then turn them to {@link User} objects
 * that our system can use.
 *
 * @author jerieljan
 */
@Service
public class UserFilesService {

    //This is where all Users files are kept. By default, it's the current working directory.
    public static final String USER_DIRECTORY = "src/main/resources/users/";
    private Logger logger = LoggerFactory.getLogger(UserFilesService.class);
    private List<User> users;

    public UserFilesService() {
        users = new ArrayList<>();
        try {
            //Attempt to load all users from the application's working directory.
            loadAllUsers(users);
        } catch (IOException e) {
            //If an error occurs, pass the exception notice to the logger.
            users = Collections.EMPTY_LIST;
            logger.error("User service cannot run: ", e);
        }
    }

    /**
     * Retrieves the list of users.
     * May be empty if no files are found or if an error has occurred.
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public User deleteUserByName(String name) {

        for (User u : users) {
            if (u.getName().equals(name)) {
                users.remove(u);
                return u;
            }
        }

        return null;
    }

    /**
     * This will load all users from the filesystem to the provided
     * collection.
     *
     * @param usersList
     */
    private void loadAllUsers(List<User> usersList) throws IOException {
        Path cwd = Paths.get(USER_DIRECTORY);
        DirectoryStream<Path> currentWorkingDirectory = Files.newDirectoryStream(cwd);

        //Check every file, attempt to parse then add if it's good.
        currentWorkingDirectory.forEach(file -> {
            Optional<User> parsedUser = parseUserFile(file);

            //If there's a valid user parsed (and not empty due to errors, add it in!)
            if (parsedUser.isPresent()) {
                usersList.add(parsedUser.get());
            }
        });

        //Sort the results by their names.
        usersList.sort(Comparator.comparing(User::getName));
    }

    /**
     * This method's sole job is to read a Path's contents and return a User.
     * If it can't be parsed due to errors, it'll return an empty Optional instead.
     *
     * @param userFile
     * @return
     */

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Optional<User> parseUserFile(Path userFile) {
        try {
            //Read all the lines (Java 7 NIO)
            List<String> userFileContents = Files.readAllLines(userFile);

            //Place all of it inside a StringBuilder.
            StringBuilder contentBuilder = new StringBuilder();
            userFileContents.forEach(contentBuilder::append);

            //Use JacksonMapper to read the entire file and create a User class if it's valid JSON.
            //The ObjectMapper is a very powerful tool. It'll let you convert Java objects to JSON and back.
            ObjectMapper om = new ObjectMapper();
            User parsedUser = om.readValue(contentBuilder.toString(), User.class);
            logger.info("Parsed user: " + parsedUser.getName());

            return Optional.of(parsedUser);
        } catch (IOException e) {
            logger.warn("Could not read file: " + userFile.getFileName() + " Reason: " + e.getMessage());
            return Optional.empty();
        }
    }
}
