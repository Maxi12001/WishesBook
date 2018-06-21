package com.wishbook.mohamed.wishesbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wishbook.mohamed.wishesbook.Comman.HttpClient;
import com.wishbook.mohamed.wishesbook.Comman.SaveSession;
import com.wishbook.mohamed.wishesbook.Entitis.CallBackPost;
import com.wishbook.mohamed.wishesbook.Entitis.Callback;
import com.wishbook.mohamed.wishesbook.Entitis.SessionInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class SignIn extends AppCompatActivity {
    private Button signIn;
    private EditText Email,Pass;
    private TextView _newAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email =(EditText)findViewById(R.id.email);
        Pass =(EditText)findViewById(R.id.password);
        signIn=(Button) findViewById(R.id.signinbutton);
        _newAccount=(TextView)findViewById(R.id.signUp);
        _newAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUp();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                StartSignIn();
            }
        });

    }


    public void openSignUp(){
        Intent intent =new Intent(this,SignUp.class);
        startActivity(intent);
    }
    public void StartSignIn(){
        JSONObject jsonParams= new JSONObject();
        try {
            int z;
            jsonParams.put("Mail",Email.getText().toString());
            jsonParams.put("passWord",Pass.getText().toString());
            HttpClient.post(this, "signin", jsonParams, new CallBackPost() {
                @Override
                public void onResponse(byte[] responseBody) {
                    try {
                        String Y = new String(responseBody, "UTF-8");
                        if(!Y.equals("error:notFound")){
                            JSONObject jsonArr = new JSONObject(Y);
                            String Sid = jsonArr.get("sid").toString();
                            String name=jsonArr.get("name").toString();
                            String id=jsonArr.get("id").toString();
                            SessionInfo.setSid(Sid);
                            SessionInfo.setId(id);
                            SessionInfo.setName(name);
                            SaveSession.saveCreadintiol(Sid,id,name,getApplicationContext());
                            openMain();
                            }
                        else{
                            erroMasage();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Callback<Integer>() {
                @Override
                public void onresponse() {

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void openMain(){
        Intent intent =new Intent(this,MainPage.class);
        startActivity(intent);
    }
    public void erroMasage(){
        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
    }
}
