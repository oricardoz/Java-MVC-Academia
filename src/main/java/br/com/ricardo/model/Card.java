package br.com.ricardo.model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private int id;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Exercise> exercises;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Card(int id, List<Exercise> exercises, Date date, User user) {
        this.id = id;
        this.exercises = exercises;
        this.date = date;
        this.user = user;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}