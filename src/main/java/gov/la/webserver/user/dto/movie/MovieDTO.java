package gov.la.webserver.user.dto.movie;


import gov.la.webserver.user.entity.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "Movie DTO")
@Getter
@AllArgsConstructor
public class MovieDTO {
    @Schema(name = "id", description = "movie id", example = "1")
    private Long id;

    @Schema(name = "name", description = "movie name", example = "Harry Potter")
    private String name;

    @Schema(name = "comment", description = "movie review", example = "Good")
    private String comment;

    public MovieDTO(final Movie movie){
        this.id = movie.getId();
        this.name = movie.getName();
        this.comment = movie.getComment();
    }
}
