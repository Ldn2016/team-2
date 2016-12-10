package bhf.commerce.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whdinata on 12/10/16.
 */
public class Holder {
    public static List<Integer> list = new ArrayList<>();

    public static void remove(int number){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) == number){
                list.remove(i);
                break;
            }
        }
    }
}
