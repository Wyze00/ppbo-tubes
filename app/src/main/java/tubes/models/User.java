package tubes.models;

import tubes.models.enums.UserRole;

public class User {
    private int id;
    private String username;
    private String password; 
    private UserRole role;

    public User(int id, String username, String password, UserRole role){
        this.id = id;
        this.username = username;
        this. password = password;
        this.role = role;
    }

    public User(String username2, String password2, UserRole player) {
        //TODO Auto-generated constructor stub
    }

    public int getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public UserRole getRole(){
        return this.role;
    }

    public String getPassword(){
        return this.password;
    }
}