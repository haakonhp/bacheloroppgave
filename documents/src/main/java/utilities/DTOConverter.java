package utilities;

import com.google.gson.Gson;

import java.util.List;

public class DTOConverter {
    public static <T,V> List<V> convertList(List<T> originalList, Class<V> castTo) {
        Gson gson = new Gson();
        return originalList.stream().map((original) -> gson.fromJson(gson.toJson(original), castTo)).toList();
    }
}
