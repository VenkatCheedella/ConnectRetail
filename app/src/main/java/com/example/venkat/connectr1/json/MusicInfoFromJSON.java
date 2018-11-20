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
public class MusicInfoFromJSON {
    public static String MUSICJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=3920_582321&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
   // public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096_1161986&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> musicList;


    public List<Map<String, ?>> getMusicList() {
        return musicList;
    }

    public int getSize() {
        return musicList.size();
    }

    public MusicInfoFromJSON() {
        musicList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadMusicinfoJSON(String json_url) {
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

        musicList.clear();
//        String listofElectronicJSONItems = "";
        String musicJSON = MyUtility.downloadJSON(json_url);
        if (musicJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + MUSICJSON_API);
            return;
        }

        try {
            JSONObject musicJson = new JSONObject(musicJSON);

            if (musicJson != null) {
                JSONArray musicJsonArray = musicJson.getJSONArray("items");
                if(musicJsonArray != null){
                    for (int i = 0; i < musicJsonArray.length(); i++) {
                        JSONObject musicJSONObject = (JSONObject) musicJsonArray.get(i);
                        if(musicJSONObject.has("name"))
                             name = (String) musicJSONObject.get("name");
                        if(musicJSONObject.has("salePrice") )
                             price = (Double) musicJSONObject.get("salePrice");
                        if(musicJSONObject.has("shortDescription"))
                        {
                            sdescription = (String) musicJSONObject.get("shortDescription");
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
                        if(musicJSONObject.has("msrp") )
                            msrp = (Double) musicJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(musicJSONObject.has("longDescription")) {
                            longDescription = (String) musicJSONObject.get("longDescription");
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
                        if(musicJSONObject.has("itemId"))
                            itemId = (Integer) musicJSONObject.get("itemId");
                        if(musicJSONObject.has("modelNumber"))
                            modelNumber = (String) musicJSONObject.get("modelNumber");
                        if(musicJSONObject.has("color"))
                            color = (String) musicJSONObject.get("color");

                        if(musicJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) musicJSONObject.get("maxItemsInOrder");

                        if(musicJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)musicJSONObject.get("customerRating"));
                        if(musicJSONObject.has("brandName"))
                             brandname = (String) musicJSONObject.get("brandName");
                        if(musicJSONObject.has("thumbnailImage"))
                             tnailimg = (String) musicJSONObject.get("thumbnailImage");
                        if(musicJSONObject.has("mediumImage") )
                             mediumimg = (String) musicJSONObject.get("mediumImage");
                        if(musicJSONObject.has("largeImage") )
                             limg = (String) musicJSONObject.get("largeImage");
//                        if(eitemJSONObject.has("standardShipRate") )
//                             standshiprate = (Double) eitemJSONObject.get("standardShipRate");
                        if(musicJSONObject.has("marketplace") )
                             marketPL = (Boolean) musicJSONObject.get("marketplace");
                        if(musicJSONObject.has("stock"))
                             stock = (String) musicJSONObject.get("stock");
                            musicList.add(createaMusicItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createaMusicItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap musicitem = new HashMap();
        musicitem.put("name", name);
        musicitem.put("salePrice", price);
        musicitem.put("shortDescription", description);
        musicitem.put("brandName", brandname);
        musicitem.put("thumbnailImage", tnailimg);
        musicitem.put("mediumImage", mediumimg);
        musicitem.put("largeImage", limg);
        musicitem.put("marketPL", marketPL);
        musicitem.put("stock", stock);
        musicitem.put("msrp", msrp);
        musicitem.put("longDescription", longDescription);
        musicitem.put("itemId", itemId);
        musicitem.put("modelNumber", modelNumber);
        musicitem.put("color", color);
        musicitem.put("maxItemsInOrder", maxItemsInOrder);
        musicitem.put("customerRating", customerRating);

        return musicitem;
    }
}
