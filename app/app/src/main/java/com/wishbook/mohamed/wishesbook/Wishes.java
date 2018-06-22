package com.wishbook.mohamed.wishesbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wishbook.mohamed.wishesbook.Comman.HttpClient;
import com.wishbook.mohamed.wishesbook.Entitis.CallBackjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Wishes extends Fragment {
    private String _UID;
    ListView _list;
    ProgressBar progressBar;
    JSONArray _wishes;

  public void set_UID(String uid){
      _UID=uid;
  }
    public Wishes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View V=inflater.inflate(R.layout.fragment_wishes, container, false);
       _list = (ListView) V.findViewById(R.id.Wishlist);
       progressBar = (ProgressBar)V.findViewById(R.id.progressBar3);
        return V;
    }
    public void onselect(){
        String apiPath = "iwish/"+_UID;
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                _wishes=j;
                Wishes.CustomAdaptor Custom=new Wishes.CustomAdaptor();
                _list.setAdapter(Custom);
                progressBar.setVisibility(View.GONE);
                _list.setVisibility(View.VISIBLE);
            }
        },apiPath);
    }
    public class CustomAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return _wishes.length();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            try {
                JSONObject n = _wishes.getJSONObject(position);
                convertView = getLayoutInflater().inflate(R.layout.wish_card, null);
                TextView title = (TextView) convertView.findViewById(R.id.Title);
                //  TextView UID=(TextView)convertView.findViewById(R.id.textId);
                //  RadioButton Closed=(RadioButton)convertView.findViewById(R.id.Closed);
                // ApealView.Aid.add(n.getString("AID"));
                String Wish=n.getString("Wish");
                title.setText(Wish);
                return convertView;} catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
