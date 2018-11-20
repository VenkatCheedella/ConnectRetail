package com.example.venkat.connectr1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.animations.AnimateUtils;
import com.example.venkat.connectr1.utilities.MyUtility;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;


/**
 * Created by venkat on 7/27/2015.
 */
public class Adapter_ElectronicsRecyclerView extends RecyclerView.Adapter<Adapter_ElectronicsRecyclerView.ViewHolder> {

    private List<Map<String, ?>> electronicsList;
    private Context context;
    OnItemClickListner eitemClickListner;
    LruCache<String, Bitmap> eitemImageCacheMemory;
    int previousPosition = 0;


    public Adapter_ElectronicsRecyclerView(List<Map<String, ?>> electronicsList, Context context) {
        this.electronicsList = electronicsList;
        this.context = context;
    }

    public Object getItem(int position) {
        return electronicsList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView tnail_item_img;
        public TextView item_desc;
        public TextView item_name;

        public ViewHolder(View view) {
            super(view);
            tnail_item_img = (ImageView) view.findViewById(R.id.item_img);
            item_name = (TextView) view.findViewById(R.id.item_name);
            item_desc = (TextView) view.findViewById(R.id.item_desc);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (eitemClickListner != null) {
                        eitemClickListner.onItemClick(v, getPosition());
                    }
                }
            });

        }

        public void bindeitem(Map<String, ?> eitem) {
            String url = (String) eitem.get("thumbnailImage");
            Bitmap bitmap = eitemImageCacheMemory.get(url);
            if (bitmap != null) {
                tnail_item_img.setImageBitmap(bitmap);
            } else {
                MyImageDownloadAsync imgDownload = new MyImageDownloadAsync(tnail_item_img);
                imgDownload.execute(new String[]{url});
            }
            // item_desc.setText((String) eitem.get("shortDescription"));
            item_name.setText((String) eitem.get("name"));
        }
    }

    @Override
    public Adapter_ElectronicsRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_electronics_view, parent, false);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map<String, ?> e_item = electronicsList.get(position);
        holder.bindeitem(e_item);
        AnimateUtils animateUtils = new AnimateUtils();


        if (position > previousPosition) {
            animateUtils.animateY(holder, true);
        } else {
            animateUtils.animateY(holder, false);
        }
        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return electronicsList.size();
    }


    public interface OnItemClickListner {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListner(final OnItemClickListner eitemClickListner) {
        this.eitemClickListner = eitemClickListner;
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

