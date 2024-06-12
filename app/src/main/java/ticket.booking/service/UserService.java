package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
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

    public UserService() throws IOException {
        //userservice default constructor where we will just load other users data
        //as we don't have current user logged we won;t initialize it
        users=getUsers();
    }

    public UserService(User user) throws IOException {
        this.user=user;
        users=getUsers();
    }

    public List<User> getUsers() throws IOException {
        File userFile=new File(USERS_PATH);
        return objectMapper.readValue(userFile,new TypeReference<List<User>>(){});
    }

    /*user_id should map to userId*/
    public Boolean loginUser(User user){
       Optional<User> foundUser =users.stream().filter(user1->{ return user1.getName().equals(user.getName()) && UserServiceUtils.checkPassword(user.getPassword(),user1.getHashPassword());
       }).findFirst();

       return foundUser.isPresent();
    }

    public Boolean signUp(User user){
        try {
            users.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException {
        File file=new File(USERS_PATH);
        objectMapper.writeValue(file,users);
    }

    public void fetchBooking(){
        user.printTicket();
    }

    public Boolean cancelTicket(int ticketId) throws IOException {
        //fetchTicket and remove it and save updated data to file i.e local db
        Optional<Ticket> foundTicket=user.getBookedTicket().stream().filter(ticket->{return ticket.getTicketId().equals(ticketId);}).findFirst();
        if(foundTicket.isPresent()){
            user.getBookedTicket().remove(foundTicket);
            saveUserListToFile();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination){
        TrainService trainService= null;
        try {
            trainService = new TrainService();
            return trainService.searchTrains(source,destination);
        } catch (IOException e) {
            System.out.println("Error while searching trains");
            return null;
        }

    }
}
