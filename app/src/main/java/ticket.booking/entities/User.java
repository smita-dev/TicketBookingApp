package ticket.booking.entities;

import java.util.List;

public class User {
    private String name;
    private String userId;
    private String password;
    private String hashPassword;
    private List<Ticket> bookedTicket;

    public User(String name, String userId, String password, List<Ticket> bookedTicket) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.hashPassword = hashPassword;
        this.bookedTicket = bookedTicket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public List<Ticket> getBookedTicket() {
        return bookedTicket;
    }

    public void setBookedTicket(List<Ticket> bookedTicket) {
        this.bookedTicket = bookedTicket;
    }

    public void printTicket(){
        for (Ticket ticket : bookedTicket) {
            System.out.println(ticket);
        }
    }
}
