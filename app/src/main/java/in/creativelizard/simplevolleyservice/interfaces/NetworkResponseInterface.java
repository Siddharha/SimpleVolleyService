package in.creativelizard.simplevolleyservice.interfaces;

import org.json.JSONObject;

/**
 * Created by siddhartha on 13/2/18.
 */

public interface NetworkResponseInterface {
    void onComplete(String response);
    void onInitialize();
}
