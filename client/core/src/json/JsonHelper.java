package json;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;


/**
 * Created by esso on 12.03.15.
 * Just some simple helper methods for handling json on the client
 */
public class JsonHelper {

    public static JsonValue getJSonObject(String json){
        JsonReader  reader = new JsonReader();
        return reader.parse(json);
    }

    public static String JsonBuilder(Object o){
        Json json = new Json();
        return json.toJson(o);
    }

}
