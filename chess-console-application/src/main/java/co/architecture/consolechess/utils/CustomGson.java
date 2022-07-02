package co.architecture.consolechess.utils;

import com.google.gson.Gson;

import java.util.Objects;

public class CustomGson {
    private final static Gson gson = new Gson();

    public static <T>String toJson(T objects) {
        return gson.toJson(objects);
    }
}
