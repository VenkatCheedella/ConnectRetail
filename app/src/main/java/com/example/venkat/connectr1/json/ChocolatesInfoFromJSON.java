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
public class ChocolatesInfoFromJSON {
    public static String CHOCOLATESJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=976759_1096070_1224976&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
   // public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096_1161986&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> chocolatesList;


    public List<Map<String, ?>> getChocolatesList() {
        return chocolatesList;
    }

    public int getSize() {
        return chocolatesList.size();
    }

    public ChocolatesInfoFromJSON() {
        chocolatesList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadChocolatesinfoJSON(String json_url) {
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

        chocolatesList.clear();
//        String listofElectronicJSONItems = "";
        String chocolatesJSON = MyUtility.downloadJSON(json_url);
        if (chocolatesJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + CHOCOLATESJSON_API);
            return;
        }

        try {
            JSONObject chocolatesJson = new JSONObject(chocolatesJSON);

            if (chocolatesJson != null) {
                JSONArray chocolatesJsonArray = chocolatesJson.getJSONArray("items");
                if(chocolatesJsonArray != null){
                    for (int i = 0; i < chocolatesJsonArray.length(); i++) {
                        JSONObject chocolateJSONObject = (JSONObject) chocolatesJsonArray.get(i);
                        if(chocolateJSONObject.has("name"))
                             name = (String) chocolateJSONObject.get("name");
                        if(chocolateJSONObject.has("salePrice") )
                             price = (Double) chocolateJSONObject.get("salePrice");
                        if(chocolateJSONObject.has("shortDescription"))
                        {
                            sdescription = (String) chocolateJSONObject.get("shortDescription");
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
                        if(chocolateJSONObject.has("msrp") )
                            msrp = (Double) chocolateJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(chocolateJSONObject.has("longDescription")) {
                            longDescription = (String) chocolateJSONObject.get("longDescription");
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
                        if(chocolateJSONObject.has("brandName"))
                             brandname = (String) chocolateJSONObject.get("brandName");
                        if(chocolateJSONObject.has("thumbnailImage"))
                             tnailimg = (String) chocolateJSONObject.get("thumbnailImage");
                        if(chocolateJSONObject.has("mediumImage") )
                             mediumimg = (String) chocolateJSONObject.get("mediumImage");
                        if(chocolateJSONObject.has("largeImage") )
                             limg = (String) chocolateJSONObject.get("largeImage");
//                        if(eitemJSONObject.has("standardShipRate") )
//                             standshiprate = (Double) eitemJSONObject.get("standardShipRate");
                        if(chocolateJSONObject.has("marketplace") )
                             marketPL = (Boolean) chocolateJSONObject.get("marketplace");
                        if(chocolateJSONObject.has("stock"))
                             stock = (String) chocolateJSONObject.get("stock");

                        if(chocolateJSONObject.has("itemId"))
                            itemId = (Integer) chocolateJSONObject.get("itemId");
                        if(chocolateJSONObject.has("modelNumber"))
                            modelNumber = (String) chocolateJSONObject.get("modelNumber");
                        if(chocolateJSONObject.has("color"))
                            color = (String) chocolateJSONObject.get("color");

                        if(chocolateJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) chocolateJSONObject.get("maxItemsInOrder");

                        if(chocolateJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)chocolateJSONObject.get("customerRating"));

                        chocolatesList.add(createaChocolateItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createaChocolateItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap chocolateitem = new HashMap();
        chocolateitem.put("name", name);
        chocolateitem.put("salePrice", price);
        chocolateitem.put("shortDescription", description);
        chocolateitem.put("brandName", brandname);
        chocolateitem.put("thumbnailImage", tnailimg);
        chocolateitem.put("mediumImage", mediumimg);
        chocolateitem.put("largeImage", limg);
        chocolateitem.put("marketPL", marketPL);
        chocolateitem.put("stock", stock);
        chocolateitem.put("msrp", msrp);
        chocolateitem.put("longDescription", longDescription);
        chocolateitem.put("itemId", itemId);
        chocolateitem.put("modelNumber", modelNumber);
        chocolateitem.put("color", color);
        chocolateitem.put("maxItemsInOrder", maxItemsInOrder);
        chocolateitem.put("customerRating", customerRating);

        return chocolateitem;
    }
}
