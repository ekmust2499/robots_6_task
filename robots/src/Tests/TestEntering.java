package Tests;

//import gui.Database;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestEntering {

    private String[] userName = new String[]{"Vl", "Evgenia"};
    private String[] userToken = new String[2];
    private String[] userNik = new String[]{"Vlad", "evgenia"};
    private String[] userPassword = new String[]{"qwerty", "qs12"};
    //private Database database;
    private TestDataBase testDataBase = new TestDataBase();

    public TestEntering() {
        //database = testDataBase.getDatabase();
        //database = new Database();
        //database.initDatabase();

    }

   // @Test
   // public void RegistrationTest() throws IOException {
        //database = testDataBase.getDatabase();
    //    userToken[1] = testDataBase.addUser();
    //    String[] answerAboutPassword = testDataBase.getUser(userNik[1], userPassword[1], false);
    //    Assert.assertEquals(null, answerAboutPassword[1]);
   //     Assert.assertEquals(userName[1], answerAboutPassword[0]);
   // }

   // @Test
   // public void RightEnteringWithPasswordTest() {
    //    String[] answerAboutPassword = testDataBase.getUser(userNik[0], userPassword[1], false);
    //    Assert.assertEquals(null, answerAboutPassword[1]);
   //     Assert.assertEquals(userNik[0], answerAboutPassword[0]);
  //  }

    @Test
    public void ErrorEnteringWithPasswordTest(){
        //String[] answerAboutPassword = database.checkAndGetUserByPassword(userNik[1], userPassword[1], false);
        //Assert.assertEquals(null, answerAboutPassword[0]);
    }

   // @Test
   // public void RightEnteringWithTokenTest() throws IOException {
   //     userToken[0] = "1qHETsddLzlQTtD2HUNUDMHarlnF8KlkiRXP6cSTKUR9yNMjQyVIuJWi6DXcJXnPY45WaTv47PGyKRiJS9EakEvVSRgQTcLLn5DqJfj4CdjFsln4SRtYAtlqtbsAxiE7c0ORvSLurDJF4DZAcfXQRBw5qtRHV1BMODDOhpmFUgG9AtqTEe7rekK8LrOlya1TjnEUhmDs1UQmNaSRctdzkfcNMM3gXyijPfN3WGCI05F7wO85ZTceW8UDu7cPYrM2";
   //     String[] answerAboutToken = base.checkAndGetUserByToken(userNik[0], userToken[0]);
   //     Assert.assertEquals(userNik[0], answerAboutToken[0]);
    //}

    @Test
    public void ErrorEnteringWithTokenTest() {
        //database = testDataBase.getDatabase();
        //String[] answerAboutToken = database.checkAndGetUserByToken(userName[0], "liopyui");
        //Assert.assertEquals(null, answerAboutToken[0]);
    }
}
