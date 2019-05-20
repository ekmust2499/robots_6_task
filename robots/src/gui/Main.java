package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Main {
    private static RobotsProgram robot;
    private static Registration registration;
    public static boolean start = true;
    public static void main(String args[]) {
        MainPanel message = new MainPanel();
        MainPanelToken messageToken = new MainPanelToken();
        while (start) {
            int window = JOptionPane.showOptionDialog(null, message, "Авторизация", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Вход", "Вход с токеном", "Регистрация"}, "Вход");
            switch (window) {
                case JOptionPane.OK_OPTION:
                    start = false;
                    robot = new RobotsProgram();
                case JOptionPane.CANCEL_OPTION:
                    registration = new Registration();
                case JOptionPane.NO_OPTION:
                    int token = JOptionPane.showOptionDialog(null, messageToken, "Авторизация", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"Вход", "Вход с паролем", "Регистрация"}, "Вход");
                    switch (token) {
                        case JOptionPane.OK_OPTION:
                            start = false;
                            robot = new RobotsProgram();
                        case JOptionPane.CANCEL_OPTION:
                            registration = new Registration();
                        case JOptionPane.NO_OPTION:
                            start = true;
                        default:
                            start = false;
                    }
                default:
                    start = false;
            }
        }
    }

    private static class MainPanel extends JPanel {

        private JTextField login;
        private JPasswordField password;

        public MainPanel() {
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

    private static class MainPanelToken extends JPanel {

        private JTextField login;
        private String token;
        private JButton btnFile;
        private JFileChooser fileChooser;

        public MainPanelToken() {
            fileChooser = new JFileChooser();
            GridBagLayout l = new GridBagLayout();
            l.columnWeights = new double[]{.3, 1};
            setLayout(l);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 30;
            gbc.anchor = GridBagConstraints.WEST;
            add(new JLabel("Логин:"), gbc);
            gbc.gridy = 1;
            btnFile = new JButton("Выбор файла с токеном");
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            login = new JTextField();
            add(login, gbc);
            gbc.gridy = 1;
            add(btnFile, gbc);
            addFileChooserListeners();
        }

        private void addFileChooserListeners() {
            btnFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fileChooser.setDialogTitle("Выберите файл");
                    // Определение режима - только файл
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = fileChooser.showOpenDialog(MainPanelToken.this);
                    // Если файл выбран, покажем его в сообщении
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            //создаем объект FileReader для объекта File
                            FileReader fr = new FileReader(file);
                            //создаем BufferedReader с существующего FileReader для построчного считывания
                            BufferedReader reader = new BufferedReader(fr);
                            // считаем сначала первую строку
                            token = reader.readLine();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

        public String getLogin() {
            return login.getText();
        }

        public String getToken() {
            return token;
        }
    }
}