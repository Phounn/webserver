package gov.la.webserver.user.repository;

import gov.la.webserver.user.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
