package com.example.venkat.connectr1.fragments;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.utilities.MyUtility;

import java.lang.ref.WeakReference;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_TopSellingProducts.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_TopSellingProducts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_TopSellingProducts extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView vImage;
//    private ImageView vImage_tnail;
//    private ImageView vImage_medium;
//    private ImageView vImage_large;
//    private ImageView vImage_tnail1;
//    private ImageView vImage_medium1;
    private TextView vTitle;
//    private TextView vBrandName;
//    private TextView vItemId;
//    private TextView vModelNumber;
//    private TextView vColor;
//    private TextView vSalePrice;
//    private TextView vMSRP;
//    private TextView vMaxItemsInOrder;
//    private TextView vAvailability;
//    private TextView vDescription;
//    private RatingBar vRatingBar;
//    private Double msrp;

    HashMap<String, ?> electronic_item;
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
    public static Fragment_TopSellingProducts newInstance(String param1, String param2) {
        Fragment_TopSellingProducts fragment = new Fragment_TopSellingProducts();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment_TopSellingProducts() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_top_selling_products, container, false);

        vImage = (ImageView) rootView.findViewById(R.id.image_detail_electronics);
//        vImage_tnail =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image1);
//        vImage_tnail1 =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image4);
//        vImage_medium =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image2);
//        vImage_medium1 =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image5);
//        vImage_large =(ImageView) rootView.findViewById(R.id.image_detail_electronics_image3);
        vTitle = (TextView) rootView.findViewById(R.id.Title_detail_electronics);
//        vBrandName = (TextView) rootView.findViewById(R.id.BrandName_detail_electronics);
//        vItemId = (TextView) rootView.findViewById(R.id.ItemId_detail_electronics);
//
//        vModelNumber = (TextView) rootView.findViewById(R.id.ModelNumber_detail_electronics);
//        vColor = (TextView) rootView.findViewById(R.id.ItemColor_detail_electronics);
//        vSalePrice = (TextView) rootView.findViewById(R.id.SalePrice_detail_electronics);
//        vMSRP = (TextView) rootView.findViewById(R.id.MSRP_detail_electronics);
//
//        vMaxItemsInOrder = (TextView) rootView.findViewById(R.id.MaximumItemsPerOrder_detail_electronics);
//
//        vAvailability = (TextView) rootView.findViewById(R.id.ItemAvailability_detail_electronics);
//
//        vDescription = (TextView) rootView.findViewById(R.id.ItemDescription_detail_electronics);
//
//        vRatingBar = (RatingBar) rootView.findViewById(R.id.CustomerRating_detail_electronics);


        electronic_item = (HashMap<String,?>)getArguments().getSerializable("electroniciteminfo");

        if(electronic_item!=null)
        {
            vTitle.setText((String)electronic_item.get("name"));
//            vBrandName.setText((String)electronic_item.get("brandName"));
//            vItemId.setText(electronic_item.get("itemId").toString());
//            vModelNumber.setText((String)electronic_item.get("modelNumber"));
//            vColor.setText((String)electronic_item.get("color"));
//            vSalePrice.setText(String.valueOf(electronic_item.get("salePrice")));
//            msrp = (Double)(electronic_item.get("msrp"));
//            if(msrp == 0.00000)
//            vMSRP.setText(String.valueOf(electronic_item.get("salePrice")));
//            else {
//                vMSRP.setText(String.valueOf(msrp));
//            }
//
//            vMaxItemsInOrder.setText(electronic_item.get("maxItemsInOrder").toString());
//            vAvailability.setText((String) electronic_item.get("stock"));
//            vDescription.setText((String) electronic_item.get("longDescription"));
//            float fRating = ((Double) electronic_item.get("customerRating")).floatValue();
//            vRatingBar.setRating(fRating);
            String url = (String) electronic_item.get("largeImage");
            MyDownloadImageAsyncTask task = new MyDownloadImageAsyncTask(vImage);
            task.execute(new String[]{url});
//            String url1 = (String) electronic_item.get("thumbnailImage");
//            MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage_tnail);
//            task1.execute(new String[] {url1});
//            String url2 = (String) electronic_item.get("mediumImage");
//            MyDownloadImageAsyncTask task2 = new MyDownloadImageAsyncTask(vImage_medium);
//            task2.execute(new String[] {url2});
//            String url3 = (String) electronic_item.get("largeImage");
//            MyDownloadImageAsyncTask task3 = new MyDownloadImageAsyncTask(vImage_large);
//            task3.execute(new String[] {url3});
//            String url4 = (String) electronic_item.get("thumbnailImage");
//            MyDownloadImageAsyncTask task4 = new MyDownloadImageAsyncTask(vImage_tnail1);
//            task4.execute(new String[] {url4});
//            String url5 = (String) electronic_item.get("mediumImage");
//            MyDownloadImageAsyncTask task5 = new MyDownloadImageAsyncTask(vImage_medium1);
//            task5.execute(new String[]{url5});




        }

