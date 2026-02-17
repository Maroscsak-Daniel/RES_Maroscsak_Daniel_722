package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class JsonUtil {

    private static final Gson GSON =
            new GsonBuilder().create();

    private JsonUtil() {}

    public static <T> List<T> readList(Path path, Class<T> clazz) {
        try (Reader r = Files.newBufferedReader(path)) {
            Type type = TypeToken
                    .getParameterized(List.class, clazz)
                    .getType();
            return GSON.fromJson(r, type);
        } catch (Exception e) {
            throw new RuntimeException("JSON error: " + e.getMessage(), e);
        }
    }
}
