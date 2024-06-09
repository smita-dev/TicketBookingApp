package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static final String USERS_PATH="./ticket.booking/localDB/user.json";
    private User user;
    private List<User> users;
    private ObjectMapper objectMapper= new ObjectMapper();

    public UserService(User user) throws IOException {
        this.user=user;
        File users=new File(USERS_PATH);
        users=objectMapper.readValue(users,new TypeReference<List<User>>(){});
    }

    /*user_id should map to userId*/
    public Boolean loginUser(User user){
       Optional<User> foundUser =users.stream().filter(user1->{ return user1.getName().equals(user.getName()) && UserServiceUtils.checkPassword(user.getPassword(),user1.getHashPassword())
       }).findFirst();

       return foundUser.isPresent();
    }

    public Boolean signUp(User user){
        try {
            users.add(user);
            saveUserListToFile();
            return Boolean.True;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException {
        File file=new File(USERS_PATH);
        objectMapper.writeValue(file,users);
    }
}
