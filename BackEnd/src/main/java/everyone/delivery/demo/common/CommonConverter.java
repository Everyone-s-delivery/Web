package everyone.delivery.demo.common;

import everyone.delivery.demo.common.request.dto.KeyColumn;
import everyone.delivery.demo.common.request.dto.OrderBy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * > 일반적으로 많이 사용 될 컨버터를 정의
 * > 특정 도메인에서만 사용 될 컨버터는 해당 도메인 페키지 하위에 만들 것
 * > 참고 자료: [https://atoz-develop.tistory.com/entry/Spring-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%B0%94%EC%9D%B8%EB%94%A9-PropertyEditor-Converter-%EA%B7%B8%EB%A6%AC%EA%B3%A0-Formatter#recentComments]
 */
public class CommonConverter {

    @Component
    public static class StringToKeyColumnConverter implements Converter<String, KeyColumn> {
        @Override
        public KeyColumn convert(String s) {
            return KeyColumn.valueOf(s);
        }
    }

    @Component
    public static class StringToOrderByConverter implements Converter<String, OrderBy> {
        @Override
        public OrderBy convert(String s) {
            return OrderBy.valueOf(s);
        }
    }

    @Component
    public static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String s) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(s, formatter);
            return dateTime;
        }
    }

}
