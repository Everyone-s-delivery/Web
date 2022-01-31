package everyone.delivery.demo.common.exception.error;


import java.util.HashMap;
import java.util.Map;

public class ErrorMap {

    private static final Map<String, RestError> errorMap = new HashMap<>();

    public static void setError(String key, RestError restError){
        errorMap.put(key,restError);
    }

    public static RestError getError(String key){
        return errorMap.get(key);
    }
}
