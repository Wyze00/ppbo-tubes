package tubes.controllers;

import java.util.List;

import tubes.models.Difficulty;
import tubes.repositories.DifficultyRepo;

public class DifficultyController {
    private DifficultyRepo difficultyRepo = new DifficultyRepo();

    public List<Difficulty> getAllDifficulties(){
        return difficultyRepo.findAll();
    }

    public void addDifficulty(Difficulty diff){
        difficultyRepo.insert(diff);
    }

    public void deleteDifficulty(Difficulty diff){
        difficultyRepo.delete(diff);
    }
}