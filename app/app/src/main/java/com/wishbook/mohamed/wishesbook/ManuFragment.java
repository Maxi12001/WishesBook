package com.wishbook.mohamed.wishesbook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManuFragment extends Fragment {

Button _friends,_wishlist,_market,_newWish;
    public ManuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_manu, container, false);
       _market=view.findViewById(R.id.Market);
       _market.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ((MainPage)getActivity()).setFragment(1);
           }
       });
       return view;
    }

}
