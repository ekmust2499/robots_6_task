package gui;

import javax.swing.*;
import java.awt.*;

public class MessageRegistration extends JPanel {

    private JTextField name;
    private JTextField login;
    private JPasswordField password;

    public MessageRegistration() {
        GridBagLayout l = new GridBagLayout();
        l.columnWeights = new double[] {.3, 1};
        setLayout(l);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridwidth = 30;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Имя:"), gbc);
        gbc.gridy = 1;
        add(new JLabel("Логин:"), gbc);
        gbc.gridy = 2;
        add(new JLabel("Пароль:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        name = new JTextField();
        gbc.gridy = 0;
        add(name, gbc);
        login = new JTextField();
        gbc.gridy = 1;
        add(login, gbc);
        password = new JPasswordField();
        gbc.gridy = 2;
        add(password, gbc);
    }

    public String getLogin() {
        return login.getText();
    }
    public String getNameUser() {
        return name.getText();
    }
    public String getPassword() {
        return new String(password.getPassword());
    }
}
