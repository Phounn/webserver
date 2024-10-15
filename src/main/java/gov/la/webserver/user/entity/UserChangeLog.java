package gov.la.webserver.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


// swagger
@Schema(description = "user_log")

// for jpa
@Entity
@Table(name = "user_change_log")

// for lombok
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
@Getter
public class UserChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "beforeField")
    private String beforeField;

    @Column(name = "beforeValue")
    private String beforeValue;

    @Column(name = "afterField")
    private String afterField;

    @Column(name = "afterValue")
    private String afterValue;

    @Column(name = "createTime")
    private LocalDateTime createTime = LocalDateTime.now();

    public void setId(Long id) {
        this.id = id;
    }

    public void setBeforeField(String beforeField) {
        this.beforeField = beforeField;
    }

    public void setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
    }

    public void setAfterField(String afterField) {
        this.afterField = afterField;
    }

    public void setAfterValue(String afterValue) {
        this.afterValue = afterValue;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public UserChangeLog(String beforeValue, String beforeField, String afterValue, String afterField){
        this.beforeField = beforeField;
        this.beforeValue = beforeValue;
        this.afterField = afterField;
        this.afterValue = afterValue;
    }
    public static UserChangeLog createUserLogEntity(String filedName, String beforeValue, String afterValue){
//        final UserChangeLog changeLog = new UserChangeLog();
        return new UserChangeLog(filedName, beforeValue, filedName, afterValue);
    }
}
