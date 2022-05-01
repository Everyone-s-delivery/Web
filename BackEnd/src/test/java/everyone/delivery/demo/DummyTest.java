package everyone.delivery.demo;


import everyone.delivery.demo.common.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@Slf4j
public class DummyTest {

    @Test
    public void TimeTest() {
        long beforeTime = System.currentTimeMillis();
        LocalDateTime localDateTime = TimeUtils.longToLocalDateTime(beforeTime);
        long afterTime = TimeUtils.localDateTimeToLong(localDateTime);
        System.out.println(beforeTime == afterTime);
    }
}
