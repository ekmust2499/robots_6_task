package gui;

import javax.swing.*;
import java.awt.*;

public class Registration {

    private RobotsProgram robot;
    private MessageRegistration message = new MessageRegistration();
    public Registration(Database database) {
        if (JOptionPane.showOptionDialog(null, message, "Регистрация", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Зарегистрировать", "Отмена"}, "Зарегистрировать") == JOptionPane.YES_OPTION) {
            if (message.getLogin().length() != 0 && message.getNameUser().length() != 0 && message.getPassword().length() != 0) {
                addUser(message, database);
            } else {
                JOptionPane.showConfirmDialog(null, "Надо заполнить все поля", "Корректный ввод", JOptionPane.OK_OPTION);
            }
        }
    }

    public void addUser(MessageRegistration message, Database database) {
        UserAccount user = new UserAccount(message.getNameUser(), message.getLogin(), message.getPassword());
        database.addUserInDatabase(user);
        JOptionPane.showMessageDialog(null, "Вы зарегистрированы!");
        Main.start = false;
        robot = new RobotsProgram();
    }
}