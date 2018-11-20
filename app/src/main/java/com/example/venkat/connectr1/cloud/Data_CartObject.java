package com.example.venkat.connectr1.cloud;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by venkat on 8/6/2015.
 */

@ParseClassName("Data_CartObject")
public class Data_CartObject extends ParseObject {

    public String getImageURL(){
        return getString("imageUrl");
    }

    public void setImageURL(String imgUrl){
        put("imageUrl", imgUrl);
    }

    public String getProductName(){
        return getString("productname");
    }

    public void setProductName(String prdName){
        put("productname", prdName);
    }

    public Double getUnitCost(){
        return getDouble("unitCost");
    }

    public void setUnitCost(Double unitCost){
        put("unitCost", unitCost);
    }

    public ParseUser getOwner(){
        return getParseUser("owner");
    }

    public void setOwner(ParseUser owner){
        put("owner", owner);
    }

    public boolean isDraft() {
        return getBoolean("isDraft");
    }

    public void setDraft(boolean isDraft) {
        put("isDraft", isDraft);
    }


    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Data_CartObject> getQuery(){
        return ParseQuery.getQuery(Data_CartObject.class);
    }
}
