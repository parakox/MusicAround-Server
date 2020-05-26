package example.model.entity;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "message")
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private String text;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "chat_id")
    private Long chatId;

    public Message(){

    }

    public Message(Date date,String text,Long userId,Long chatId){
        this.date=date;
        this.text=text;
        this.userId=userId;
        this.chatId=chatId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
