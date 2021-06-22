package readstack.domain.api;

import java.util.List;

//Definiujemy klasę DTO o nazwie UserRegistration.
//czyli kalsa ktora bedzie pomagala pobrac dane z User by nie bylo to bezposrednio z bazdy danych
public class UserRegistration {
    private String username;
    private String email;
    private String password;

    public List<DiscoveryBasicInfo> getUserVotedDisc() {
        return userVotedDisc;
    }

    private List<DiscoveryBasicInfo> userVotedDisc;

    //moze dodac liste z fav disc by pozniej po zalogowaniu moc ja wyswietlic

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRegistration(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
