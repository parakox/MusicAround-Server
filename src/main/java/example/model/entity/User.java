package example.model.entity;

import example.model.entity.Chat;
import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "user")
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private Boolean confirmed;
    private Boolean online;
    private Double longitude;
    private Double altitude;
    @ManyToMany(mappedBy = "users")
    private List<Chat> chats;

    public User(){

    }
    public User(String email,String nickname,String password,Boolean confirmed,Boolean online, List<Chat> chats,Double longitude,Double altitude) {
        this.email=email;
        this.nickname=nickname;
        this.password=password;
        this.confirmed=confirmed;
        this.online = online;
        this.chats = chats;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public String getEmail() {
        return email;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public String getPassword() {
        return password;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
