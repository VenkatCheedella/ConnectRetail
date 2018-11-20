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
public class BooksInfoFromJSON {
    public static String BOOKSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=3920&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
   // public static String IPADTABLETSJSON_API = "http://api.walmartlabs.com/v1/paginated/items?format=json&category=4096_1161986&apiKey=seuyq22f8ejzzvq4fdxuh3zd";
    //public static String ELECTRONICSJSON_API = "http://www.hdwallpapersimages.com/wp-content/uploads/2014/01/Winter-Tiger-Wild-Cat-Images.jpg";
    public List<Map<String, ?>> booksList;


    public List<Map<String, ?>> getBooksList() {
        return booksList;
    }

    public int getSize() {
        return booksList.size();
    }

    public BooksInfoFromJSON() {
        booksList = new ArrayList<Map<String, ?>>();
    }

    public void dowloadBooksinfoJSON(String json_url) {
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

        booksList.clear();
//        String listofElectronicJSONItems = "";
        String booksJSON = MyUtility.downloadJSON(json_url);
        if (booksJSON == null) {
            Log.d("MyDebugMsg", "Having trouble loading URL: " + BOOKSJSON_API);
            return;
        }

        try {
            JSONObject booksJson = new JSONObject(booksJSON);

            if (booksJson != null) {
                JSONArray booksJsonArray = booksJson.getJSONArray("items");
                if(booksJsonArray != null){
                    for (int i = 0; i < booksJsonArray.length(); i++) {
                        JSONObject bookJSONObject = (JSONObject) booksJsonArray.get(i);
                        if(bookJSONObject.has("name"))
                             name = (String) bookJSONObject.get("name");
                        if(bookJSONObject.has("salePrice") )
                             price = (Double) bookJSONObject.get("salePrice");
                        if(bookJSONObject.has("shortDescription"))
                        {
                            sdescription = (String) bookJSONObject.get("shortDescription");
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
                        if(bookJSONObject.has("msrp") )
                            msrp = (Double) bookJSONObject.get("msrp");
                        else
                            msrp = 0.00000;
                        if(bookJSONObject.has("longDescription")) {
                            longDescription = (String) bookJSONObject.get("longDescription");
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
                        if(bookJSONObject.has("itemId"))
                            itemId = (Integer) bookJSONObject.get("itemId");
                        if(bookJSONObject.has("modelNumber"))
                            modelNumber = (String) bookJSONObject.get("modelNumber");
                        if(bookJSONObject.has("color"))
                            color = (String) bookJSONObject.get("color");

                        if(bookJSONObject.has("maxItemsInOrder"))
                            maxItemsInOrder = (Integer) bookJSONObject.get("maxItemsInOrder");

                        if(bookJSONObject.has("customerRating"))
                            customerRating = Double.parseDouble((String)bookJSONObject.get("customerRating"));
                        if(bookJSONObject.has("brandName"))
                             brandname = (String) bookJSONObject.get("brandName");
                        if(bookJSONObject.has("thumbnailImage"))
                             tnailimg = (String) bookJSONObject.get("thumbnailImage");
                        if(bookJSONObject.has("mediumImage") )
                             mediumimg = (String) bookJSONObject.get("mediumImage");
                        if(bookJSONObject.has("largeImage") )
                             limg = (String) bookJSONObject.get("largeImage");
//                        if(eitemJSONObject.has("standardShipRate") )
//                             standshiprate = (Double) eitemJSONObject.get("standardShipRate");
                        if(bookJSONObject.has("marketplace") )
                             marketPL = (Boolean) bookJSONObject.get("marketplace");
                        if(bookJSONObject.has("stock"))
                             stock = (String) bookJSONObject.get("stock");
                            booksList.add(createaBookItem(name, price, sdescription, brandname, tnailimg, mediumimg,
                                    limg, marketPL, stock, msrp,longDescription,itemId,modelNumber,color,maxItemsInOrder,customerRating));
                        }
                }
            }
        } catch (JSONException ex) {
            Log.d("MyDebugMsg", "JSONException in download of eJSONArray");
            ex.printStackTrace();
        }
    }

    private static HashMap createaBookItem(String name, Double price, String description, String brandname, String tnailimg,
                                                  String mediumimg, String limg, Boolean marketPL, String stock, Double msrp, String longDescription, Integer itemId
            , String modelNumber, String color, Integer maxItemsInOrder, Double customerRating) {
        HashMap bookitem = new HashMap();
        bookitem.put("name", name);
        bookitem.put("salePrice", price);
        bookitem.put("shortDescription", description);
        bookitem.put("brandName", brandname);
        bookitem.put("thumbnailImage", tnailimg);
        bookitem.put("mediumImage", mediumimg);
        bookitem.put("largeImage", limg);
        bookitem.put("marketPL", marketPL);
        bookitem.put("stock", stock);
        bookitem.put("msrp", msrp);
        bookitem.put("longDescription", longDescription);
        bookitem.put("itemId", itemId);
        bookitem.put("modelNumber", modelNumber);
        bookitem.put("color", color);
        bookitem.put("maxItemsInOrder", maxItemsInOrder);
        bookitem.put("customerRating", customerRating);

        return bookitem;
    }
}
