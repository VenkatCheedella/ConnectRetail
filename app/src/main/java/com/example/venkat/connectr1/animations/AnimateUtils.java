package com.example.venkat.connectr1.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.animation.AnticipateInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by rambabu on 8/5/2015.
 */
public class AnimateUtils {

    public AnimateUtils() {
    }

    public static void animateY(ViewHolder holder,Boolean goingDown)
    {

        ObjectAnimator translatorY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goingDown==true?200:-200, 0);
        translatorY.setDuration(1500);
        translatorY.start();
    }

    public static void animateXY(ViewHolder holder,Boolean goingDown)
    {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator translatorY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goingDown==true?200:-200, 0);
        ObjectAnimator translatorX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -50,50,-25,0,25,0);
        translatorY.setDuration(1500);
        translatorX.setDuration(1500);
        animatorSet.playTogether(translatorX, translatorY);
        animatorSet.start();
    }

    public static void animateXYInterpolator(ViewHolder holder,Boolean goingDown)
    {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView,"scaleX",0.8F,0.9F,1.0F);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView,"scaleY",0.8F,0.9F,1.0F);
        ObjectAnimator translatorY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goingDown==true?200:-200, 0);
        ObjectAnimator translatorX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -100,-75, -50,-25,0,25,50,75,100,0,25,50,75,100,75,50,25,0);

        animatorSet.playTogether(translatorX, translatorY, scaleX, scaleY);
        animatorSet.setInterpolator(new AnticipateInterpolator());
        animatorSet.setDuration(1500);
        animatorSet.start();
    }

    public static void experimentAnimator(ViewHolder holder,Boolean goingDown)
    {

            YoYo.with(Techniques.RubberBand)
            .duration(1500)
            .playOn(holder.itemView);

    }

//    Selected
/*
    YoYo.with(Techniques.Bounce)
            .duration(700)
    .playOn(holder.itemView);

    --------------
  public static void experimentAnimator(ViewHolder holder,Boolean goingDown)
    {
        if(goingDown==true)
        YoYo.with(Techniques.BounceInDown)
                .duration(700)
                .playOn(holder.itemView);
        else
            YoYo.with(Techniques.BounceInUp)
            .duration(700)
            .playOn(holder.itemView);

    }

    -----------------4  public static void experimentAnimator(ViewHolder holder,Boolean goingDown)
    {
        if(goingDown==true)
        YoYo.with(Techniques.BounceInUp)
                .duration(700)
                .playOn(holder.itemView);
        else
            YoYo.with(Techniques.BounceInDown)
            .duration(700)
            .playOn(holder.itemView);

    }


----good for recycler view----------
  public static void experimentAnimator(ViewHolder holder,Boolean goingDown)
    {
        if(goingDown==true)
        YoYo.with(Techniques.BounceInUp)
                .duration(700)
                .playOn(holder.itemView);
        else
            YoYo.with(Techniques.BounceInUp)
            .duration(700)
            .playOn(holder.itemView);

    }

    */

}
