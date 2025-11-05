package tubes.repositories;

import java.util.ArrayList;
import java.util.List;

import tubes.models.Skill;
import tubes.models.enums.Element;

public class SkillRepo {
    
    public Skill findById(int id){
        return null;
    }

    public List<Skill> findAll(){
        return new ArrayList<Skill>();
    }

    public void insert(Skill skill) {}

    public void delete(Skill skill) {}

    public void update(Skill skill) {}

    public Skill findDefaultByElement(Element element){
        return null;
    }
}
