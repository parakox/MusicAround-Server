package example.model.entity;

import example.model.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "verification_token")
@Table(name = "verification_token")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date expiryDate;

    public VerificationToken(String token,User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }

    public VerificationToken(){

    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
