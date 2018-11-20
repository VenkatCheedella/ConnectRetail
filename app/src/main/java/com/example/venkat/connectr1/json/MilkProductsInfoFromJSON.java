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
public class MilkProductsInfoFromJSON {
    public static String MILKPRODUCTSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=976759_976788_1001466&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
   // public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096_1161986&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> milkProductsList;


    public List<Map<String, ?>> getMilkProductsList() {
        return milkProductsList;
    }

    public int getSize() {
        return milkProductsList.size();
    }

    public MilkProductsInfoFromJSON() {
        milkProductsList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadMilkProductsinfoJSON(String json_url) {
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


        milkProductsList.clear();
//        String listofElectronicJSONItems = "";
        String milkProductsJSON = MyUtility.downloadJSON(json_url);
        if (milkProductsJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + MILKPRODUCTSJSON_API);
            return;
        }

        try {
            JSONObject milkProductsJson = new JSONObject(milkProductsJSON);

            if (milkProductsJson != null) {
                JSONArray milkProductsJsonArray = milkProductsJson.getJSONArray("items");
                if(milkProductsJsonArray != null){
                    for (int i = 0; i < milkProductsJsonArray.length(); i++) {
                        JSONObject milkProductsJSONObject = (JSONObject) milkProductsJsonArray.get(i);
                        if(milkProductsJSONObject.has("name"))
                             name = (String) milkProductsJSONObject.get("name");
                        if(milkProductsJSONObject.has("salePrice") )
                             price = (Double) milkProductsJSONObject.get("salePrice");
                        if(milkProductsJSONObject.has("shortDescription"))
                        {
                            sdescription = (String) milkProductsJSONObject.get("shortDescription");
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

                        if(milkProductsJSONObject.has("msrp") )
                            msrp = (Double) milkProductsJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(milkProductsJSONObject.has("longDescription")) {
                            longDescription = (String) milkProductsJSONObject.get("longDescription");
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

                        if(milkProductsJSONObject.has("brandName"))
                             brandname = (String) milkProductsJSONObject.get("brandName");
                        if(milkProductsJSONObject.has("thumbnailImage"))
                             tnailimg = (String) milkProductsJSONObject.get("thumbnailImage");
                        if(milkProductsJSONObject.has("mediumImage") )
                             mediumimg = (String) milkProductsJSONObject.get("mediumImage");
                        if(milkProductsJSONObject.has("largeImage") )
                             limg = (String) milkProductsJSONObject.get("largeImage");
//                        if(eitemJSONObject.has("standardShipRate") )
//                             standshiprate = (Double) eitemJSONObject.get("standardShipRate");
                        if(milkProductsJSONObject.has("marketplace") )
                             marketPL = (Boolean) milkProductsJSONObject.get("marketplace");
                        if(milkProductsJSONObject.has("stock"))
                             stock = (String) milkProductsJSONObject.get("stock");
                        if(milkProductsJSONObject.has("itemId"))
                            itemId = (Integer) milkProductsJSONObject.get("itemId");
                        if(milkProductsJSONObject.has("modelNumber"))
                            modelNumber = (String) milkProductsJSONObject.get("modelNumber");
                        if(milkProductsJSONObject.has("color"))
                            color = (String) milkProductsJSONObject.get("color");

                        if(milkProductsJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) milkProductsJSONObject.get("maxItemsInOrder");

                        if(milkProductsJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)milkProductsJSONObject.get("customerRating"));


                            milkProductsList.add(createaMilkProductItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createaMilkProductItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap milkProductitem = new HashMap();
        milkProductitem.put("name", name);
        milkProductitem.put("salePrice", price);
        milkProductitem.put("shortDescription", description);
        milkProductitem.put("brandName", brandname);
        milkProductitem.put("thumbnailImage", tnailimg);
        milkProductitem.put("mediumImage", mediumimg);
        milkProductitem.put("largeImage", limg);
        milkProductitem.put("marketPL", marketPL);
        milkProductitem.put("stock", stock);
        milkProductitem.put("msrp", msrp);
        milkProductitem.put("longDescription", longDescription);
        milkProductitem.put("itemId", itemId);
        milkProductitem.put("modelNumber", modelNumber);
        milkProductitem.put("color", color);
        milkProductitem.put("maxItemsInOrder", maxItemsInOrder);
        milkProductitem.put("customerRating", customerRating);

        return milkProductitem;
    }
}
