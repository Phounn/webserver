package gov.la.webserver.user.dto;

import gov.la.webserver.user.entity.UserChangeLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class UserChangeLogDTO {
    @Schema(name = "id", description = "user id", example = "1")
    private Long id;

    @Schema(name = "beforeValue", description = "user name", example = "Tony Stark")
    private String beforeValue;

    @Schema(name = "beforeField", description = "user age", example = "12")
    private String beforeField;

    @Schema(name = "afterValue", description = "user nickName", example = "Tony Stark")
    private String afterValue;

    @Schema(name = "afterField", description = "username", example = "SFA")
    private String afterField;


    public UserChangeLogDTO(UserChangeLog entity) {
        this.id = entity.getId();
        this.beforeField = entity.getBeforeField();
        this.beforeValue = entity.getBeforeValue();
        this.afterValue = entity.getAfterValue();
        this.afterField = entity.getAfterField();
    }
}
