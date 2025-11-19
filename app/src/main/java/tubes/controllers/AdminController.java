package tubes.controllers;

import tubes.views.AdminView;
import tubes.views.WeaponView;

public class AdminController {
    private AdminView adminView = new AdminView();
    private WeaponView weaponView = new WeaponView();

    // Admin bisa nambah enemy, boss, weapon, skill, buff, potion
    public void start(){

        // 1 - 7
        int choice = adminView.handleMainMenu();

        switch(choice){
            case 1 -> weaponView.handleMainMenu();
            default -> System.out.println("1");
        }
    }
}
