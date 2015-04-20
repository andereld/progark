package com.mygdx.seabattle.json;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;


/**
 * Created by esso on 12.03.15.
 * Just some simple helper methods for handling com.mygdx.seabattle.json on the client
 */
public class JsonHelper {

    public static JsonValue parseJson(String json){
        JsonReader  reader = new JsonReader();
        return reader.parse(json);
    }

    public static String buildJson(Object o){
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        return json.toJson(o);
    }

    public static String prettyPrint(JsonValue value){
        JsonValue.PrettyPrintSettings settings = new JsonValue.PrettyPrintSettings();
        settings.outputType = JsonWriter.OutputType.json;
        return value.prettyPrint(settings);
    }

}
