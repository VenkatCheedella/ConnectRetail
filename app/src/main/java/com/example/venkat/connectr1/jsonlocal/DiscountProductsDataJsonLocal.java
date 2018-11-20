package com.example.venkat.connectr1.jsonlocal;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountProductsDataJsonLocal {
    List<Map<String,?>> OfferProductsList;

    public List<Map<String, ?>> getDiscountedProductsList() {
        return OfferProductsList;
    }
    public int getSize(){
        return OfferProductsList.size();
    }
    public HashMap getItem(int i){
        if (i >=0 && i < OfferProductsList.size()){
            return (HashMap) OfferProductsList.get(i);
        } else
            return null;
    }

    public DiscountProductsDataJsonLocal() {
        OfferProductsList = new ArrayList<Map<String,?>>();
    }

    // Why do you need context here?

    public void loadLocalMovieDataJson(Context context) {

        String name="";
        Double price= 0.00000;
        Double msrp = 0.00000;
        String longDescription ="";
        String sdescription = "";
        String brandname ="";
        String tnailimg = "";
        String mediumimg = "";
        String limg = "";
        Boolean marketPL = false;
        String stock = "";
        Integer itemId = 0;
        String modelNumber = "";
        String color = "";
        Integer maxItemsInOrder = 0;
        Double customerRating = 3.00000;
        OfferProductsList.clear(); // clear the list

        // movie.json contains an array of movies
        String offerProductsArray = loadFileFromAsset(context, "offerProducts.JSON");
        if (offerProductsArray == null){
            Log.d("MyDebugMsg", "Having trouble load movie.json");
            return;
        }

        try {
            // Parse the string to construct JSON array
            JSONArray OfferProductsJsonArray = new JSONArray(offerProductsArray);
            for (int i = 0; i < OfferProductsJsonArray.length(); i++) {
                JSONObject offerProductJsonObj = (JSONObject) OfferProductsJsonArray.get(i);
                if (offerProductJsonObj != null) {
                    if(offerProductJsonObj.has("name"))
                        name = (String) offerProductJsonObj.get("name");
                    if(offerProductJsonObj.has("salePrice") )
                        price = (Double) offerProductJsonObj.get("salePrice");
                    if(offerProductJsonObj.has("msrp") )
                        msrp = (Double) offerProductJsonObj.get("msrp");
                    else
                        msrp = 0.00000;
                    if(offerProductJsonObj.has("shortDescription")) {
                        sdescription = (String) offerProductJsonObj.get("shortDescription");
                        sdescription = sdescription.replace("&quot;", "''");
                        sdescription = sdescription.replace("&lt;", "");
                        sdescription = sdescription.replace("/br&gt;", "");
                        sdescription = sdescription.replace("br&gt;", "");
                        sdescription = sdescription.replace("&nbsp;", "");
                        sdescription = sdescription.replace("/p&gt;", "");
                        sdescription = sdescription.replace("p&gt;", "");
                        sdescription = sdescription.replace("/b&gt;", "");
                        sdescription = sdescription.replace("/sup&gt;", "");
                        sdescription = sdescription.replace("/li&gt;", "");
                        sdescription = sdescription.replace("/ul&gt;", "");
                        sdescription = sdescription.replace("b&gt;", "");
                        sdescription = sdescription.replace("sup&gt;", "");
                        sdescription = sdescription.replace("&reg;", "");
                        sdescription = sdescription.replace("li&gt", "");
                        sdescription = sdescription.replace("ul&gt", "");

                        sdescription = sdescription.replace("", "");
                    }
                    if(offerProductJsonObj.has("longDescription")) {
                        longDescription = (String) offerProductJsonObj.get("longDescription");
                        longDescription = longDescription.replace("<br />", "");
                        longDescription = longDescription.replace("strong&gt;", "");
                        longDescription = longDescription.replace("b&gt;", "");
                        longDescription = longDescription.replace("br&gt;", "");
                        longDescription = longDescription.replace("&lt;", "");
                        longDescription = longDescription.replace("ul&gt;", "");
                        longDescription = longDescription.replace("li&gt;", "");
                        longDescription = longDescription.replace("a&gt;", "");
                        longDescription = longDescription.replace("&quot;", "");
                        longDescription = longDescription.replace("p&gt;", "");
                        longDescription = longDescription.replace("ul&gt;", "");
                        longDescription = longDescription.replace("strong", "");
                        longDescription = longDescription.replace("&gt;", "");
                        longDescription = longDescription.replace("/", "");
                        longDescription = longDescription.replace("ul", "");
                        longDescription = longDescription.replace("class", "");
                        longDescription = longDescription.replace("=", "");
                        longDescription = longDescription.replace("noindent", "");
                        longDescription = longDescription.replace("none", "");
                        longDescription = longDescription.replace("/li", "");
                        longDescription = longDescription.replace("style", "");
                        longDescription = longDescription.replace("list-style", "");


                    }
                    if(offerProductJsonObj.has("brandName"))
                        brandname = (String) offerProductJsonObj.get("brandName");
                    if(offerProductJsonObj.has("thumbnailImage"))
                        tnailimg = (String) offerProductJsonObj.get("thumbnailImage");
                    if(offerProductJsonObj.has("mediumImage") )
                        mediumimg = (String) offerProductJsonObj.get("mediumImage");
                    if(offerProductJsonObj.has("largeImage") )
                        limg = (String) offerProductJsonObj.get("largeImage");
//                        if(offerProductJsonObj.has("standardShipRate") )
//                             standshiprate = (Double) offerProductJsonObj.get("standardShipRate");
                    if(offerProductJsonObj.has("marketplace") )
                        marketPL = (Boolean) offerProductJsonObj.get("marketplace");
                    if(offerProductJsonObj.has("stock"))
                        stock = (String) offerProductJsonObj.get("stock");
                    if(offerProductJsonObj.has("itemId"))
                        itemId = (Integer) offerProductJsonObj.get("itemId");
                    if(offerProductJsonObj.has("modelNumber"))
                        modelNumber = (String) offerProductJsonObj.get("modelNumber");
                    if(offerProductJsonObj.has("color"))
                        color = (String) offerProductJsonObj.get("color");

                    if(offerProductJsonObj.has("maxItemsInOrder"))
                        maxItemsInOrder = (Integer) offerProductJsonObj.get("maxItemsInOrder");

                    if(offerProductJsonObj.has("customerRating"))
                        customerRating = Double.parseDouble((String)offerProductJsonObj.get("customerRating"));

                    OfferProductsList.add(createanOfferProductItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                            limg, marketPL, stock, msrp, longDescription, itemId, modelNumber, color, maxItemsInOrder, customerRating));
                }
            }
        } catch (JSONException ex){
            Log.d("MyDebugMsg", "JSONException in loadLocalMovieDataJson()");
            ex.printStackTrace();
        }
    }

    public String loadFileFromAsset(Context context, String fileName) {
        String contents = null, line;
        try {
            InputStream stream = context.getAssets().open(fileName);
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder out = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
                reader.close();
                contents = out.toString();
            }
        } catch (IOException ex) {
            Log.d("MyDebugMsg", "IOException in loadFileFromAsset()");
            ex.printStackTrace();
        }
        return contents;
    }

    private static HashMap createanOfferProductItem(String name, Double price, String description, String brandname, String tnailimg,
                                                    String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap offerProduct = new HashMap();
        offerProduct.put("name", name);
        offerProduct.put("salePrice", price);
        offerProduct.put("shortDescription", description);
        offerProduct.put("brandName", brandname);
        offerProduct.put("thumbnailImage", tnailimg);
        offerProduct.put("mediumImage", mediumimg);
        offerProduct.put("largeImage", limg);
        offerProduct.put("marketPL", marketPL);
        offerProduct.put("stock", stock);
        offerProduct.put("msrp", msrp);
        offerProduct.put("longDescription", longDescription);
        offerProduct.put("itemId", itemId);
        offerProduct.put("modelNumber", modelNumber);
        offerProduct.put("color", color);
        offerProduct.put("maxItemsInOrder", maxItemsInOrder);
        offerProduct.put("customerRating", customerRating);
        return offerProduct;
    }

//    private static HashMap createMovie_brief(String name, String description,
//                                             double rating, String url, String id) {
//        HashMap movie = new HashMap();
//
//        movie.put("name", name);
//        movie.put("description", description);
//        movie.put("rating",rating);
//        movie.put("url",url);
//        movie.put("id",id);
//        return movie;
//    }

}
