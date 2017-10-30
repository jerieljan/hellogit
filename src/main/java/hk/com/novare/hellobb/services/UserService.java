package hk.com.novare.hellobb.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import hk.com.novare.hellobb.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Date: 10/30/17
 * Time: 11:00 AM
 *
 * @author jerieljan
 */
@Service
public class UserService {

    //This is where all Users files are kept. By default, it's the current working directory.
    public static final String USER_DIRECTORY = "";
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        try {
            //Attempt to laod all users from the application's working directory.
            loadAllUsers(users);
        } catch (IOException e) {
            //If an error occurs, pass the exception notice to the logger.
            users = Collections.EMPTY_LIST;
            logger.error("User service cannot run: ", e);
        }
    }

    /**
     * This will load all users from the filesystem to the provided
     * collection.
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
    }

    /**
     * This method's sole job is to read a Path's contents and return a User.
     * If it can't be parsed due to errors, it'll return an empty Optional instead.
     * @param userFile
     * @return
     */
    private Optional<User> parseUserFile(Path userFile) {
        try {
            //Read all the lines (Java 7 NIO)
            List<String> userFileContents = Files.readAllLines(userFile);

            //Place all of it inside a StringBuilder.
            StringBuilder contentBuilder = new StringBuilder();
            userFileContents.forEach(contentBuilder::append);

            //Use JacksonMapper to read the entire file and create a User class if it's valid JSON.
            ObjectMapper om = new ObjectMapper();
            User parsedUser = om.readValue(contentBuilder.toString(), User.class);

            return Optional.of(parsedUser);
        } catch (IOException e) {
            logger.warn("Could not read file: " + userFile.getFileName() + " Reason: " + e.getMessage());
            return Optional.empty();
        }
    }
}
