package com.example.serverconnection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mLogin;
    private UserInfo usr;
    //private ConnectServer cntsvr;
    private EditText usrNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogin = findViewById(R.id.Main_Login_Buttonid);
        usrNameEditText = findViewById(R.id.UserName_PlainTextID);
        passwordEditText = findViewById(R.id.Password_PainTextId);
        usr = new UserInfo();
        //cntsvr = new ConnectServer();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.Main_Login_Buttonid:
                String usrName = usrNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                boolean usrN = usr.checkUser(usrName);
                boolean psd = usr.checkPassword(password);
                //cntsvr.SendSearchData("Woody  happily");
                // Do something

                if(usrN && psd){
                    Intent intent = new Intent(this, Home.class);
                    startActivity(intent);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "User Name or Password is wrong";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
        }
    }
}
