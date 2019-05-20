package gui;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class Database {
    private DatabaseReference databaseReference;

    void initDatabase() throws IOException {
        try {
            FileInputStream serviceAccount = new FileInputStream("robots2019-bf175-firebase-adminsdk-14ohx-deaa42114e.json");
            System.out.println(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://robots2019-bf175.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Users");
            System.out.println(databaseReference);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    String[] checkAndGetUserByToken(String login, String token){
        String[] usefulData = new String[2];
        //UserAccount[] users = new UserAccount[1];
        DatabaseReference childReference = databaseReference.child(login);
        System.out.println(childReference);
        Object event = new Object();
        childReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                if(dataSnapshot.exists()) {

                    UserAccount user = new UserAccount();
                    user.setUserName(dataSnapshot.child("name").getValue().toString());
                    user.setLogin(dataSnapshot.child("login").getValue().toString());
                    user.setPassword(dataSnapshot.child("password").getValue().toString());
                    String tokenFromDatabase = dataSnapshot.child("token").getValue().toString();
                    long time = Long.parseLong(dataSnapshot.child("time_to_live").getValue().toString());
                    Date date = new Date();
                    long now = date.getTime();
                    if (now > time) //если время жизни токена истекло
                    {
                        usefulData[0] = null;
                        usefulData[1] = "Токен устарел";
                        //users[0] = null;
                    }
                    else {
                        if (tokenFromDatabase.equals(token)) {
                            usefulData[0] = user.getUserName();
                            //users[0] = user;
                        } else {
                            usefulData[0] = null;
                            usefulData[1] = "Неверный токен";
                            //users[0] = user;
                        }
                    }
                    System.out.println(usefulData[0]);
                    System.out.println(usefulData[1]);
                }
                else
                {
                    usefulData[0] = null;
                    usefulData[1] = "Пользователя не существует";
                    //users[0] = null;
                }

                synchronized(event)
                {
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                event.notifyAll();
            }
        });
        try {
            synchronized(event)
            {
                event.wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return usefulData;
    }

    String[] checkAndGetUserByPassword(String login, String password){

        String[] usefulData = new String[2];
        //UserAccount[] users = new UserAccount[1];
        DatabaseReference childReference = databaseReference.child(login);
        System.out.println(childReference);
        Object event = new Object();
        childReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                if(dataSnapshot.exists()) {

                    UserAccount user = new UserAccount();
                    user.setUserName(dataSnapshot.child("name").getValue().toString());
                    user.setLogin(dataSnapshot.child("login").getValue().toString());
                    user.setPassword(dataSnapshot.child("password").getValue().toString());
                    long time = Long.parseLong(dataSnapshot.child("time_to_live").getValue().toString());
                    Date date = new Date();
                    long now = date.getTime();
                    System.out.println(time);
                    System.out.println(now);
                    if (user.getPassword().equals(password)) {
                        //users[0] = user;
                        usefulData[0] = user.getUserName();
                        if (now > time) //если время жизни токена истекло
                        {
                            String token = TokenGenerator.nextToken();
                            updateToken(user.getLogin(), token);
                            usefulData[1] = token;
                            //users[0] = null;
                        }
                    }

                    else {
                        //users[0] = null;
                        usefulData[0] = null;
                        usefulData[1] = "Неверный пароль";

                    }
                    System.out.println(usefulData[0]);
                }
                else {
                    //users[0] = null;
                    usefulData[0] = null;
                    usefulData[1] = "Пользователя не существует";
                }
                synchronized(event)
                {
                    event.notify();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
                event.notifyAll();
            }
        });
        try {
            synchronized(event)
            {
                event.wait(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return usefulData;
    }

    private void writeTokenInFile(String token){
        File file = new File("token.txt");
        try {
            try (OutputStream os = new FileOutputStream(file)) {
                try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os))) {
                    dos.writeUTF(token);
                    dos.flush();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateToken(String login, String token) {

        DatabaseReference childReference = databaseReference;
        System.out.println(childReference);
        Map<String, Object> hopperUpdates = new HashMap<>();
        Date now = new Date();
        long seconds = now.getTime();
        long time = seconds + 1800000;
        System.out.println("in update");
        hopperUpdates.put("login", login);
        hopperUpdates.put("token", token);
        hopperUpdates.put("time_to_live", String.valueOf(time));
        childReference.child(login).updateChildren(hopperUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference dataReference) {
                writeTokenInFile(token);
                System.out.println("update Token");
            }
        });

        System.out.println("вышли из update");
    }

    void addUserInDatabase(UserAccount user) {
        System.out.println("зашли в save");
        Map<String, Object> hopperUpdates = new HashMap<>();
        String name = user.getUserName();
        String login = user.getLogin();
        String password = user.getPassword();
        String token = TokenGenerator.nextToken();
        Date now = new Date();
        long seconds = now.getTime();
        long time = seconds + 1800000;
        System.out.println("in");
        hopperUpdates.put("name", name);
        hopperUpdates.put("login", login);
        hopperUpdates.put("password", password);
        hopperUpdates.put("token", token);
        hopperUpdates.put("time_to_live", String.valueOf(time));
        databaseReference.child(login).setValue(hopperUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference dataReference) {
                writeTokenInFile(token);
            }
        });
        System.out.println("вышли из save");
    }
}
