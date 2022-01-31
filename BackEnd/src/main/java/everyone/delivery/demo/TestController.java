package everyone.delivery.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${test}")
    private String test2;

    @GetMapping("/test")
    public String test() {
        return userName;
    }


    @GetMapping("/test2")
    public String test2() {
        return test2;
    }
}
