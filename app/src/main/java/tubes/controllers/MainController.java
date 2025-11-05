package tubes.controllers;

import tubes.models.User;
import tubes.models.enums.UserRole;

public class MainController {
    private AuthController authController = new AuthController();

    public void start(){

        // Auth
        User user = null;
        
        while(user == null) {
            user = authController.start();
        }

        if(user.getRole() == UserRole.ADMIN){
            AdminController adminController = new AdminController();
            adminController.start();
        } else {
            GameController gameController = new GameController(user);
            gameController.start();
        }
    }
}
