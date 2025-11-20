package tubes.controllers;

import tubes.views.AdminView;
import tubes.views.WeaponView;
import tubes.views.EnemyView;
import tubes.views.BossView;
import tubes.views.SkillView;
import tubes.views.PotionView;
import tubes.views.BuffView;
import tubes.views.DifficultyView;

public class AdminController {
    private AdminView adminView = new AdminView();
    private WeaponView weaponView = new WeaponView();
    private EnemyView enemyView = new EnemyView();
    private BossView bossView = new BossView();
    private SkillView skillView = new SkillView();
    private PotionView potionView = new PotionView();
    private BuffView buffView = new BuffView();
    private DifficultyView difficultyView = new DifficultyView();
    // Admin bisa nambah enemy, boss, weapon, skill, buff, potion
    public void start(){
        int choice = adminView.handleMainMenu();
        
        while(choice != 0){
            switch(choice){
                case 1 -> weaponView.handleWeaponMenu();
                case 2 -> enemyView.handleEnemyMenu();
                case 3 -> bossView.handleBossMenu();
                case 4 -> skillView.handleSkillMenu();
                case 5 -> buffView.handleBuffMenu();
                case 6 -> potionView.handlePotionMenu();
                case 7 -> difficultyView.handleDifficultyMenu();
                default -> System.out.println("Exiting Admin Menu");
                
            }

            choice = adminView.handleMainMenu();
        }
    }
}
