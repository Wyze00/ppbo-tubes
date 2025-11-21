package tubes.controllers;

import tubes.models.User;
import tubes.models.dto.LoginForm;
import tubes.models.enums.UserRole;
import tubes.repositories.UserRepo;
import tubes.views.AuthView;

public class AuthController {
    private AuthView authView = new AuthView();
    private UserRepo userRepo = new UserRepo();

    public User start(){

        try {
            int choice = authView.handleMenu();

            if(choice == 0){
               return login();
            } else {
                return register();
            }

        } catch (Exception e) {
            authView.handleError(e.getMessage());
        }

        return null;
    }

    public User login() throws Exception {
        LoginForm form = authView.handleLogin();
        this.validateLoginForm(form);
        
        User user = userRepo.findByUsername(form.getUsername()); 

        if(user == null || !form.getPassword().equals(user.getPassword())){
            throw new Exception("Username / Password Salah");
        }

        authView.handleSuccess("Login Berhasil");
        return user;
    }

    public User register() throws Exception {
        LoginForm form = authView.handleRegister();
        this.validateLoginForm(form);

        User user = userRepo.findByUsername(form.getUsername());

        if(user != null){
            throw new Exception("Username sudah ada");
        }

        User newUser = new User(form.getUsername(), form.getPassword(), UserRole.PLAYER);
        this.userRepo.insert(newUser);

        authView.handleSuccess("Registrasi Berhasil");
        return this.userRepo.findByUsername(newUser.getUsername());
    }

    public void validateLoginForm(LoginForm form) throws Exception {
        if(form.getPassword().length() < 5){
            throw new Exception("Password Minimal 6 karakter");
        }
    }
}
