package dynamiclistview.infosys.com.dynamiclistview;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.content.Context;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
 *
 * DataAdapter.java - Adapter class for
 * @author  Abhinav_Kumar21
 * @version 1.0
 * @Description Adapter class contain default functions to add elements to UI. RowElement model class as ArrayList in constructor
 *
 *
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private ArrayList<RowElement> android;
    private Context context;

    public DataAdapter(Context context,ArrayList<RowElement> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }


    /*
      onBindViewHolder -  This will be used for lazy loading. Picasso Lib is loading the images only when there is a valid image
      on server to load. Otherwise the entire space will be taken up by Text in a row.
    */

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        final DataAdapter.ViewHolder mviewHolder = viewHolder;

       // if(android.get(i).getTitle()!=null && android.get(i).getDescription()!=null && android.get(i).getImageHref()!=null )
        {
           //if(android.get(i).getTitle()!=null)
              mviewHolder.tv_name.setText(android.get(i).getTitle());

            //if(android.get(i).getDescription()!=null)
              mviewHolder.tv_version.setText(android.get(i).getDescription());

            //if(android.get(i).getImageHref()!=null)
              Picasso.with(context).load(android.get(i).getImageHref()).resize(500, 500).into(mviewHolder.tv_api_level);
        }
    }


    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version;
        private ImageView tv_api_level;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_version = (TextView)view.findViewById(R.id.tv_version);
            tv_api_level = (ImageView)view.findViewById(R.id.tv_api_level);

        }
    }


}
