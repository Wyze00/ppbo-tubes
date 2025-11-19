package tubes.views;

import javax.swing.*;
import java.awt.*;
import tubes.models.dto.LoginForm;
import tubes.util.Dialog;

public class AuthView {
    
    public int handleMenu(){
        String[] choices = {"Login", "Register"};
        return Dialog.inputChoice("Pilih Metode", choices);
    }

    public LoginForm handleLogin() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);

        JOptionPane.showConfirmDialog(
                null,
                panel,
                "Form Login",
                JOptionPane.OK_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        return new LoginForm(userField.getText(), String.valueOf(passField.getPassword()));
    }

    public LoginForm handleRegister(){
        return handleLogin();
    }

    public void handleError(String message){
        Dialog.outputError(message);
    }

    public void handleSuccess(String message){
        Dialog.outputInformation(message);
    }
}
