package com.aditya.letmedrive;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.aditya.letmedrive.adapters.ContactListAdapter;
import com.aditya.letmedrive.pojo.ContactData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RelativeLayout relativeLayout;
    private RelativeLayout relativeLayout_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        relativeLayout = (RelativeLayout) findViewById(R.id.loaderGroup);
        FetchContactTask fetchContactTask = new FetchContactTask();
        fetchContactTask.execute();
    }


    /**
     * This section is used for building the immersive mode in version 19 and above
     * So it is not applicable for version below that.
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (hasFocus) {
                getWindow().getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    class FetchContactTask extends AsyncTask<Void, Integer, ArrayList<ContactData>> {
        int count = 0;
        String contactName;
        String contactNumber;
        String emailId;
        ArrayList<ContactData> arrayList = new ArrayList<>();
        ContactData contactData;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        int size = phones.getCount();
        MainActivity mainActivity;

        @Override
        protected ArrayList<ContactData> doInBackground(Void... params) {
            while (phones.moveToNext()) {
                if (phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) != null ||
                        phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)).length() == 0) {
                    contactName = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                } else {
                    contactName = "N/A";
                }
                if (phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) != null ||
                        phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).length() == 0) {
                    contactNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                } else {
                    contactNumber = "N/A";
                }
                contactData = new ContactData(contactName, contactNumber);

                arrayList.add(contactData);
                onProgressUpdate((int) (((double) count / size) * 100));
                count++;
            }


            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<ContactData> contactDatas) {
            phones.close();
            ContactListAdapter contactListAdapter = new ContactListAdapter(MainActivity.this);
            contactListAdapter.setContactList(contactDatas);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(contactListAdapter);
            relativeLayout.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {

            }
        }

        @Override
        protected void onPreExecute() {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }
}
