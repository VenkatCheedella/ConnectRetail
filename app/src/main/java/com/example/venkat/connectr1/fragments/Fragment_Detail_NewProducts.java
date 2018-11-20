package com.example.venkat.connectr1.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.Activity_Youtube;
import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.activities.Activity_DescriptionDialog;
import com.example.venkat.connectr1.dialogfragments.DialogFragment_DescriptionDialog;
import com.example.venkat.connectr1.utilities.MyUtility;

import java.lang.ref.WeakReference;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Detail_NewProducts} interface
 * to handle interaction events.
 * Use the {@link Fragment_Detail_NewProducts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Detail_NewProducts extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView vImage;
    private ImageView vImage_tnail;
    private ImageView vImage_medium;
    private ImageView vImage_large;
    private ImageView vImage_tnail1;
    private ImageView vImage_medium1;
    private TextView vTitle;
    private TextView vBrandName;
    private TextView vItemId;
    private TextView vModelNumber;
    private TextView vColor;
    private TextView vSalePrice;
    private TextView vMSRP;
    private TextView vMaxItemsInOrder;
    private TextView vAvailability;
    private TextView vDescription;
    private RatingBar vRatingBar;
    private Double msrp;
    private ImageView youtubeButton;
    private ImageView shareButton;
    private String youtubeString;
    HashMap<String, ?> electronic_item;
    ShareActionProvider mShareActionProvider;
    View v;




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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Detail_Electronics.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Detail_NewProducts newInstance(String param1, String param2) {
        Fragment_Detail_NewProducts fragment = new Fragment_Detail_NewProducts();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_Detail_NewProducts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_new_products, container, false);

        vImage = (ImageView) rootView.findViewById(R.id.image_detail_electronics);
        vImage_tnail =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image1);
        vImage_tnail1 =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image4);
        vImage_medium =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image2);
        vImage_medium1 =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image5);
        vImage_large =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image3);
        vTitle = (TextView) rootView.findViewById(R.id.Title_detail_electronics);
        vBrandName = (TextView) rootView.findViewById(R.id.BrandName_detail_electronics);
        vItemId = (TextView) rootView.findViewById(R.id.ItemId_detail_electronics);

        vModelNumber = (TextView) rootView.findViewById(R.id.ModelNumber_detail_electronics);
        vColor = (TextView) rootView.findViewById(R.id.ItemColor_detail_electronics);
        vSalePrice = (TextView) rootView.findViewById(R.id.SalePrice_detail_electronics);
        vMSRP = (TextView) rootView.findViewById(R.id.MSRP_detail_electronics);

        vMaxItemsInOrder = (TextView) rootView.findViewById(R.id.MaximumItemsPerOrder_detail_electronics);

        vAvailability = (TextView) rootView.findViewById(R.id.ItemAvailability_detail_electronics);

        //vDescription = (TextView) rootView.findViewById(R.id.ItemDescription_detail_electronics);

        vRatingBar = (RatingBar) rootView.findViewById(R.id.CustomerRating_detail_electronics);

        youtubeButton = (ImageView) rootView.findViewById(R.id.youtube_image_detail_electronics);
        shareButton = (ImageView) rootView.findViewById(R.id.share_image_detail_electronics);


        electronic_item = (HashMap<String,?>)getArguments().getSerializable("electroniciteminfo");

        if(electronic_item!=null)
        {
            vTitle.setText((String)electronic_item.get("name"));
            vBrandName.setText((String)electronic_item.get("brandName"));
            vItemId.setText(electronic_item.get("itemId").toString());
            vModelNumber.setText((String)electronic_item.get("modelNumber"));
            vColor.setText((String)electronic_item.get("color"));
            vSalePrice.setText(String.valueOf(electronic_item.get("salePrice")));
            msrp = (Double)(electronic_item.get("msrp"));
            if(msrp == 0.00000)
            vMSRP.setText(String.valueOf(electronic_item.get("salePrice")));
            else {
                vMSRP.setText(String.valueOf(msrp));
            }
            youtubeString = (String) electronic_item.get("youtubeString");
            vMaxItemsInOrder.setText(electronic_item.get("maxItemsInOrder").toString());
            vAvailability.setText((String) electronic_item.get("stock"));
//            vDescription.setText((String) electronic_item.get("longDescription"));
            float fRating = ((Double) electronic_item.get("customerRating")).floatValue();
            vRatingBar.setRating(fRating);
            youtubeButton.setImageResource(R.drawable.youtubeimage);
            String url = (String) electronic_item.get("largeImage");
            MyDownloadImageAsyncTask task = new MyDownloadImageAsyncTask(vImage);
            task.execute(new String[]{url});
            String url1 = (String) electronic_item.get("thumbnailImage");
            MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage_tnail);
            task1.execute(new String[] {url1});
            String url2 = (String) electronic_item.get("mediumImage");
            MyDownloadImageAsyncTask task2 = new MyDownloadImageAsyncTask(vImage_medium);
            task2.execute(new String[] {url2});
            String url3 = (String) electronic_item.get("largeImage");
            MyDownloadImageAsyncTask task3 = new MyDownloadImageAsyncTask(vImage_large);
            task3.execute(new String[] {url3});
            String url4 = (String) electronic_item.get("thumbnailImage");
            MyDownloadImageAsyncTask task4 = new MyDownloadImageAsyncTask(vImage_tnail1);
            task4.execute(new String[] {url4});
            String url5 = (String) electronic_item.get("mediumImage");
            MyDownloadImageAsyncTask task5 = new MyDownloadImageAsyncTask(vImage_medium1);
            task5.execute(new String[]{url5});




        }



        Button description_activity_demo = (Button) rootView.findViewById(R.id.ItemDescriptionField_detail_electronics);

        View.OnClickListener activityDemo = new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Activity_DescriptionDialog.class);
                i.putExtra(DialogFragment_DescriptionDialog.Description_ARGS,(String) electronic_item.get("longDescription"));

