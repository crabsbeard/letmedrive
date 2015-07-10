package com.aditya.letmedrive.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.aditya.letmedrive.R;
import com.aditya.letmedrive.pojo.ContactData;
import com.aditya.letmedrive.views.ContactListView;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Created by devad_000 on 09-07-2015.
 *
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListView> {
    private LayoutInflater layoutInflater;
    private ArrayList<ContactData> contactDataArrayList;
    private Context context;
    private int lastPosition = -1;
    private static final int TYPE_HEADER = 0;

    public ContactListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setContactList(ArrayList<ContactData> contactList) {
        contactDataArrayList = contactList;
    }

    @Override
    public ContactListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.contact_view, viewGroup, false);
        return new ContactListView(view);
    }

    @Override
    public void onBindViewHolder(ContactListView contactListView, int i) {
        ContactData contactData = contactDataArrayList.get(i);
        contactListView.getTv_contactName().setText(contactData.getContactName());
        contactListView.getTv_contactNumber().setText(contactData.getContactNumber());
        contactListView.getTv_shortcode().setBackgroundColor(randomColor());
        contactListView.getTv_shortcode().setText(setShortCode(contactData.getContactName()));
    }

    private int randomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(96), random.nextInt(96), random.nextInt(96));
    }

    /**
     * Creates a really funky effect if the list is huge, which is our case, so not using animations
     */

    private void setAnimate(RelativeLayout container, int i) {
        if (i > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            container.startAnimation(animation);
            lastPosition = 1;
        }
    }

    private String setShortCode(String contactName) {
        if (contactName.equals("N/A")) {
            return contactName;
        }
        if (contactName.length() < 2) {
            return contactName.substring(0,2).toUpperCase();
        } else {

            return contactName.substring(0, 2).toUpperCase();
        }
    }

    @Override
    public int getItemCount() {
        return contactDataArrayList.size();
    }
}
