package example.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "chat")
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "chat_information",
            joinColumns = {
                    @JoinColumn(name = "chat_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id",referencedColumnName = "id")
            }
    )
    private List<User> users;

    @OneToMany
    @JoinColumn(name = "chat_id")
    private List<Message> messages;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
