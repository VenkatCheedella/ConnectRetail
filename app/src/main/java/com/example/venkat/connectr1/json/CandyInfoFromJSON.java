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
public class CandyInfoFromJSON {
    public static String CANDYJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=976759_1096070_1229916&apiKey=seuyq22f8ejzzvq4fdxuh3zd";

    public List<Map<String, ?>> candiesList;


    public List<Map<String, ?>> getCandiesList() {
        return candiesList;
    }

    public int getSize() {
        return candiesList.size();
    }

    public CandyInfoFromJSON() {
        candiesList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadCandiesinfoJSON(String json_url) {
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

        candiesList.clear();
        String candiesJSON = MyUtility.downloadJSON(json_url);
        if (candiesJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + CANDYJSON_API);
            return;
        }

        try {
            JSONObject candiesJson = new JSONObject(candiesJSON);

            if (candiesJson != null) {
                JSONArray candiesJsonArray = candiesJson.getJSONArray("items");
                if(candiesJsonArray != null){
                    for (int i = 0; i < candiesJsonArray.length(); i++) {
                        JSONObject candyJSONObject = (JSONObject) candiesJsonArray.get(i);
                        if(candyJSONObject.has("name"))
                             name = (String) candyJSONObject.get("name");
                        if(candyJSONObject.has("salePrice") )
                             price = (Double) candyJSONObject.get("salePrice");
                        if(candyJSONObject.has("shortDescription"))
                        {
                            sdescription = (String) candyJSONObject.get("shortDescription");
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
                        if(candyJSONObject.has("brandName"))
                             brandname = (String) candyJSONObject.get("brandName");
                        if(candyJSONObject.has("thumbnailImage"))
                             tnailimg = (String) candyJSONObject.get("thumbnailImage");
                        if(candyJSONObject.has("mediumImage") )
                             mediumimg = (String) candyJSONObject.get("mediumImage");
                        if(candyJSONObject.has("largeImage") )
                             limg = (String) candyJSONObject.get("largeImage");
                        if(candyJSONObject.has("marketplace") )
                             marketPL = (Boolean) candyJSONObject.get("marketplace");
                        if(candyJSONObject.has("msrp") )
                            msrp = (Double) candyJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(candyJSONObject.has("longDescription")) {
                            longDescription = (String) candyJSONObject.get("longDescription");
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
                        if(candyJSONObject.has("itemId"))
                            itemId = (Integer) candyJSONObject.get("itemId");
                        if(candyJSONObject.has("modelNumber"))
                            modelNumber = (String) candyJSONObject.get("modelNumber");
                        if(candyJSONObject.has("color"))
                            color = (String) candyJSONObject.get("color");

                        if(candyJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) candyJSONObject.get("maxItemsInOrder");

                        if(candyJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)candyJSONObject.get("customerRating"));

                        if(candyJSONObject.has("stock"))
                             stock = (String) candyJSONObject.get("stock");
                            candiesList.add(createaCandyItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createaCandyItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap candyitem = new HashMap();
        candyitem.put("name", name);
        candyitem.put("salePrice", price);
        candyitem.put("shortDescription", description);
        candyitem.put("brandName", brandname);
        candyitem.put("thumbnailImage", tnailimg);
        candyitem.put("mediumImage", mediumimg);
        candyitem.put("largeImage", limg);
        candyitem.put("marketPL", marketPL);
        candyitem.put("stock", stock);
        candyitem.put("msrp", msrp);
        candyitem.put("longDescription", longDescription);
        candyitem.put("itemId", itemId);
        candyitem.put("modelNumber", modelNumber);
        candyitem.put("color", color);
        candyitem.put("maxItemsInOrder", maxItemsInOrder);
        candyitem.put("customerRating", customerRating);

        return candyitem;
    }
}
