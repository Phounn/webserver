package gov.la.webserver.user.controller;


import gov.la.webserver.comon.response.dto.ApiResponseDTO;
import gov.la.webserver.user.dto.MovieDTO;
import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.service.MovieService;
import gov.la.webserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
}
