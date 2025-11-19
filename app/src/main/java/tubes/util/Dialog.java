package tubes.util;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Dialog {
    
    public static void outputPlain(String message){
        JOptionPane.showMessageDialog(
            null,
            message,
            "Rpg Game",
            JOptionPane.PLAIN_MESSAGE
        );
    }

    public static void outputInformation(String message){
        JOptionPane.showMessageDialog(
            null,
            message,
            "Rpg Game",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void outputError(String message){
        JOptionPane.showMessageDialog(
            null,
            message,
            "Rpg Game",
            JOptionPane.ERROR_MESSAGE
        );
    }

    public static String inputString(String message){
        return JOptionPane.showInputDialog(message);

    }

    public static int inputInt(String message){
        return Integer.parseInt(Dialog.inputString(message));
    }

    public static boolean inputBoolean(String message){
        return  JOptionPane.showConfirmDialog(null, message, "Rpg Game", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ? true : false;
    }

    public static int inputChoice(String message, String[] options){
        return JOptionPane.showInternalOptionDialog(
                null,
                message, "Rpg Game",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, 
                options, 
                options[0]
        );
    }

    public static void showCustomPanel(String title, JPanel panel){
        JOptionPane.showMessageDialog(
            null,
            panel,
            title,
            JOptionPane.PLAIN_MESSAGE
        );
    }
}