package bhf.commerce;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import bhf.commerce.models.Item;

import static org.junit.Assert.assertEquals;

/**
 * Created by whdinata on 12/10/16.
 */
public class ItemTest {
    Item item;

    @Before
    public void setup() throws Exception {
        // arrange

        try{
            item = new Item(new JSONObject("{\n" +
                    "      \"_id\": \"584bc43c13400c6938e7db6c\",\n" +
                    "      \"updated_at\": \"2016-12-10T09:00:44.418Z\",\n" +
                    "      \"title\": \"Crystal Rose Bowl\",\n" +
                    "      \"description\": \"Vintage pressed green glass\",\n" +
                    "      \"imgUrl\": \"http://thumbs.picclick.com/00/s/MTIwMFgxNjAw/z/Oa8AAOSwKtVW00LR/$/Vintage-Rose-Bowl-Royal-Brierley-Crystal-Cut-Glass-_57.jpg\",\n" +
                    "      \"price\": 7.99,\n" +
                    "      \"category\": \"Accessories\",\n" +
                    "      \"userId\": \"3121425\",\n" +
                    "      \"status\": \"approved\",\n" +
                    "      \"__v\": 0,\n" +
                    "      \"created_at\": \"2016-12-10T09:00:44.389Z\"\n" +
                    "    }"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTitle(){
        // arrange
        String expectedTitle = "Crystal Rose Bowl";

        // act
        String actualTitle = item.getTitle();

        // assert
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testGetPrice(){
        // arrange
        String expectedPrice = "7.99";

        //act
        String actualPrice = item.getPrice();

        //assert
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetDescription(){
        // arrange
        String expectedDescription = "Vintage pressed green glass";

        //act
        String actualDescription = item.getDescription();

        //assert
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testGetStatus(){
        // arrange
        String expectedStatus = "approved";

        //act
        String actualStatus = item.getStatus();

        //assert
        assertEquals(expectedStatus, actualStatus);
    }
}
