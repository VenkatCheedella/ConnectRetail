package com.example.venkat.connectr1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.utilities.MyUtility;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rambabu on 6/13/2015.
 */


public class Adapter_DiscountedProductsRecyclerView extends RecyclerView.Adapter<Adapter_DiscountedProductsRecyclerView.ViewHolder> {

    private List<Map<String, ?>> dataSet;
    private Context context;
    OnItemClickListener mItemClickListener;
    LruCache<String, Bitmap> eitemImageCacheMemory;
    TextView oldPrice;
    TextView newPrice;
    TextView vdiscount;


    public Adapter_DiscountedProductsRecyclerView(Context myContext, List<Map<String, ?>> myDataSet) {
        dataSet = myDataSet;
        context = myContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView vIcon;
        private TextView vTitle;
        private TextView vDescription;
        private RatingBar vRatingBar;



        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.image);
            vTitle = (TextView) v.findViewById(R.id.title);
            newPrice = (TextView) v.findViewById(R.id.offerprice);
            oldPrice = (TextView) v.findViewById(R.id.costPrice);
            vdiscount = (TextView) v.findViewById(R.id.discount);
          //  vRatingBar = (RatingBar) v.findViewById(R.id.ratingBar);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });


        }

        public void bindMovieData(Map<String, ?> movie) {
            vTitle.setText((String) movie.get("name"));
//            vIcon.setImageResource((Integer) movie.get("thumbnailImage"));
//            Double dRating = (Double)movie.get("rating");
//            float fRating = dRating.floatValue();
//            vRatingBar.setRating(fRating);
            String url = (String) movie.get("thumbnailImage");
            double a = (Double)movie.get("salePrice");
            double b = (Double)movie.get("msrp");
            if(b == 0.00)
            {
                b = 1.1 * a;
            }
            double discount = (((b/a)*100)-100);
            DecimalFormat df = new DecimalFormat("#.#");
            String s = df.format(discount);
            String b1 = df.format(b);





            Bitmap bitmap = eitemImageCacheMemory.get(url);
            if (bitmap != null) {
                vIcon.setImageBitmap(bitmap);
            } else {
                MyImageDownloadAsync imgDownload = new MyImageDownloadAsync(vIcon);
                imgDownload.execute(new String[]{url});
            }
            newPrice.setText("OfferPrice: " + String.valueOf(movie.get("salePrice")));
            oldPrice.setText("OldPrice: "+b1);
            vdiscount.setText(s+" % Off");

        }


    }


    @Override
    public Adapter_DiscountedProductsRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardview_discounted_products, parent, false);
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
    public int getItemViewType(int position) {
//        Map<String,?> movie = dataSet.get(position);
//        int p;
//        Double dTempRating = (Double)movie.get("rating");
//        if(dTempRating>=providedRating)
//        p = 0;
//        else p = 1;
        return position;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Map<String, ?> movie = dataSet.get(i);
        viewHolder.bindMovieData(movie);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;

    }

    public interface OnListItemSelectedListener {
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

