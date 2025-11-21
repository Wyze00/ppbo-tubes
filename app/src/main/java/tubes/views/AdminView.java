package tubes.views;

import tubes.util.Dialog;

public class AdminView {
    
    public int handleMainMenu(){

        String menu = "Admin Menu \n" +
                      "1. Manage Weapons\n" +
                      "2. Manage Enemies\n" +
                      "3. Manage Bosses\n" +
                      "4. Manage Skills\n" +
                      "5. Manage Buffs\n" +
                      "6. Manage Potions\n" +
                      "7. Manage Difficulty\n" +
                      "0. Logout\n";

        return Dialog.inputInt(menu);
    }
}
