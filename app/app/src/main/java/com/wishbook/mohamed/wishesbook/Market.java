package com.wishbook.mohamed.wishesbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.wishbook.mohamed.wishesbook.Comman.HttpClient;
import com.wishbook.mohamed.wishesbook.Entitis.CallBackjson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Market extends Fragment {
    Spinner _catSpin;
    List<String> _spinData;
    List<String> _catID;
    ArrayAdapter<String> _spinerAdaptor;
    JSONArray _product;
    ListView _productList;

    public Market() {
        // Required empty public constructor
    }

    private void _loadSpinData(){
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {

                _spinData= new ArrayList<String>();
                _catID=new ArrayList<String>();
                for(int i=0;i<j.length();i++){
                    JSONObject x= null;
                    try {
                        x = j.getJSONObject(i);
                        String name= x.getString("Name");
                        String id=x.getString("CID");
                        _spinData.add(name);
                        _catID.add(id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                _spinerAdaptor=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, _spinData);
                _spinerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                _catSpin.setAdapter(_spinerAdaptor);
                _spinerAdaptor.notifyDataSetChanged();
            }
        },"category");
    }
    public void updateList(int postion){
        String apiPath = "product/"+_catID.get(postion);
        HttpClient.Get(new CallBackjson() {
            @Override
            public void onResponse(JSONArray j) {
                _product=j;
                CustomAdaptor Custom=new CustomAdaptor();
                _productList.setAdapter(Custom);
                //load.setVisibility(View.GONE);
                //_productList.setVisibility(View.VISIBLE);
            }
        },apiPath);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View marketView= inflater.inflate(R.layout.fragment_market, container, false);
        _catSpin =(Spinner)marketView.findViewById(R.id.CatSpin);
        _productList=(ListView)marketView.findViewById(R.id.Products);
        _catSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateList(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _loadSpinData();
        return marketView;
    }

    public class CustomAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return _product.length();
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
                JSONObject n = _product.getJSONObject(position);
                convertView = getLayoutInflater().inflate(R.layout.product_card, null);

                TextView price = (TextView) convertView.findViewById(R.id.price);
                TextView title = (TextView) convertView.findViewById(R.id.Title);


                //  TextView UID=(TextView)convertView.findViewById(R.id.textId);
                //  RadioButton Closed=(RadioButton)convertView.findViewById(R.id.Closed);
                // ApealView.Aid.add(n.getString("AID"));

                 String Title=n.getString("DSC");
                 String Price=n.getString("price");
                 title.setText(Title);
                 price.setText(Price);
            return convertView;} catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
