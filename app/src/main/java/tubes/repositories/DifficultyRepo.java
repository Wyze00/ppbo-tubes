package tubes.repositories;

import java.util.ArrayList;
import java.util.List;

import tubes.models.Difficulty;

public class DifficultyRepo {

    public Difficulty findById(int id){
        return null;
    }

    public List<Difficulty> findAll(){
        ArrayList<Difficulty> difficulties = new ArrayList<>();
        difficulties.add(new Difficulty(0, "Easy", 150, 70, 15, 10));
        return difficulties;
    }

    public void insert(Difficulty diff) {}

    public void delete(Difficulty diff) {}

    public void update(Difficulty diff) {}
    
}
