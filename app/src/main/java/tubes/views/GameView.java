package tubes.views;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import tubes.models.Enemy;
import tubes.models.Player;
import tubes.models.Skill;
import tubes.models.Weapon;
import tubes.models.enums.Element;
import tubes.util.Dialog;

public class GameView {
    
    public int handleMainMenu(boolean isContinue){
        final int[] userChoice = {0};

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        JLabel messageLabel = new JLabel("Selamat Datang di Main Menu", SwingConstants.CENTER);
        JButton btnNewGame = new JButton("New Game");
        JButton btnContinue = new JButton("Continue");

        if (!isContinue) {
            btnContinue.setEnabled(false);
        }

        btnNewGame.addActionListener(e -> {
            userChoice[0] = 0;
        });

        btnContinue.addActionListener(e -> {
            userChoice[0] = 1;
        });

        panel.add(messageLabel);
        panel.add(btnNewGame);
        panel.add(btnContinue);

        Dialog.showCustomPanel("Main Menu", panel);
        return userChoice[0];
    }

    public int handleChooseDifficulty(String[] diffNames){
        return Dialog.inputChoice("Pilih Difficulty", diffNames);
    }

    public String handleChooseElement(String[] elementNames){
        int choice = Dialog.inputChoice("Pilih Element", elementNames);
        return elementNames[choice];
    }
    
    public void handleSaveGame(){
        Dialog.outputInformation("Game Save Success");
    }

    public String handleRunGame2(Player player, Enemy enemy, String log, boolean disableWeapon, boolean disableSkill){

        final String[] options = new String[]{"hand"};

        Dialog.outputInformation(log);
        Dialog.outputInformation(player.toString());
        Dialog.outputInformation(enemy.toString());

        return "hand";
    }

    public String handleRunGame(Player player, Enemy enemy, String log, boolean disableWeapon, boolean disableSkill){

        final String[] result = {"hand"}; 

        JDialog battleDialog = new JDialog((Frame)null, "Battle Turn - Stage " + player.getStage(), true);
        battleDialog.setSize(900, 650); // Sedikit diperlebar agar muat 4 tombol
        battleDialog.setLayout(new BorderLayout());
        battleDialog.setLocationRelativeTo(null);
        battleDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // --- STATUS PANEL ---
        JPanel statusPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statusPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel playerPanel = createInfoPanel("PLAYER", player.getUsername(), player.getCurrentHp(), player.getHp(), player.getCurrentMana(), player.getMana(), player.getElement());
        JPanel enemyPanel = createInfoPanel("ENEMY", enemy.getName(), enemy.getCurrentHp(), enemy.getHp(), enemy.getCurrentMana(), enemy.getMana(), enemy.getElement());

        statusPanel.add(playerPanel);
        statusPanel.add(enemyPanel);

        // --- LOG PANEL ---
        JTextArea logArea = new JTextArea();
        logArea.setText(log);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setCaretPosition(logArea.getDocument().getLength());
        
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Battle Log"));

        // --- BUTTON PANEL ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        
        JButton btnHand = new JButton("Hand Attack");
        JButton btnWeapon = new JButton("Weapon Attack");
        JButton btnSkill = new JButton("Skill Attack");
        JButton btnInfo = new JButton("Info / Status"); // TOMBOL BARU

        // Styling
        Dimension btnSize = new Dimension(160, 50); // Ukuran tombol
        btnHand.setPreferredSize(btnSize);
        btnWeapon.setPreferredSize(btnSize);
        btnSkill.setPreferredSize(btnSize);
        btnInfo.setPreferredSize(btnSize);
        btnInfo.setBackground(new Color(230, 240, 255)); // Sedikit beda warna biar jelas

        // Logic Disable
        if (disableWeapon) {
            btnWeapon.setEnabled(false);
            btnWeapon.setToolTipText("No Weapon / Not enough Mana");
        } else {
            btnWeapon.setToolTipText("Mana Cost: " + player.getEquippedWeapon().getMana());
        }

        if (disableSkill) {
            btnSkill.setEnabled(false);
            btnSkill.setToolTipText("Cooldown / Not enough Mana");
        } else {
            btnSkill.setToolTipText("Mana Cost: " + player.getEquippedSkill().getManaCost());
        }

        // Action Listener Utama (Untuk Attack)
        ActionListener attackListener = e -> {
            JButton source = (JButton) e.getSource();
            if (source == btnHand) result[0] = "hand";
            else if (source == btnWeapon) result[0] = "weapon";
            else if (source == btnSkill) result[0] = "skill";
            
            battleDialog.dispose();
        };

        btnHand.addActionListener(attackListener);
        btnWeapon.addActionListener(attackListener);
        btnSkill.addActionListener(attackListener);

        // Action Listener Khusus Info (Tidak menutup dialog battle)
        btnInfo.addActionListener(e -> {
            showPlayerInfo(battleDialog, player);
        });

        buttonPanel.add(btnHand);
        buttonPanel.add(btnWeapon);
        buttonPanel.add(btnSkill);
        buttonPanel.add(btnInfo); // Add tombol info

        battleDialog.add(statusPanel, BorderLayout.NORTH);
        battleDialog.add(scrollPane, BorderLayout.CENTER);
        battleDialog.add(buttonPanel, BorderLayout.SOUTH);

        battleDialog.setVisible(true);

        return result[0];
    }

