package dynamiclistview.infosys.com.dynamiclistview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

/**
 * Created by Abhinav_Kumar21 on 3/24/2017.
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

        if (mviewHolder.tv_name != null && mviewHolder.tv_version != null && android.get(i).getImageHref() != null) {

            if(mviewHolder.tv_name != null)
                mviewHolder.tv_name.setText(android.get(i).getTitle());

            if(mviewHolder.tv_version != null)
                mviewHolder.tv_version.setText(android.get(i).getDescription());
            // viewHolder.tv_api_level.setText(android.get(i).getImageHref());

            // viewHolder.tv_android.setText(android_versions.get(i).getAndroid_version_name());
            System.out.println("URL ::"+i+ android.get(i).getImageHref()   );
            if( android.get(i).getImageHref() != null) {

               // Picasso.with(context).load(android.get(i).getImageHref()).into(viewHolder.tv_api_level);


                Picasso.Builder builder = new Picasso.Builder(context);


                builder.listener(new Picasso.Listener()
                {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
                    {
                        mviewHolder.tv_api_level.setVisibility(View.GONE);
                    }
                });
                builder.build().load(android.get(i).getImageHref()).resize(200, 200).into(mviewHolder.tv_api_level);

            }


            //load image with picasso
           // if (mSubredditItems.get(position).getUrl() != null)
           //     Picasso.with(mCntx).load(mSubredditItems.get(position).getUrl()).into(holder.pic);
            //holder._pic.setImage(ImageSource.uri(mSubredditItems.get(position).getUrl()));


            //int proportionalHeight = UIUtil.containerHeight((MainActivity) mCntx);
           // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, proportionalHeight); // (width, height)
           // viewHolder.container.setLayoutParams(params);



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