//        TextView description_activity_demo = (TextView) rootView.findViewById(R.id.ItemDescriptionField_detail_electronics);
//
//        View.OnClickListener activityDemo = new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(),Activity_DescriptionDialog.class);
//                i.putExtra(DialogFragment_DescriptionDialog.Description_ARGS,(String) electronic_item.get("longDescription"));
//
////                startActivity(i);
////                getActivity().startActivityForResult(i,REQUEST_DATE);
//                startActivityForResult(i, 0);
//            }
//        };
//        description_activity_demo.setOnClickListener(activityDemo);


//        View.OnClickListener clickListener = new View.OnClickListener() {
//            public void onClick(View v) {
//                String url1 = (String) electronic_item.get("thumbnailImage");
//                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
//                task1.execute(new String[] {url1});
//            }
//        };
//        vImage_tnail.setOnClickListener(clickListener);
//
//        View.OnClickListener clickListener1 = new View.OnClickListener() {
//            public void onClick(View v) {
//                String url1 = (String) electronic_item.get("mediumImage");
//                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
//                task1.execute(new String[] {url1});
//            }
//        };
//        vImage_medium.setOnClickListener(clickListener1);
//
//        View.OnClickListener clickListener2 = new View.OnClickListener() {
//            public void onClick(View v) {
//                String url1 = (String) electronic_item.get("largeImage");
//                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
//                task1.execute(new String[] {url1});
//            }
//        };
//        vImage_large.setOnClickListener(clickListener2);
//
//        View.OnClickListener clickListener3 = new View.OnClickListener() {
//            public void onClick(View v) {
//                String url1 = (String) electronic_item.get("thumbnailImage");
//                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
//                task1.execute(new String[] {url1});
//            }
//        };
//        vImage_tnail1.setOnClickListener(clickListener3);
//
//        View.OnClickListener clickListener4 = new View.OnClickListener() {
//            public void onClick(View v) {
//                String url1 = (String) electronic_item.get("mediumImage");
//                MyDownloadImageAsyncTask task1 = new MyDownloadImageAsyncTask(vImage);
//                task1.execute(new String[] {url1});
//            }
//        };
//        vImage_medium1.setOnClickListener(clickListener4);

        return rootView;

    }

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

    public static Fragment_TopSellingProducts newInstance(HashMap<String,?> electronicitemInfo){
        Fragment_TopSellingProducts fragment = new Fragment_TopSellingProducts();
        Bundle args = new Bundle();
        args.putSerializable("electroniciteminfo", electronicitemInfo);
        fragment.setArguments(args);
        return fragment;

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(resultCode!=Activity.RESULT_OK)return;
//        if(requestCode == 0)
//        {
//            String description = (String) data.getSerializableExtra(DialogFragment_DescriptionDialog.Description_ARGS);
//
//        }
//    }

}
