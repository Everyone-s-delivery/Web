package everyone.delivery.demo.security.Sign.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResult {
    private String token;
    private Long userId;
}