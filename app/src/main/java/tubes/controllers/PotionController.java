package tubes.controllers;

import java.util.List;
import tubes.models.Potion;
import tubes.repositories.PotionRepo;

public class PotionController {
    private PotionRepo potionRepo = new PotionRepo();

    public List<Potion> getAllPotions(){
        return potionRepo.findAll();
    }

    public void addPotion(Potion potion){
        potionRepo.insert(potion);
    }

    public void deletePotion(Potion potion){
        potionRepo.delete(potion);
    }
}