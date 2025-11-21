package tubes.controllers;

import java.util.List;
import tubes.models.Enemy;
import tubes.repositories.EnemyRepo;

public class EnemyController {
    private EnemyRepo enemyRepo = new EnemyRepo();

    public List<Enemy> getAllEnemies(){
        return enemyRepo.findAll();
    }

    public void addEnemy(Enemy enemy){
        enemyRepo.insert(enemy);
    }

    public void deleteEnemy(Enemy enemy){
        enemyRepo.delete(enemy);
    }
}