//                startActivity(i);
//                getActivity().startActivityForResult(i,REQUEST_DATE);
                startActivityForResult(i, 0);
            }
        };
        description_activity_demo.setOnClickListener(activityDemo);

//        shareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareButton);
//                Intent intentShare = new Intent(Intent.ACTION_SEND);
//                intentShare.setType("text/plain");
////                String content = "Movie Title:  " + (String) movie.get("name") + "\n" + "Movie Description: " + (String) movie.get("description") + "\n" +
////                        "Movie Released in Year:    " + (String ) movie.get("year") +  "\nLength of Movie is:  " + (String) movie.get("length")
////                        + "\nDirector:  " + (String) movie.get("director") + "\nCasting:    " + (String) movie.get("stars")+ "\nMovie Rated as: "  + movie.get("rating").toString();
//                String content = "hello";
//                intentShare.putExtra(Intent.EXTRA_TEXT,content);
//                mShareActionProvider.setShareIntent(intentShare);
//            }
//        });


        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                String url1 = (String) electronic_item.get("thumbnailImage");
                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
                task1.execute(new String[]{url1});
            }
        };
        vImage_tnail.setOnClickListener(clickListener);

        View.OnClickListener clickListener1 = new View.OnClickListener() {
            public void onClick(View v) {
                String url1 = (String) electronic_item.get("mediumImage");
                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
                task1.execute(new String[] {url1});
            }
        };
        vImage_medium.setOnClickListener(clickListener1);

        View.OnClickListener clickListener2 = new View.OnClickListener() {
            public void onClick(View v) {
                String url1 = (String) electronic_item.get("largeImage");
                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
                task1.execute(new String[] {url1});
            }
        };
        vImage_large.setOnClickListener(clickListener2);

        View.OnClickListener clickListener3 = new View.OnClickListener() {
            public void onClick(View v) {
                String url1 = (String) electronic_item.get("thumbnailImage");
                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
                task1.execute(new String[] {url1});
            }
        };
        vImage_tnail1.setOnClickListener(clickListener3);

        View.OnClickListener clickListener4 = new View.OnClickListener() {
            public void onClick(View v) {
                String url1 = (String) electronic_item.get("mediumImage");
                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
                task1.execute(new String[] {url1});
            }
        };
        vImage_medium1.setOnClickListener(clickListener4);


        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_Youtube.class);
                String nameOfItem = (String) electronic_item.get("name");
                intent.putExtra("itemName", nameOfItem);
                intent.putExtra("youtubeString",youtubeString);
                startActivity(intent);
            }
        });

        return rootView;

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//
//        inflater.inflate(R.menu.menu_sub_activities, menu);
//        MenuItem shareItem = menu.findItem(R.id.cart);
//        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
//        Intent intentShare = new Intent(Intent.ACTION_SEND);
//        intentShare.setType("text/plain");
//        String content = "hello";
////                = "Movie Title:  " + (String) movie.get("name") + "\n" + "Movie Description: " + (String) movie.get("description") + "\n" +
////                "Movie Released in Year:    " + (String ) movie.get("year") +  "\nLength of Movie is:  " + (String) movie.get("length")
////                + "\nDirector:  " + (String) movie.get("director") + "\nCasting:    " + (String) movie.get("stars")+ "\nMovie Rated as: "  + movie.get("rating").toString();
//        intentShare.putExtra(Intent.EXTRA_TEXT,content);
//        mShareActionProvider.setShareIntent(intentShare);
//
//
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

    public static Fragment_Detail_NewProducts newInstance(HashMap<String,?> electronicitemInfo){
        Fragment_Detail_NewProducts fragment = new Fragment_Detail_NewProducts();
        Bundle args = new Bundle();
        args.putSerializable("electroniciteminfo", electronicitemInfo);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode!=Activity.RESULT_OK)return;
        if(requestCode == 0)
        {
            String description = (String) data.getSerializableExtra(DialogFragment_DescriptionDialog.Description_ARGS);

        }
    }

}
