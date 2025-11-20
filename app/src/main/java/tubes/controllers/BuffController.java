package tubes.controllers;

import java.util.List;

import tubes.models.Buff;
import tubes.repositories.BuffRepo;

public class BuffController {
    private BuffRepo buffRepo = new BuffRepo();

    public List<Buff> getAllBuffs(){
        return buffRepo.findAll();
    }

    public void addBuff(Buff buff){
        buffRepo.insert(buff);
    }

    public void deleteBuff(Buff buff){
        buffRepo.delete(buff);
    }
}
