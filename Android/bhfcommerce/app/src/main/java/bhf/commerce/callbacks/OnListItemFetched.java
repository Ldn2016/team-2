package bhf.commerce.callbacks;

import bhf.commerce.models.Item;

/**
 * Created by whdinata on 12/10/16.
 */
public interface OnListItemFetched {
    void onItemFetched(Item items[]);
}
