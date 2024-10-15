package gov.la.webserver.user.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


//For Jpa
@Entity
@Table(name = "users_user")

//lambok
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protect from outsider
public class User {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "nickname")
    private String nickname;

    @Embedded
    private Account account;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(final String nickName, final Integer age, final String name) {
        this.name = name;
        this.age = age;
        this.nickname = nickName;
        this.role = Role.USER;
    }
    public User(String name, Integer age, String nickName, Account account){
        this(name, age, nickName);
        this.account = account;
    }

    public void changeNickName(String nickName) {
        this.nickname = nickName;
    }

    public void changeAge(Integer age){
        this.age = age;
    }

    public void  changeName(String name){
        this.name = name;
    }

    public static User createUserWithAccount(final String name, final Integer age, final String nickName, final String username, final String password){
        return new User(name, age, nickName, Account.create(username, password));

    }
}
