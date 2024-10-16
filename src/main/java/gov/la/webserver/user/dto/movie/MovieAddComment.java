package gov.la.webserver.user.dto.movie;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Movie Comment DTO")
@NoArgsConstructor
@Data
public class MovieAddComment {
    @Schema(name="name", description = "Add comment", example = "Good")
    private String name;

   @Schema(name="comment", description = "Add comment", example = "Good")
   private String comment;

   public MovieAddComment(String name, String comment){
       this.name = name;
       this.comment = comment;
   }
}
