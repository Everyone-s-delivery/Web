package everyone.delivery.demo.common.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class FileConfiguration {

    @Value("${file.path}")
    private String path;
}
