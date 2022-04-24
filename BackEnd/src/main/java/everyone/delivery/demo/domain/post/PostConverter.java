package everyone.delivery.demo.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import everyone.delivery.demo.common.request.dto.OrderBy;
import everyone.delivery.demo.domain.post.dtos.PostSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.Validator;

/***
 * > 글 도메인에서 사용되는 컨버터
 */
public class PostConverter {

    @Component
    @RequiredArgsConstructor
    public static class JsonStringToPostSearchDtoConverter implements Converter<String, PostSearchDto> {
        private final ObjectMapper mapper;
        private final Validator validator;

        @Override
        public PostSearchDto convert(String s) {
            PostSearchDto searchOption = mapper.convertValue(s,PostSearchDto.class);
            validator.validate(searchOption);
            return searchOption;
        }
    }
}
