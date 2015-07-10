package com.aditya.letmedrive.views;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aditya.letmedrive.R;

/**
 * Created by devad_000 on 09-07-2015.
 * View Holder
 */
public class ContactListView extends RecyclerView.ViewHolder {
    TextView tv_contactName;
    TextView tv_contactNumber;
    TextView tv_shortcode;
    RelativeLayout container;
    public ContactListView(View itemView) {
        super(itemView);
        tv_contactName = (TextView) itemView.findViewById(R.id.tv_contactName);
        tv_contactNumber = (TextView) itemView.findViewById(R.id.tv_contactNumber);
        tv_shortcode = (TextView) itemView.findViewById(R.id.tv_shortcode);
        container = (RelativeLayout) itemView.findViewById(R.id.container);
    }

    public RelativeLayout getContainer() {
        return container;
    }

    public TextView getTv_contactName() {
        return tv_contactName;
    }

    public TextView getTv_contactNumber() {
        return tv_contactNumber;
    }


    public TextView getTv_shortcode() {
        return tv_shortcode;
    }
}
