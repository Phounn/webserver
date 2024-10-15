package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.MovieAddDTO;
import gov.la.webserver.user.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> findAllMovies();

    MovieDTO addMovie(MovieAddDTO movieAddDTO);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    Boolean deleteMovie(Long id);

    MovieDTO findOneMovie(Long id);

}
