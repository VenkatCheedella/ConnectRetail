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
public class ElectronicsInfoFromJSON {
    public static String ELECTRONICSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=3944_3951_132960&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> electronicsList;


    public List<Map<String, ?>> getElectronicsList() {
        return electronicsList;
    }

    public int getSize() {
        return electronicsList.size();
    }

    public ElectronicsInfoFromJSON() {
        electronicsList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadElectronicsinfoJSON(String json_url) {
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

        electronicsList.clear();
        String listofElectronicJSONItems = "";
        String electronicsJSON = MyUtility.downloadJSON(json_url);
        if (electronicsJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + ELECTRONICSJSON_API);
            return;
        }

        try {
            JSONObject electronicsJson = new JSONObject(electronicsJSON);

            if (electronicsJson != null) {
                JSONArray electronicsJsonArray = electronicsJson.getJSONArray("items");
                if(electronicsJsonArray != null){
                    for (int i = 0; i < 25; i++) {
                        JSONObject eitemJSONObject = (JSONObject) electronicsJsonArray.get(i);

                        if(eitemJSONObject.has("name"))
                             name = (String) eitemJSONObject.get("name");
                        if(eitemJSONObject.has("salePrice") )
                             price = (Double) eitemJSONObject.get("salePrice");
                        if(eitemJSONObject.has("msrp") )
                            msrp = (Double) eitemJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(eitemJSONObject.has("shortDescription")) {
                            sdescription = (String) eitemJSONObject.get("shortDescription");
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
                        if(eitemJSONObject.has("longDescription")) {
                            longDescription = (String) eitemJSONObject.get("longDescription");
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
                        if(eitemJSONObject.has("brandName"))
                             brandname = (String) eitemJSONObject.get("brandName");
                        if(eitemJSONObject.has("thumbnailImage"))
                             tnailimg = (String) eitemJSONObject.get("thumbnailImage");
                        if(eitemJSONObject.has("mediumImage") )
                             mediumimg = (String) eitemJSONObject.get("mediumImage");
                        if(eitemJSONObject.has("largeImage") )
                             limg = (String) eitemJSONObject.get("largeImage");
//                        if(eitemJSONObject.has("standardShipRate") )
//                             standshiprate = (Double) eitemJSONObject.get("standardShipRate");
                        if(eitemJSONObject.has("marketplace") )
                             marketPL = (Boolean) eitemJSONObject.get("marketplace");
                        if(eitemJSONObject.has("stock"))
                             stock = (String) eitemJSONObject.get("stock");
                        if(eitemJSONObject.has("itemId"))
                            itemId = (Integer) eitemJSONObject.get("itemId");
                        if(eitemJSONObject.has("modelNumber"))
                            modelNumber = (String) eitemJSONObject.get("modelNumber");
                        if(eitemJSONObject.has("color"))
                            color = (String) eitemJSONObject.get("color");

                        if(eitemJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) eitemJSONObject.get("maxItemsInOrder");

                        if(eitemJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)eitemJSONObject.get("customerRating"));





                            electronicsList.add(createanElectronicItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createanElectronicItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
                                                  , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap eitem = new HashMap();
        eitem.put("name", name);
        eitem.put("salePrice", price);
        eitem.put("shortDescription", description);
        eitem.put("brandName", brandname);
        eitem.put("thumbnailImage", tnailimg);
        eitem.put("mediumImage", mediumimg);
        eitem.put("largeImage", limg);
        eitem.put("marketPL", marketPL);
        eitem.put("stock", stock);
        eitem.put("msrp", msrp);
        eitem.put("longDescription", longDescription);
        eitem.put("itemId", itemId);
        eitem.put("modelNumber", modelNumber);
        eitem.put("color", color);
        eitem.put("maxItemsInOrder", maxItemsInOrder);
        eitem.put("customerRating", customerRating);


        return eitem;
    }
}
