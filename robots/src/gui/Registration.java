package gui;

import javax.swing.*;
import java.awt.*;

public class Registration {

    private RobotsProgram robot;
    public Registration(Database database) {
        RegistrationPanel message = new RegistrationPanel();
        if (JOptionPane.showOptionDialog(null, message, "Регистрация", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] {"Зарегистрировать", "Отмена"}, "Зарегистрировать") == JOptionPane.YES_OPTION) {
            if (message.getLogin().length() != 0 && message.getNameUser().length() != 0 && message.getPassword().length() != 0) {
                UserAccount user = new UserAccount(message.getNameUser(), message.getLogin(), message.getPassword());
                database.addUserInDatabase(user);
                JOptionPane.showMessageDialog(null, "Вы зарегистрированы!");
                Main.start = false;
                robot = new RobotsProgram();
            }
            else {
                JOptionPane.showMessageDialog(null, "Надо заполнить все поля", "Корректный ввод", JOptionPane.OK_OPTION);
            }
        }
    }

    private static class RegistrationPanel extends JPanel {

        private JTextField name;
        private JTextField login;
        private JPasswordField password;

        public RegistrationPanel() {
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
}
