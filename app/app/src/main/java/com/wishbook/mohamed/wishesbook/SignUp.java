package com.wishbook.mohamed.wishesbook;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.wishbook.mohamed.wishesbook.Comman.HttpClient;
import com.wishbook.mohamed.wishesbook.Entitis.CallBackPost;
import com.wishbook.mohamed.wishesbook.Entitis.Callback;
import com.wishbook.mohamed.wishesbook.Validation.EmailValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp extends AppCompatActivity {
    private EditText _name,_mail,_pass,_repass,_bdate;
    private Button _Register;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        _name=(EditText)findViewById(R.id.name);
        _mail=(EditText)findViewById(R.id.email);
        _pass=(EditText)findViewById(R.id.pass);
        _repass=(EditText)findViewById(R.id.repass);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        _bdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SignUp.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        _Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        _bdate.setText(sdf.format(myCalendar.getTime()));
    }
    public void Masage(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();}
    private void signUpClicked()
    {
        String Email= _mail.getText().toString();
        String Pass= _pass.getText().toString();
        String conPass= _repass.getText().toString();
        String date=_bdate.getText().toString();

        String name= _name.getText().toString();

        if (!EmailValidator.emailValidator(Email)){
            Masage("Invalid email address");
            return;}
        if(!Pass.equals(conPass)){
            Masage("the password not match");
            return;
        }
        if(name.equals(""))
        {
            Masage("name required");
            return;
        }


        JSONObject jsParam=new JSONObject();
        try {
            jsParam.put("Name",name);
            jsParam.put("Mail",Email);
            jsParam.put("passWord",Pass);
            jsParam.put("Bdate",date);

            HttpClient.post(this, "user", jsParam, new CallBackPost() {
                @Override
                public void onResponse(byte[] responseBody) {
                    try {
                        String Y = new String(responseBody, "UTF-8");
                        JSONObject jsonobj = new JSONObject(Y);
                        String msg = jsonobj.getString("msg");
                        if(!msg.equals("Badinput")){
                            Masage(msg);

                           /* String Sid = jsonArr.get("SID").toString();
                            String name=jsonArr.get("name").toString();
                            String id=jsonArr.get("ID").toString();
                            SessionInfo.setSid(Sid);
                            SessionInfo.setId(id);
                            SessionInfo.setName(name);
                            openApeal();*/}
                        else{
                            Masage(Y);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Callback<Integer>() {
                @Override
                public void onresponse() { }});
        }catch (JSONException je){
            je.printStackTrace();
        }
    }

}
