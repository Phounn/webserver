package gov.la.webserver.user.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies_movie")

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "comment")
    private String comment;

    public Movie(final String name, final String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Movie(String comment){
        this.comment = comment;
    }
    public void changeComment(String comment){
        this.comment = comment;
    }

    public static Movie addComment(final String name, final String comment){
        return new Movie(name, comment);
    }


}
