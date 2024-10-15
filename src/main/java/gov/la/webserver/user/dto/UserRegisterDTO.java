package gov.la.webserver.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "User Register DTO")
@Data
public class UserRegisterDTO {

    @Schema(name = "name", description = "uesr name", example = "Tony Stark")
    private String name;

    @Schema(name = "age", description = "uesr age", example = "18")
    private Integer age;


    @Schema(name = "nickName", description = "uesr nickname", example = "Iron Man")
    private String nickName;

    @Schema(name = "username", description = "user account", example = "Tony Stark")
    private String username;

    @Schema(name = "password", description = "user password", example = "1234")
    private String password;

    public UserRegisterDTO(String name, Integer age, String nickName) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;

    }
}
