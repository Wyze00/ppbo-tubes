package tubes.views;

import java.util.List;
import tubes.controllers.SkillController;
import tubes.models.Skill;
import tubes.models.enums.Element;
import tubes.models.enums.Rarity;
import tubes.util.Dialog;

public class SkillView {
    private SkillController skillController = new SkillController();

    public void handleSkillMenu(){

        String menu = "Skill Menu \n" +
                      "1. View All Skills\n" +
                      "2. Add Skill\n" +
                      "3. Delete Skill\n" +
                      "0. Back to Admin Menu\n" +
                      "Choose an option: ";

        int choice = Dialog.inputInt(menu);

        switch(choice){
            case 1 -> this.handleViewAllSkills();
            case 2 -> this.handleAddSkill();
            case 3 -> this.handleDeleteSkill();
        }
    }

    public void handleViewAllSkills(){
        List<Skill> skillData = skillController.getAllSkills();

        for(Skill e : skillData){
            Dialog.outputInformation(e.toString());
        }
    }

    public void handleAddSkill(){
        String name = Dialog.inputString("Enter skill name: ");
        
        String[] allRarityNames = Rarity.getAllNames();
        int rarityChoose = Dialog.inputChoice("Select skill rarity:", allRarityNames);
        Rarity rarity = Rarity.valueOf(allRarityNames[rarityChoose]);
        
        int attack = Dialog.inputInt("Enter skill attack: ");
        
        int manaCost = Dialog.inputInt("Enter skill mana cost: ");
        
        int cooldown = Dialog.inputInt("Enter skill cooldown (in turns): ");
        
        String[] allElementNames = Element.getAllNames();
        int elementChoose = Dialog.inputChoice("Select skill element:", allElementNames);
        Element element = Element.valueOf(allElementNames[elementChoose]);

        skillController.addSkill(new Skill(0, name, rarity, attack, manaCost, cooldown, element));
    }

    public void handleDeleteSkill(){
        List<Skill> skillsData = skillController.getAllSkills();

        String[] skillNames = new String[skillsData.size()];
        for(int i = 0; i < skillsData.size(); i++){
            skillNames[i] = skillsData.get(i).getSkillId() + ". " + skillsData.get(i).getName();
        }

        int skillChoose = Dialog.inputChoice("Select skill to delete:", skillNames);
        Skill selectedSkill = skillsData.get(skillChoose);

        skillController.deleteSkill(selectedSkill);
    }
}
