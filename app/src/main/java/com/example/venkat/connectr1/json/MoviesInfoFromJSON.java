package com.example.venkat.connectr1.json;

import android.util.Log;

import com.example.venkat.connectr1.utilities.MyUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by venkat on 7/26/2015.
 */
public class MoviesInfoFromJSON {
    public static String MOVIESJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
   // public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096_1161986&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> moviesList;


    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    public int getSize() {
        return moviesList.size();
    }

    public MoviesInfoFromJSON() {
        moviesList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadMoviesinfoJSON(String json_url) {
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

        moviesList.clear();
//        String listofElectronicJSONItems = "";
        String moviesJSON = MyUtility.downloadJSON(json_url);
        if (moviesJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + MOVIESJSON_API);
            return;
        }

        try {
            JSONObject moviesJson = new JSONObject(moviesJSON);

            if (moviesJson != null) {
                JSONArray moviesJsonArray = moviesJson.getJSONArray("items");
                if(moviesJsonArray != null){
                    for (int i = 0; i < moviesJsonArray.length(); i++) {
                        JSONObject movieJSONObject = (JSONObject) moviesJsonArray.get(i);
                        if(movieJSONObject.has("name"))
                             name = (String) movieJSONObject.get("name");
                        if(movieJSONObject.has("salePrice") )
                             price = (Double) movieJSONObject.get("salePrice");
                        if(movieJSONObject.has("shortDescription"))
                        {
                            sdescription = (String) movieJSONObject.get("shortDescription");
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

                        if(movieJSONObject.has("msrp") )
                            msrp = (Double) movieJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(movieJSONObject.has("longDescription")) {
                            longDescription = (String) movieJSONObject.get("longDescription");
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
                        if(movieJSONObject.has("itemId"))
                            itemId = (Integer) movieJSONObject.get("itemId");
                        if(movieJSONObject.has("modelNumber"))
                            modelNumber = (String) movieJSONObject.get("modelNumber");
                        if(movieJSONObject.has("color"))
                            color = (String) movieJSONObject.get("color");

                        if(movieJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) movieJSONObject.get("maxItemsInOrder");

                        if(movieJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)movieJSONObject.get("customerRating"));
                        if(movieJSONObject.has("brandName"))
                             brandname = (String) movieJSONObject.get("brandName");
                        if(movieJSONObject.has("thumbnailImage"))
                             tnailimg = (String) movieJSONObject.get("thumbnailImage");
                        if(movieJSONObject.has("mediumImage") )
                             mediumimg = (String) movieJSONObject.get("mediumImage");
                        if(movieJSONObject.has("largeImage") )
                             limg = (String) movieJSONObject.get("largeImage");
//                        if(eitemJSONObject.has("standardShipRate") )
//                             standshiprate = (Double) eitemJSONObject.get("standardShipRate");
                        if(movieJSONObject.has("marketplace") )
                             marketPL = (Boolean) movieJSONObject.get("marketplace");
                        if(movieJSONObject.has("stock"))
                             stock = (String) movieJSONObject.get("stock");
                            moviesList.add(createaMovieItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createaMovieItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap movieitem = new HashMap();
        movieitem.put("name", name);
        movieitem.put("salePrice", price);
        movieitem.put("shortDescription", description);
        movieitem.put("brandName", brandname);
        movieitem.put("thumbnailImage", tnailimg);
        movieitem.put("mediumImage", mediumimg);
        movieitem.put("largeImage", limg);
        movieitem.put("marketPL", marketPL);
        movieitem.put("stock", stock);
        movieitem.put("msrp", msrp);
        movieitem.put("longDescription", longDescription);
        movieitem.put("itemId", itemId);
        movieitem.put("modelNumber", modelNumber);
        movieitem.put("color", color);
        movieitem.put("maxItemsInOrder", maxItemsInOrder);
        movieitem.put("customerRating", customerRating);

        return movieitem;
    }
}