    /**
     * Helper untuk menampilkan detail info player dalam popup
     */
    private void showPlayerInfo(Component parent, Player player) {
        StringBuilder info = new StringBuilder();
        info.append("=== PLAYER STATUS ===\n");
        info.append("Base Attack: ").append(player.getAttack()).append("\n");
        info.append("Base Defense: ").append(player.getDefense()).append("\n\n");

        // Info Weapon
        info.append("--- EQUIPPED WEAPON ---\n");
        Weapon w = player.getEquippedWeapon();
        if (w != null) {
            info.append("Name: ").append(w.getName()).append("\n");
            info.append("Rarity: ").append(w.getRarity()).append("\n");
            info.append("Attack Bonus: ").append(w.getAttack()).append("\n");
            info.append("Mana Cost: ").append(w.getMana()).append("\n");
            info.append("Element: ").append(w.getElement()).append("\n");
        } else {
            info.append("None (Tangan Kosong)\n");
        }
        info.append("\n");

        // Info Skill
        info.append("--- EQUIPPED SKILL ---\n");
        Skill s = player.getEquippedSkill();
        if (s != null) {
            info.append("Name: ").append(s.getName()).append("\n");
            info.append("Rarity: ").append(s.getRarity()).append("\n");
            info.append("Damage: ").append(s.getAttack()).append("\n");
            info.append("Mana Cost: ").append(s.getManaCost()).append("\n");
            info.append("Cooldown: ").append(s.getCooldown()).append(" turns\n");
            info.append("Element: ").append(s.getElement()).append("\n");
        } else {
            info.append("None\n");
        }

        JOptionPane.showMessageDialog(parent, info.toString(), "Player Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createInfoPanel(String title, String name, int curHp, int maxHp, int curMana, int maxMana, Element element) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        JLabel lblName = new JLabel(name + " [" + element + "]");
        lblName.setFont(new Font("Arial", Font.BOLD, 16));
        lblName.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHp = new JLabel("HP: " + curHp + "/" + maxHp);
        lblHp.setAlignmentX(Component.LEFT_ALIGNMENT);
        JProgressBar hpBar = new JProgressBar(0, maxHp);
        hpBar.setValue(curHp);
        hpBar.setForeground(Color.RED);
        hpBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblMana = new JLabel("Mana: " + curMana + "/" + maxMana);
        lblMana.setAlignmentX(Component.LEFT_ALIGNMENT);
        JProgressBar manaBar = new JProgressBar(0, maxMana);
        manaBar.setValue(curMana);
        manaBar.setForeground(Color.BLUE);
        manaBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblName);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblHp);
        panel.add(hpBar);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblMana);
        panel.add(manaBar);

        return panel;
    }

    public void handleGameOver(){
        Dialog.outputError("Game Over");
    }

    public int handleShowReward(String[] optionNames){
        return Dialog.inputChoice("Pilih Reward", optionNames);
    }

    public void showRewardApplied(String name, String message){}
}
