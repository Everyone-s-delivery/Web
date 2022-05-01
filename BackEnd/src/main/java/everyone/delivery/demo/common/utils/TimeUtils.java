package everyone.delivery.demo.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class TimeUtils {

    /***
     * > Asia/Seoul 기준 long -> LocalDateTime
     * @param timeStamp
     * @return
     */
    public static LocalDateTime longToLocalDateTime(Long timeStamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), TimeZone.getTimeZone("Asia/Seoul").toZoneId());
    }

    /***
     * > Asia/Seoul 기준 LocalDateTime -> long
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToLong(LocalDateTime localDateTime){
        return localDateTime.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
    }
}
