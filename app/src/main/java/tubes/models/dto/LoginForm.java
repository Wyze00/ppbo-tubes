package tubes.models.dto;

public class LoginForm {
    private String username;
    private String password;

    public LoginForm(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
