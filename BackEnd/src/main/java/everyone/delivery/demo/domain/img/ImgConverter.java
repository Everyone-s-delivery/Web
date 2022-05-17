package everyone.delivery.demo.domain.img;

import everyone.delivery.demo.domain.img.enums.ImageType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/***
 * > 이미지 도메인에서 사용되는 컨버터
 */
public class ImgConverter {

    @Component
    @RequiredArgsConstructor
    public static class StringToImageTypeConverter implements Converter<String, ImageType> {

        @Override
        public ImageType convert(String s) {
            return ImageType.valueOf(s);
        }
    }
}
