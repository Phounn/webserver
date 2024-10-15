package gov.la.webserver.user.repository;

import gov.la.webserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//public class UserRepository {
//    public static final List<User> storedDatabase = new ArrayList<>();
//
//    static {
//        storedDatabase.add(new User("1", "Tony Stark", 20, "Iron Man"));
//        storedDatabase.add(new User("2", "Tony Stark", 22, "Iron Man"));
//        storedDatabase.add(new User("3", "Tony Stark", 23, "Iron Man"));
//        storedDatabase.add(new User("4", "Tony Stark", 24, "Iron Man"));
//        storedDatabase.add(new User("5", "Tony Stark", 25, "Iron Man"));
//    }
//
//    public List<User> findAll() {
//        return storedDatabase;
//    }
//
//    public void createUser(User user) {
//        storedDatabase.add(user);
//    }
//
//    public User findById(String id) {
//        return storedDatabase.stream()
//                .filter(e -> id.equalsIgnoreCase(e.getIdentifier()))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void updateUser(final String id, String nickname) {
//        User findUser = findById(id);
//
//        if (findUser != null) {
//            findUser.changeNickName(nickname);
//        }
//    }
//
//    public void deleteUser(final String id) {
//        User findUser = findById(id);
//
//        if (findUser != null) {
//            storedDatabase.remove(findUser);
//        }
//    }
//}

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccount_username(String username);
}
