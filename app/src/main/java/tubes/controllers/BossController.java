package tubes.controllers;

import java.util.List;
import tubes.models.Boss;
import tubes.repositories.BossRepo;

public class BossController {
    private BossRepo bossRepo = new BossRepo();

    public List<Boss> getAllBosses(){
        return bossRepo.findAll();
    }

    public void addBoss(Boss boss){
        bossRepo.insert(boss);
    }

    public void deleteBoss(Boss boss){
        bossRepo.delete(boss);
    }
}
