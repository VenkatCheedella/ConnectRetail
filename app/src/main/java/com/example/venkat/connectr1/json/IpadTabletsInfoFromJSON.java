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
public class IpadTabletsInfoFromJSON {
    public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=3944_1078524_1077944&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
   // public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096_1161986&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> electronicsList;


    public List<Map<String, ?>> getElectronicsList() {
        return electronicsList;
    }

    public int getSize() {
        return electronicsList.size();
    }

    public IpadTabletsInfoFromJSON() {
        electronicsList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadElectronicsinfoJSON(String json_url) {
        String name="";
        Double price= 0.00000;
       // String description ="";
        String sdescription = "";
        String brandname ="";
        String tnailimg = "";
        String mediumimg = "";
        String limg = "";
       // Double standshiprate = 0.00000;
        Boolean marketPL = false;
        String stock = "";

        electronicsList.clear();
        String listofElectronicJSONItems = "";
        String electronicsJSON = MyUtility.downloadJSON(json_url);
        if (electronicsJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + IPADTABLETSJSON_API);
            return;
        }

        try {
            JSONObject electronicsJson = new JSONObject(electronicsJSON);

            if (electronicsJson != null) {
                JSONArray electronicsJsonArray = electronicsJson.getJSONArray("items");
                if(electronicsJsonArray != null){
                    for (int i = 0; i < electronicsJsonArray.length(); i++) {
                        JSONObject eitemJSONObject = (JSONObject) electronicsJsonArray.get(i);
                        if(eitemJSONObject.has("name"))
                             name = (String) eitemJSONObject.get("name");
                        if(eitemJSONObject.has("salePrice") )
                             price = (Double) eitemJSONObject.get("salePrice");
                        if(eitemJSONObject.has("shortDescription"))
                            sdescription = (String)eitemJSONObject.get("shortDescription");

//                        if(eitemJSONObject.has("longDescription"))
//                             description = (String) eitemJSONObject.get("longDescription");
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
                            electronicsList.add(createanElectronicItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock));
                        }
}
        }
        } catch (JSONException ex) {
        Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
        ex.printStackTrace();
        }
        }

    private static HashMap createanElectronicItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock) {
        HashMap eitem = new HashMap();
        eitem.put("name", name);
        eitem.put("price", price);
        eitem.put("description", description);
        eitem.put("brandname", brandname);
        eitem.put("tnailimg", tnailimg);
        eitem.put("mediumimg", mediumimg);
        eitem.put("limg", limg);
        eitem.put("marketPL", marketPL);
        eitem.put("stock", stock);

        return eitem;
    }
}
