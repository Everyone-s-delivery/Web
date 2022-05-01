package everyone.delivery.demo.security.user.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import everyone.delivery.demo.common.utils.TimeUtils;
import everyone.delivery.demo.common.validation.annotaion.NotOverlappedEmail;
import everyone.delivery.demo.security.user.UserEntity;
import everyone.delivery.demo.security.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements UserDetails {

    private Long userId;

    @NotOverlappedEmail(message = "invalid user data. email dose not duplicated")
    @NotNull(message = "Not enough user data. email cannot be null.")
    @Email(message = "invalid email form.")
    private String email;

    @NotOverlappedEmail(message = "invalid user data. nickName dose not duplicated")
    @NotNull(message = "Not enough user data. nickName cannot be null.")
    private String nickName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //직렬화 과정에서 제외
    @NotNull(message = "Not enough user data.")
    private String password;

    @NotNull(message = "Not enough user data.")
    private String address;

    private Set<UserRole> roles;

    private Long regDate;

    private Long updateDate;

    public UserEntity toEntity(){
        return UserEntity.builder()
                            .userId(userId)
                            .email(email)
                            .nickName(nickName)
                            .password(password)
                            .roles(roles)
                            .address(address)
                            .regDate(TimeUtils.longToLocalDateTime(regDate))
                            .updateDate(TimeUtils.longToLocalDateTime(updateDate))
                            .build();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	//직렬화 과정에서 제외시킨다는 의미
    public boolean isAdmin(){
        return roles.contains(UserRole.ROLE_ADMIN);
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	//직렬화 과정에서 제외시킨다는 의미
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        for (UserRole role : roles) {
            list.add(new SimpleGrantedAuthority(role.name()));
        }
        return list;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	//직렬화 과정에서 제외시킨다는 의미
    @Override
    public String getUsername() {
        return this.email;
    }

    //계정이 만료되었는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠기지 않았는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정 패스워드가 만료 안됬는지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용 가능한지
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

}
