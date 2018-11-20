package com.example.venkat.connectr1.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venkat.connectr1.activities.R;
import com.example.venkat.connectr1.utilities.ImageHelper;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import java.util.List;
import java.util.Map;

/**
 * Created by venkat on 7/10/2015.
 */
public class Adapter_NavigationDrawerList extends RecyclerView.Adapter<Adapter_NavigationDrawerList.ViewHolder> {

    private List<Map<String, ?>> nav_draw_items;
    private Context context;
    private OnItemClickListner navItemClickListner;
    int currentPosition;
    Bitmap prof_Img_Bitmap;
    String name;
    String email;

    public Adapter_NavigationDrawerList(List<Map<String, ?>> nav_items, Context context) {
        nav_draw_items = nav_items;
        this.context = context;
    }

    public Object getItem(int position) {
        return nav_draw_items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView item_img;
        public TextView item_text;
        public ImageView img_divider;
        public TextView view_text;
        public ImageView view_banner;
        View mView;
        int currviewType;
        ImageView displayPic;
        ParseUser currentUser;
        String prof_img_string;
        TextView namet;
        TextView emailt;


        public ViewHolder(View view, int viewType) {
            super(view);
            mView = view;
            currviewType = viewType;
            item_img = (ImageView) view.findViewById(R.id.taskimg);
            item_text = (TextView) view.findViewById(R.id.tasktext);
            img_divider = (ImageView) view.findViewById(R.id.nav_div);
            view_text = (TextView) view.findViewById(R.id.view_text);
            view_banner = (ImageView) view.findViewById(R.id.view_banner);
            displayPic = (ImageView) view.findViewById(R.id.user_profile_img);
            namet = (TextView) view.findViewById(R.id.user_name);
            emailt =(TextView) view.findViewById(R.id.user_email);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navItemClickListner != null) {
                        navItemClickListner.OnItemClick(v, getAdapterPosition());

                    }
                    currentPosition = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }

        public void bindData(Map<String, ?> nav_item, int position) {
            if (currentPosition == position) {
                mView.setBackgroundColor(Color.parseColor("#B6B6B6"));

//                if(currviewType == 1)
//                    item_text.setTextColor(Color.parseColor("#FFFFFF"));
//                if(currviewType == 3)
//                    view_text.setTextColor(Color.parseColor("#FFFFFF"));


            } else {
                mView.setBackgroundColor(0x00000000);
            }

            switch (currviewType) {
                case 0:
                    if(view_banner!=null) {
                        view_banner.setImageResource(((Integer) nav_item.get("itemimage")).intValue());
                        currentUser= ParseUser.getCurrentUser();
                        if(!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                            if (currentUser.get("profileImageString") != null) {
                                prof_img_string = (String) currentUser.get("profileImageString");
                                name = (String) currentUser.get("name");
                                email = (String ) currentUser.getUsername();
                                namet.setText(name);
                                emailt.setText(email);
                            }
                            if (prof_img_string != null) {
                                byte[] img_bytes;
                                img_bytes = Base64.decode(prof_img_string, Base64.DEFAULT);
                                prof_Img_Bitmap = BitmapFactory.decodeByteArray(img_bytes, 0, img_bytes.length);
                                prof_Img_Bitmap = ImageHelper.getRoundedCornerBitmap(prof_Img_Bitmap, 100);
                                //nav_Draw_user_Img_ref = (ImageView)getActivity().findViewById(R.id.user_profile_img);
                                displayPic.setImageBitmap(prof_Img_Bitmap);

                            }
                        }
                    }
                    break;
                case 1:
                    if (item_img != null) {
                        item_img.setImageResource(((Integer) nav_item.get("itemimage")).intValue());
                    }
                    if (item_text != null) {
                        item_text.setText((String) nav_item.get("itemname"));
                    }
                    break;
                case 2:
                    if (img_divider != null) {
                        img_divider.setImageResource(((Integer) nav_item.get("itemimage")).intValue());
                    }
                    break;
                case 3:
                    if (view_text != null) {
                        view_text.setText((String) nav_item.get("itemname"));
                    }
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public Adapter_NavigationDrawerList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view;
        switch (viewType) {
            case 0:
                _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navigation_draw0, parent, false);
                break;
            case 1:
                _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navigation_draw1, parent, false);
                break;
            case 2:
                _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navigation_draw2, parent, false);
                break;
            case 3:
                _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navigation_draw3, parent, false);
                break;
            default:
                _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navigation_draw1, parent, false);
                break;

        }
        ViewHolder nav_vh = new ViewHolder(_view, viewType);
        return nav_vh;
    }

    @Override
    public void onBindViewHolder(Adapter_NavigationDrawerList.ViewHolder holder, int position) {
        Map<String, ?> nav_item = nav_draw_items.get(position);
        holder.bindData(nav_item, position);
    }

    @Override
    public int getItemViewType(int position) {
        Map<String, ?> currItem = nav_draw_items.get(position);
        return (Integer) currItem.get("type");
    }

    @Override
    public int getItemCount() {
        return nav_draw_items.size();
    }

    public interface OnItemClickListner {
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListner(final OnItemClickListner navItemClickListner) {
        this.navItemClickListner = navItemClickListner;
    }
}
