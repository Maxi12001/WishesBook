package com.wishbook.mohamed.wishesbook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.wishbook.mohamed.wishesbook.Entitis.SessionInfo;

public class MainPage extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private  FragmantAdaptor myAdaptor;
    private ViewPager myViewPager;
    private  String UID;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    ActionBar actionbar;
    private int currentPostion=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar=(Toolbar)findViewById(R.id.apealBar);
        setSupportActionBar(toolbar);
        actionbar=getSupportActionBar();
        actionbar.setTitle("WishesBook");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_list_black_24dp);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.myFragment);
        navView=(NavigationView)findViewById(R.id.nav_view);
        navListner();
        myViewPager=(ViewPager)findViewById(R.id.FragmantView);
        setupPagerAdaptor(myViewPager);
        //setAlarm();
    }

    private void setupPagerAdaptor(ViewPager viewPager){
        myAdaptor=new FragmantAdaptor(getSupportFragmentManager());

       ManuFragment Manu= new ManuFragment();
      Wishes wishes =new Wishes();
        myAdaptor.addFragmant(Manu);
        myAdaptor.addFragmant(wishes);
        viewPager.setAdapter(myAdaptor);
    }
    public void setUID(String ID){
        UID =ID;
    }
    public void setFragment(int postion){
       if(postion==0){
           /*( (Apeals)  myAdaptor.getItem(postion)).setc_id_name(CatID,Catname);
           ( (Apeals)  myAdaptor.getItem(postion)).updateList();*/
       }
       else if (postion ==1){
           ( (Wishes) myAdaptor.getItem(postion)).set_UID(UID);
           ( (Wishes) myAdaptor.getItem(postion)).onselect();
       }
       else if(postion==3){
           //( (Apeals)  myAdaptor.getItem(postion)).updateList();
       }
        myViewPager.setCurrentItem(postion);
       currentPostion=postion;
    }

    @Override
    public void onBackPressed(){
        if(currentPostion>0){
            int pos = currentPostion==3 ? 0 : currentPostion-1;
            myViewPager.setCurrentItem(pos);
        currentPostion=pos;
        }
        else
            super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int X =item.getItemId();
        if(X==android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(),"Not implemnted Yet", Toast.LENGTH_SHORT).show();
            return super.onOptionsItemSelected(item);}


    }
    public void openNActivity(int ActivityNo){
        Intent intent ;
        if (ActivityNo==1){
            intent=new Intent(this,profile.class);
        }
            //intent =new Intent(this,newApeal2.class);
        else
            intent=new Intent(this,profile.class);
        intent.putExtra("UID",SessionInfo.getId());
        startActivity(intent);
    }
    public void navListner(){
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                int id =item.getItemId();
                switch (id){
                    case R.id.RemindList:
                       setFragment(3);
                        break;
                    case R.id.Myprofile:
                        openNActivity(3);
                        break;
                    case R.id.Category:
                        setFragment(0);
                        break;
                    default: Toast.makeText(getApplicationContext(),"Not implemnted Yet", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
 /*   public void notification(){
        Intent intent =new Intent(this,last24.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder mBuilde =new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_info_black_24dp)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
    }*/
/*public void setAlarm(){

    Intent intent = new Intent(this, AlertReciver.class);
    alarmIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 123456, intent, 0);
    alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);
    alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() +
                    60 * 1000, alarmIntent);
    Toast.makeText(this, "Alarm set in 60 seconds",Toast.LENGTH_LONG).show();
}*/
public  void setTitle(String nTitle){
        actionbar.setTitle(nTitle);
}
}
