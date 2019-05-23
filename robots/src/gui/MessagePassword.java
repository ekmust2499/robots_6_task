package gui;

import javax.swing.*;
import java.awt.*;

public class MessagePassword extends JPanel {

    private JTextField login;
    private JPasswordField password;

    public MessagePassword() {
        GridBagLayout l = new GridBagLayout();
        l.columnWeights = new double[] {.3, 1};
        setLayout(l);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 30;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Логин:"), gbc);
        gbc.gridy = 1;
        add(new JLabel("Пароль:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        login = new JTextField();
        add(login, gbc);

        password = new JPasswordField();
        gbc.gridy = 1;
        add(password, gbc);
    }

    public String getLogin() {
        return login.getText();
    }
    public String getPassword() {
        return new String(password.getPassword());
    }
}