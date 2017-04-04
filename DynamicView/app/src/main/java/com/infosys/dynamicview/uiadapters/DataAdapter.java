package com.infosys.dynamicview.uiadapters;


import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.content.Context;


import com.infosys.dynamicview.R;
import com.infosys.dynamicview.model.RowElement;
import com.infosys.dynamicview.apisutil.Util;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 * <p>
 * DataAdapter.java - Adapter class for
 *
 * @author Abhinav_Kumar21
 * @version 1.0
 * @Description Adapter class contain default functions to add elements to UI. RowElement model class as ArrayList in constructor
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<RowElement> arrRowElement;
    private Context context;

    public DataAdapter(Context context, ArrayList<RowElement> listArr) {
        this.arrRowElement = listArr;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(com.infosys.dynamicview.R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }


    /*
      onBindViewHolder -  This will be used for lazy loading. Picasso Lib is loading the images only when there is a valid image
      on server to load. Otherwise the entire space will be taken up by Text in a row.
    */

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        //this checks the values of row and calls the remove function if there is null data

        if (arrRowElement.get(i).getTitle() == null &&
                arrRowElement.get(i).getDescription() == null &&
                arrRowElement.get(i).getImageHref() == null) {

            remove(arrRowElement.get(i));
        }
        final DataAdapter.ViewHolder mviewHolder = viewHolder;

        if (arrRowElement.get(i).getTitle() != null) {
            mviewHolder.tv_title.setVisibility(View.VISIBLE);
            mviewHolder.tv_title.setText(arrRowElement.get(i).getTitle());
        } else {
            mviewHolder.tv_title.setVisibility(View.GONE);
        }


        if (arrRowElement.get(i).getDescription() != null) {
            mviewHolder.tv_description.setVisibility(View.VISIBLE);
            mviewHolder.tv_description.setText(arrRowElement.get(i).getDescription());
        } else {
            mviewHolder.tv_description.setVisibility(View.GONE);
        }


              /*
              This will check if image on server is valid or not. If the link is not valid image view will be removed from
              row and entire space will be taken up by other row elements
              */
        if (arrRowElement.get(i).getImageHref() != null) {

            Picasso.with(context).load(arrRowElement.get(i).getImageHref()).resize(Util.IMG_RESIZE_VALUE, Util.IMG_RESIZE_VALUE).into(mviewHolder.img_url_ref, new Callback() {
                @Override
                public void onSuccess() {
                    mviewHolder.img_url_ref.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError() {
                    mviewHolder.img_url_ref.setVisibility(View.GONE);

                }
            });

        } else {
            mviewHolder.img_url_ref.setVisibility(View.GONE);
        }


    }

    /*
       Remove the row which has all null values as
       list is dynamic and should not show anything not required
     */

    public void remove(RowElement item) {
        final int position = arrRowElement.indexOf(item);
        arrRowElement.remove(position);
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                notifyItemRemoved(position);
            }
        };

        handler.post(r);
    }


    @Override
    public int getItemCount() {
        // textViewNoData.setVisibility(android.size() > 0 ? View.GONE : View.VISIBLE);
        return arrRowElement.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_description;
        private ImageView img_url_ref;

        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView) view.findViewById(com.infosys.dynamicview.R.id.tv_rowtitle);
            tv_description = (TextView) view.findViewById(com.infosys.dynamicview.R.id.tv_rowdescription);
            img_url_ref = (ImageView) view.findViewById(com.infosys.dynamicview.R.id.img_url);
        }
    }
}
