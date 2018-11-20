package com.example.venkat.connectr1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.utilities.MyUtility;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rambabu on 6/29/2015.
 */
public class Adapter_NewArrivalProductsRecyclerView extends  RecyclerView.Adapter<Adapter_NewArrivalProductsRecyclerView.ViewHolder> {

    private List<Map<String,?>> dataSet;
    private Context context;
    OnItemClickListener mItemClickListener;
    LruCache<String, Bitmap> eitemImageCacheMemory;

    public Adapter_NewArrivalProductsRecyclerView(Context myContext, List<Map<String, ?>> myDataSet) {
        dataSet = myDataSet;
        context = myContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView vIcon;
        private TextView vTitle;


        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.image_grid);
            vTitle = (TextView) v.findViewById(R.id.title_grid);
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(mItemClickListener!=null)
                    {
                        mItemClickListener.onItemClick(v,getLayoutPosition());
                    }
                }
            });
        }

        public void bindMovieData(Map<String, ?> movie) {

            String url = (String) movie.get("thumbnailImage");
            Bitmap bitmap = eitemImageCacheMemory.get(url);
            if (bitmap != null) {
                vIcon.setImageBitmap(bitmap);
            } else {
                MyImageDownloadAsync imgDownload = new MyImageDownloadAsync(vIcon);
                imgDownload.execute(new String[]{url});
            }
            vTitle.setText((String) movie.get("name"));
         //   vIcon.setImageResource((Integer) movie.get("thumbnailImage"));
        }



    }





    @Override
    public Adapter_NewArrivalProductsRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
                   v = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.cardview_new_arrival_products,parent,false);



        ViewHolder vh = new ViewHolder(v);
        if (eitemImageCacheMemory == null) {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int cacheSize = maxMemory / 8;
            eitemImageCacheMemory = new LruCache<String, Bitmap>(cacheSize) {

                @Override
                protected int sizeOf(String key, Bitmap image) {
                    return image.getByteCount() / 1024;
                }
            };
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position)
    {

        return position;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Map<String,?> movie = dataSet.get(i);
        viewHolder.bindMovieData(movie);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface OnItemClickListener{
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener){
        this.mItemClickListener = mItemClickListener;

    }

    public interface OnListItemSelectedListener{
        public void onListItemSelected(int position, HashMap<String, ?> movie);
    }

    private class MyImageDownloadAsync extends AsyncTask<String, Void, Bitmap> {

        private final WeakReference<ImageView> item_img_ref;

        public MyImageDownloadAsync(ImageView item_img) {
            this.item_img_ref = new WeakReference<ImageView>(item_img);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap img_bitmap = null;
            for (String url : urls) {
                img_bitmap = MyUtility.downloadImage(url);
                if (img_bitmap != null) {
                    eitemImageCacheMemory.put(url, img_bitmap);
                }
            }
            return img_bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            final ImageView imgView = item_img_ref.get();
            if (imgView != null) {
                imgView.setImageBitmap(bitmap);
            }
        }
    }
}

