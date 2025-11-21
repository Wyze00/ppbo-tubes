package tubes.controllers;

import java.util.List;
import tubes.models.Skill;
import tubes.repositories.SkillRepo;

public class SkillController {
    private SkillRepo skillRepo = new SkillRepo();

    public List<Skill> getAllSkills(){
        return skillRepo.findAll();
    }

    public void addSkill(Skill skill){
        skillRepo.insert(skill);
    }

    public void deleteSkill(Skill skill){
        skillRepo.delete(skill);
    }
}
