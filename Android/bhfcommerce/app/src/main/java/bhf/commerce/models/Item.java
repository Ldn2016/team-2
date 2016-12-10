package bhf.commerce.models;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by whdinata on 12/9/16.
 */
public class Item {
    private Bitmap bitmap;
    private String imageUrl;
    private String title;
    private String description;
    private String suggestedPrice;
    private String status;
    private String price;

    public Item(){}

    public Item(JSONObject obj){
        try{
            title = obj.getString("title");
            description = obj.getString("description");
            suggestedPrice = obj.getString("suggestedPrice");
            price = obj.getString("price");
            status = obj.getString("status");
            imageUrl = obj.getString("imgUrl");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(String suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
