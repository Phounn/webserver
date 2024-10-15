package gov.la.webserver.user.controller;

import gov.la.webserver.comon.response.dto.ApiResponseDTO;
import gov.la.webserver.user.dto.UserChangeLogDTO;
import gov.la.webserver.user.service.UserChangeLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-change-logs")
@RequiredArgsConstructor
public class UserChangeLogController {
    public final UserChangeLogService userChangeLogService;

    @Operation(
            summary = "Get All User Change List",
            description = "Query saved user change in the form of a list.",
            security = @SecurityRequirement(name = "basicScheme")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @Secured("ROLE_ADMIN")
    @GetMapping
    public Mono<ApiResponseDTO<Object>> getAllUserChangelog(){
        List <UserChangeLogDTO> userChangeLogDTOList = userChangeLogService.getAllUserChangelog();

        return Mono.just(ApiResponseDTO.builder()
                        .code(200)
                        .message("find all user change logs")
                        .body(userChangeLogDTOList)
                        .build());
    }
}
