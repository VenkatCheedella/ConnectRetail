package com.example.venkat.connectr1.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.activities.Activity_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager;
import com.example.venkat.connectr1.jsonlocal.NewArrivalProductsDataJsonLocal;
import com.example.venkat.connectr1.utilities.MyUtility;

import java.lang.ref.WeakReference;


/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment_hompage_viewflipper extends Fragment {

    private ViewFlipper mViewFlipper1;
    private ViewFlipper mViewFlipper2;
    private ViewFlipper mViewFlipper3;

    private ImageView image;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private ImageView image9;
    private ImageView image10;
    private ImageView viewmore;

    public Fragment_hompage_viewflipper() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set up a progress dialog

        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        mViewFlipper1 = (ViewFlipper) v.findViewById(R.id.viewFlipper1);
        mViewFlipper2 = (ViewFlipper) v.findViewById(R.id.viewFlipper2);
        mViewFlipper3 = (ViewFlipper) v.findViewById(R.id.viewFlipper3);
        NewArrivalProductsDataJsonLocal mvData = new NewArrivalProductsDataJsonLocal();
        mvData.loadLocalMovieDataJson(getActivity());
        int i = 0;
        while (i < mvData.getSize() - 3) {
            LinearLayout.LayoutParams flipper_layout_params = new LinearLayout.LayoutParams(250, 600);
            LinearLayout flipper1ImgesLayout = new LinearLayout(getActivity());
            flipper1ImgesLayout.setOrientation(LinearLayout.HORIZONTAL);
            flipper1ImgesLayout.setLayoutParams(flipper_layout_params);

            LinearLayout flipper2ImgesLayout = new LinearLayout(getActivity());
            flipper2ImgesLayout.setOrientation(LinearLayout.HORIZONTAL);
            flipper2ImgesLayout.setLayoutParams(flipper_layout_params);

            LinearLayout flipper3ImgesLayout = new LinearLayout(getActivity());
            flipper3ImgesLayout.setOrientation(LinearLayout.HORIZONTAL);
            flipper3ImgesLayout.setLayoutParams(flipper_layout_params);

            ImageView img1 = new ImageView(getActivity());
            ImageView img2 = new ImageView(getActivity());
            ImageView img3 = new ImageView(getActivity());
            LinearLayout.LayoutParams imgparams = new LinearLayout.LayoutParams(240, 400);
            img1.setLayoutParams(imgparams);
            img2.setLayoutParams(imgparams);
            img3.setLayoutParams(imgparams);
            //img1.setImageResource((Integer) mvData.getItem(i).get("largeImage"));

            String url = (String) mvData.getItem(i).get("mediumImage");
            MyDownloadImageAsyncTask task = new MyDownloadImageAsyncTask(img1);
            task.execute(new String[]{url});
           // img2.setImageResource((Integer) mvData.getItem(i + 1).get("largeImage"));
           // img3.setImageResource((Integer) mvData.getItem(i + 2).get("largeImage"));

            String url1 = (String) mvData.getItem(i+1).get("mediumImage");
            MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(img2);
            task1.execute(new String[]{url1});

            String url2 = (String) mvData.getItem(i+2).get("mediumImage");
            MyDownloadImageAsyncTask task2 = new MyDownloadImageAsyncTask(img3);
            task2.execute(new String[]{url2});

            flipper1ImgesLayout.addView(img1);
            flipper2ImgesLayout.addView(img2);
            flipper3ImgesLayout.addView(img3);
            mViewFlipper1.addView(flipper1ImgesLayout);
            mViewFlipper2.addView(flipper2ImgesLayout);
            mViewFlipper3.addView(flipper3ImgesLayout);

            i = i + 1;
        }
//        while (i < mvData.getSize() - 3) {
//            LinearLayout flipperImgesLayout = new LinearLayout(getActivity());
//            flipperImgesLayout.setOrientation(LinearLayout.HORIZONTAL);
//            LinearLayout.LayoutParams flipper_layout_params = new LinearLayout.LayoutParams(400,600);
//            ImageView img1 = new ImageView(getActivity());
//            ImageView img2 = new ImageView(getActivity());
//            ImageView img3 = new ImageView(getActivity());
//            LinearLayout.LayoutParams imgparams = new LinearLayout.LayoutParams(240, 400);
//            img1.setLayoutParams(imgparams);
//            img2.setLayoutParams(imgparams);
//            img3.setLayoutParams(imgparams);
//            img1.setImageResource((Integer) mvData.getItem(i).get("image"));
//            img2.setImageResource((Integer) mvData.getItem(i + 1).get("image"));
//            img3.setImageResource((Integer) mvData.getItem(i + 2).get("image"));
//            flipperImgesLayout.addView(img1);
//            flipperImgesLayout.addView(img2);
//            flipperImgesLayout.addView(img3);
//            mViewFlipper.addView(flipperImgesLayout);
//            i = i + 3;
//        }
        mViewFlipper1.setAutoStart(true);
        mViewFlipper1.setFlipInterval(3000);

        mViewFlipper2.setAutoStart(true);
        mViewFlipper2.setFlipInterval(3000);

        mViewFlipper3.setAutoStart(true);
        mViewFlipper3.setFlipInterval(3000);

        mViewFlipper1.startFlipping();
        mViewFlipper2.startFlipping();
        mViewFlipper3.startFlipping();

        //mViewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        mViewFlipper1.setInAnimation(getActivity(), R.animator.in_from_right);
        mViewFlipper1.setOutAnimation(getActivity(), R.animator.out_to_left);

        mViewFlipper2.setInAnimation(getActivity(), R.animator.in_from_right);
        mViewFlipper2.setOutAnimation(getActivity(), R.animator.out_to_left);

        mViewFlipper3.setInAnimation(getActivity(), R.animator.in_from_right);
        mViewFlipper3.setOutAnimation(getActivity(), R.animator.out_to_left);
        //mViewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);

        viewmore = (ImageView) v.findViewById(R.id.image_view_more);
        viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager.class);
                intent.putExtra("openPage",5);
                startActivity(intent);
            }
        });

        image = (ImageView) v.findViewById(R.id.horizontalScrollImage1);
        image.setImageResource(R.drawable.image1);
        image1 = (ImageView) v.findViewById(R.id.horizontalScrollImage2);
        image1.setImageResource(R.drawable.image1);
        image2 = (ImageView) v.findViewById(R.id.horizontalScrollImage3);
        image2.setImageResource(R.drawable.image1);
        image3 = (ImageView) v.findViewById(R.id.horizontalScrollImage4);
        image3.setImageResource(R.drawable.image1);
        image4 = (ImageView) v.findViewById(R.id.horizontalScrollImage5);
        image4.setImageResource(R.drawable.view_more);
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager.class);
                intent.putExtra("openPage",10);
                startActivity(intent);
            }
        });


        image6 = (ImageView) v.findViewById(R.id.horizontalScrollImage6);
        image6.setImageResource(R.drawable.image1);
        image7 = (ImageView) v.findViewById(R.id.horizontalScrollImage7);
        image7.setImageResource(R.drawable.image1);
        image8 = (ImageView) v.findViewById(R.id.horizontalScrollImage8);
        image8.setImageResource(R.drawable.image1);
        image9 = (ImageView) v.findViewById(R.id.horizontalScrollImage9);
        image9.setImageResource(R.drawable.image1);
        image10 = (ImageView) v.findViewById(R.id.horizontalScrollImage10);
        image10.setImageResource(R.drawable.view_more);
        image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_DiscountedProducts_TopSellingProducts_NewArrivals_ViewPager.class);
                intent.putExtra("openPage",15);
                startActivity(intent);
            }
        });

        return v;
    }

    private class MyDownloadImageAsyncTask extends AsyncTask<String,Void,Bitmap>
    {
        private final WeakReference<ImageView> imageViewReference;
        public MyDownloadImageAsyncTask(ImageView imv) {
            imageViewReference = new WeakReference<ImageView>(imv);
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            for (String url : urls){
                bitmap = MyUtility.downloadImage(url);
            }
            return bitmap;
        }
        // sets the bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(imageViewReference!=null && bitmap!=null)
            {
                final ImageView imageView = imageViewReference.get();
                if(imageView!=null)
                {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
