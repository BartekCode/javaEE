package readstack.domain.api;

import org.apache.commons.codec.digest.DigestUtils;
import readstack.domain.user.User;
import readstack.domain.user.UserDao;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

//No i klasę warstwy usług, w której będzie metoda odpowiedzialna za rejestrację.
public class UserService {
    private UserDao userDao= new UserDao();


    //mapujemy usera i zapisujemy go!
    public void register(UserRegistration userRegistration){
        User userToSave = UserMapper.map(userRegistration);
//        metoda do szyfrowania będzie wykorzystana w aktualnej metodzie register(),
        hashPasswordWithSha256(userToSave);
        userDao.save(userToSave);
    }

//    szyfrujemy haslo
    private void hashPasswordWithSha256(User user) {
        String sha256Password = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(sha256Password);//ustawiamy haslo jako zaszyfrowane

    }



    //mapujemy User
    private static class UserMapper{
        static User map(UserRegistration userRegistration){
            return new User(userRegistration.getUsername(), userRegistration.getEmail(), userRegistration.getPassword(),
                    LocalDateTime.now());

        }
    }
}
