package gui;

class UserAccount {

    private String userName;
    private String login;
    private String password;

    UserAccount() {
    }

    UserAccount(String userName, String login, String password) {
        this.userName = userName;
        this.password = password;
        this.login = login;
    }

    String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
}
