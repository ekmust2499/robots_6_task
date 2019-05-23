package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MessageToken extends JPanel {

    private JTextField login;
    private String token;
    private JButton btnFile;
    private JFileChooser fileChooser;

    public MessageToken() {
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
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(MessageToken.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        FileReader fr = new FileReader(file);
                        BufferedReader reader = new BufferedReader(fr);
                        token = reader.readLine();
                        token = token.substring(2);
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
