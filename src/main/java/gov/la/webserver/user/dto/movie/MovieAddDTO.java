package gov.la.webserver.user.dto.movie;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Movie Add DTO")
@Data
public class MovieAddDTO {
    @Schema(name = "name", description = "movie name", example = "Harry Potter")
    private String name;

    @Schema(name = "comment", description = "movie comment", example = "Good")
    private String comment;

    public MovieAddDTO(String name, String comment){
        this.name = name;
        this.comment = comment;
    }

}
