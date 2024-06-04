package br.com.ricardo.model;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_complaint")
    private int id;

    private String topic;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id_complaint")
    private User user;

    public Complaint(int id, String topic, String text, User user) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.user = user;
    }

    public Complaint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
