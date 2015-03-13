package json;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;


/**
 * Created by root on 12.03.15.
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
