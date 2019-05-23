package gui;

import java.io.*;

import javax.swing.*;

public class Main {
    private static RobotsProgram robot;
    private static Registration registration;
    private static Database database = new Database();
    static boolean start = true;
    private static MessageToken messageToken;
    private static MessagePassword messagePassword;

    public static boolean CheckedUserWithPassword(String[] answerAboutPassword){
        if (answerAboutPassword[0] != null) {
            if (answerAboutPassword[1] != null) {
                try {
                    File file = new File("token.txt");
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(answerAboutPassword[1]);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            start = false;
            robot = new RobotsProgram();
            return true;
        }
        return false;
    }

    public static boolean CheckedUserWithToken(String token) {
        if (token != null) {
            start = false;
            robot = new RobotsProgram();
            return true;
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        database.initDatabase();
        messagePassword = new MessagePassword();
        messageToken = new MessageToken();
        while (start) {
            int window = JOptionPane.showOptionDialog(null, messagePassword, "Авторизация", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Вход", "Вход с токеном", "Регистрация"}, "Вход");
            switch (window) {
                case 0: {
                    String[] answerAboutPassword = database.checkAndGetUserByPassword(messagePassword.getLogin(), messagePassword.getPassword());
                    if (CheckedUserWithPassword(answerAboutPassword))
                        break;
                    else{
                        JOptionPane.showMessageDialog(null, answerAboutPassword[1], "Ошибка", JOptionPane.OK_OPTION);
                        continue;
                    }
                }
                case 2:
                    registration = new Registration(database);
                    continue;
                case 1:
                    int token = JOptionPane.showOptionDialog(null, messageToken, "Авторизация", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Вход", "Вход с паролем", "Регистрация"}, "Вход");
                    switch (token) {
                        case 0:
                            if (messageToken.getLogin().length() != 0 && messageToken.getToken().length() != 0) {
                                String[] answerAboutToken = database.checkAndGetUserByToken(messageToken.getLogin(), messageToken.getToken());
                                if (CheckedUserWithToken(answerAboutToken[1]))
                                    break;
                                else {
                                    JOptionPane.showMessageDialog(null, "Неверный токен!\nВозможно ваш токен устарел, поэтому зайдите с паролем, мы его вам обновим", "Ошибка токена", JOptionPane.OK_OPTION);
                                    continue;
                                }
                            }
                            else {
                                JOptionPane.showConfirmDialog(null, "Надо заполнить все поля", "Корректный ввод", JOptionPane.OK_OPTION);
                                continue;
                            }
                        case 2:
                            registration = new Registration(database);
                            continue;
                        case 1:
                            continue;
                    }
                case -1:
                    start = false;
            }
        }
    }
}