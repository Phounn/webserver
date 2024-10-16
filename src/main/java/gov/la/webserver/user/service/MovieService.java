package gov.la.webserver.user.service;

import gov.la.webserver.user.dto.movie.MovieAddComment;
import gov.la.webserver.user.dto.movie.MovieAddDTO;
import gov.la.webserver.user.dto.movie.MovieDTO;

import java.util.List;

public interface MovieService {
    List<MovieDTO> findAllMovies();

    MovieDTO addMovie(MovieAddDTO movieAddDTO);

    MovieDTO addComment(MovieAddComment movieAddComment);

    MovieDTO updateComment(Long id, MovieDTO movieDTO);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    Boolean deleteMovie(Long id);

    MovieDTO findOneMovie(Long id);

}
