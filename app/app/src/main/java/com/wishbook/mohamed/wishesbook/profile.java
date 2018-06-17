package com.wishbook.mohamed.wishesbook;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wishbook.mohamed.wishesbook.Comman.HttpClient;
import com.wishbook.mohamed.wishesbook.Entitis.CallBackjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profile extends AppCompatActivity {
    public TextView Case,BloodType,TrustedBy,trustedIn,name;
    JSONArray ProfileApeal;
    ListView apealList;
    ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String uid=getIntent().getStringExtra("UID");
        setContentView(R.layout.activity_profile);
        name = (TextView) findViewById(R.id.name);
        apealList = (ListView) findViewById(R.id.listview);
        load=(ProgressBar)findViewById(R.id.progressBar);
        load.getIndeterminateDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                try {
                    JSONObject i = j.getJSONObject(0);
                    name.setText(i.getString("name"));
                    BloodType.setText(i.getString("bloodtype"));
                    Case.setText(i.getString("noOfapeal"));
                    trustedIn.setText(i.getString("trustedin"));
                    TrustedBy.setText(i.getString("trustedBy"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, "profile/profileInfo?uid=" + uid);

        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                ProfileApeal=j;
                profile.CustomAdaptor customAdaptor=new profile.CustomAdaptor();
                apealList.setAdapter(customAdaptor);
                load.setVisibility(View.GONE);
                apealList.setVisibility(View.VISIBLE);
            }
        },"profile/GetUserApeal?uid="+uid);
    }
    public class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return ProfileApeal.length();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            try {
                JSONObject n=ProfileApeal.getJSONObject(position);
                convertView=getLayoutInflater().inflate(R.layout.product_card,null);
                TextView title=(TextView)convertView.findViewById(R.id.Title);

                //  TextView UID=(TextView)convertView.findViewById(R.id.textId);
                //  RadioButton Closed=(RadioButton)convertView.findViewById(R.id.Closed);

              /*  ApealView.Aid.add(n.getString("AID"));
                name.setText( n.getString("name"));
                apeal.setText(n.getString("Content"));
                date.setText(n.getString("date"));
                title.setText(n.getString("Title"));
                */// UID.setText(n.getString("ID"));
                // Closed.setChecked(n.getBoolean("closed"));

                return convertView;
            } catch (JSONException e) {
                e.printStackTrace();

            }
            return null;

        }


    }

}
