package gov.la.webserver.user.service.impl;

import gov.la.webserver.user.dto.movie.MovieAddComment;
import gov.la.webserver.user.dto.movie.MovieAddDTO;
import gov.la.webserver.user.dto.movie.MovieDTO;
import gov.la.webserver.user.entity.Movie;
import gov.la.webserver.user.repository.MovieRepository;
import gov.la.webserver.user.service.MovieService;
import gov.la.webserver.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;

    @Override
    public List<MovieDTO> findAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO addMovie(MovieAddDTO movieAddDTO) {
        return null;
    }

    @Override
    public MovieDTO addComment(MovieAddComment movieAddComment) {
        String movieGetName = movieAddComment.getName();
        log.info("This is movieGetName>>>>>>>> {}", movieGetName);
       Movie movie = Movie.addComment(movieAddComment.getName(), movieAddComment.getComment());
       Movie savedMovie = movieRepository.save(movie);
       return new MovieDTO(savedMovie);
    }

    @Override
    public MovieDTO updateComment(Long id, MovieDTO movieDTO) {
        Movie savedMovie = movieRepository.findById(id).orElseThrow(UserNotFoundException::new);

        savedMovie.changeComment(movieDTO.getComment());
        Movie updateComment = movieRepository.save(savedMovie);
        return new MovieDTO(updateComment);
    }


    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        return null;
    }

    @Override
    public Boolean deleteMovie(Long id) {
        return null;
    }

    @Override
    public MovieDTO findOneMovie(Long id) {
        return null;
    }
}
