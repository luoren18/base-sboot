package top.luoren.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author luoren
 * @date 2019/9/5 14:57
 */
public class JsonUtil {
    private static final Gson GSON = new Gson();

    /**
     * Object转成JSON数据
     */
    public static String objectToJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return GSON.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return GSON.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            return null;
        }

    }
}
