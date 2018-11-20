package com.example.venkat.connectr1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.cloud.Data_CartObject;
import com.example.venkat.connectr1.utilities.MyUtility;
import com.parse.ParseQueryAdapter;

import java.lang.ref.WeakReference;

/**
 * Created by venkat on 8/6/2015.
 */
public class Adapter_OrderHistory extends ParseQueryAdapter<Data_CartObject> {

    LruCache<String, Bitmap> eitemImageCacheMemory;
    private ImageView trash_img;
    Data_CartObject currObj;

    public Adapter_OrderHistory(Context context, QueryFactory<Data_CartObject> queryFactory) {
        super(context, queryFactory);
    }

    @Override
    public View getItemView(Data_CartObject object, View v, ViewGroup parent) {
        ViewHolder holder;
        currObj = object;
        Log.d("Activity-OrderHistory", "ItemViewCalled");
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
        if(v == null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart, parent, false);
            holder = new ViewHolder();
            holder.itemDescription = (TextView)v.findViewById(R.id.itemName);
            holder.itemCost = (TextView)v.findViewById(R.id.itemPrice);
            holder.itemImage = (ImageView)v.findViewById(R.id.itemImg);
          //  trash_img = (ImageView)v.findViewById(R.id.rem_item);
            v.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)v.getTag();
        }
        TextView itemDesc = holder.itemDescription;
        TextView itemPrice = holder.itemCost;
        ImageView itemImg = holder.itemImage;
        itemDesc.setText((String)object.getProductName());
        Double itemPrc = (Double)object.getUnitCost();
        itemPrice.setText((String) itemPrc.toString());
        String img_url = object.getImageURL();
        Bitmap bitmap = eitemImageCacheMemory.get(img_url);
        if(bitmap != null){
            itemImg.setImageBitmap(bitmap);
        }
        else
        {
            MyImageDownloadAsync imgDownload = new MyImageDownloadAsync(itemImg);
            imgDownload.execute(new String[]{img_url});
        }


        // Remove an item from the Cart

        trash_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currObj.deleteInBackground();
                this.notifyAll();
            }
        });


        return v;
    }




    private static class ViewHolder{
        TextView itemDescription;
        ImageView itemImage;
        TextView itemCost;
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
