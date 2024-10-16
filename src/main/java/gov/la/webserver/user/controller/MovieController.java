package gov.la.webserver.user.controller;


import gov.la.webserver.comon.response.dto.ApiResponseDTO;
import gov.la.webserver.user.dto.movie.MovieAddComment;
import gov.la.webserver.user.dto.movie.MovieDTO;
import gov.la.webserver.user.entity.Movie;
import gov.la.webserver.user.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController {
    private final MovieService movieService;
    @Operation(
            summary = "Get All Movie List",
            description = "Query saved users in the form of a list.",
            security = @SecurityRequirement(name = "basicScheme")
    )

    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping
    public Flux<ApiResponseDTO<Object>> getAllMovies() {
        log.info("call getAllMovies");
        List<MovieDTO> userList = movieService.findAllMovies();
        return Flux.fromIterable(userList)
                .map(user ->
                        ApiResponseDTO.builder()
                                .code(200)
                                .message("Movie List found")
                                .body(user)
                                .build());
    }

    @Operation(
            summary = "Add Comment",
            description = "Add the comment."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))
            }
    )
    @PostMapping
    public Mono<ApiResponseDTO<Object>> addComment(@RequestBody final MovieAddComment movieAddComment) {
        log.info("Add Comment >>> {}", movieAddComment.getComment());
        MovieDTO movieDTO = movieService.addComment(movieAddComment);
        return Mono.justOrEmpty(
                ApiResponseDTO.builder()
                        .code(200)
                        .message("Add comment")
                        .build()

        );
    }
    @Operation(
            summary = "Update Comment",
            description = "Update the comment"
    )
    @Parameters({
            @Parameter(name = "id", description = "id of movie", example = "1", required = true)
    })
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation =  Movie.class))
            }
    )
    @PutMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> updateComment(@PathVariable final Long id, @RequestBody final MovieDTO movieDTO){
        log.info("call updateComment >>> comment {}", movieDTO.getComment());
        MovieDTO resMovieDTO = movieService.updateComment(id, movieDTO);
        return Mono.justOrEmpty(ApiResponseDTO.builder()
                .code(200)
                .message("updated user")
                .body(resMovieDTO)
                .build()
        ).switchIfEmpty(
                Mono.just(
                        ApiResponseDTO.builder()
                                .code(404)
                                .message("User not found")
                                .build()
                )
        );
    }
}
