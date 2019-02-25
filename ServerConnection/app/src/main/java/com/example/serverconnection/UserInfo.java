package com.example.serverconnection;

public class UserInfo {
    private String username;
    private String password;

    UserInfo(){
        username = "Bhaskar";
        password = "imbhaskar";
    }

    public boolean checkUser(String user){
        if (username.compareTo(user)==0)
            return true;
        else
            return false;
    }

    public boolean checkPassword(String psd){
        if (password.compareTo(psd)==0)
            return true;
        else
            return false;
    }
}
