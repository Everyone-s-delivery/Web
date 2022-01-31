package everyone.delivery.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${spring.datasource.username}")
    private String userName;


    @GetMapping("/test")
    public String test() {
        return userName;
    }
}
