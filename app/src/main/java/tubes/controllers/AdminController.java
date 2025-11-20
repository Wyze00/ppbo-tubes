package tubes.controllers;

import tubes.views.AdminView;
import tubes.views.WeaponView;
import tubes.views.EnemyView;

public class AdminController {
    private AdminView adminView = new AdminView();
    private WeaponView weaponView = new WeaponView();
    private EnemyView enemyView = new EnemyView();
    // Admin bisa nambah enemy, boss, weapon, skill, buff, potion
    public void start(){
        int choice = adminView.handleMainMenu();
        
        while(choice != 0){
            switch(choice){
                case 1 -> weaponView.handleMainMenu();
                case 2 -> enemyView.handleEnemyView();
                default -> System.out.println("Exiting Admin Menu");
                
            }

            choice = adminView.handleMainMenu();
        }
    }
}
