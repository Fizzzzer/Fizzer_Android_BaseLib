package com.fizzer.doraemon.base.Http.Gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Create by Fizzer on 2019/6/14
 * Email: Fizzer503@gmail.com
 * Description: Gson解析int类型时异常处理类
 * 如果int类型返回异常时，返回-1
 */

public class GsonIntegerDefaultAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        try {
            if (json.getAsString().equals("") || json.getAsString().equalsIgnoreCase("null")) {
                return -1;
            }
        } catch (Exception ex) {
        }
        try {
            return json.getAsInt();
        } catch (NumberFormatException ex) {
            throw new JsonSyntaxException(ex);
        }
    }

    @Override
    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
