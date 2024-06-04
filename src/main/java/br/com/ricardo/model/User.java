package br.com.ricardo.model;
import br.com.ricardo.model.Enum.EUserType;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;
    @Column(nullable = false)
    private String name;
    @Column( nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EUserType userType;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Card> cards;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Complaint> complaints;

    public User(int id, String name, String login, String password, String email, EUserType userType, List<Card> cards, List<Complaint> complaints) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.cards = cards;
        this.complaints = complaints;
    }

    public User() {
        name = "";
        login = "";
        email = "";
    }

    public int getId() {
        return id;
    }

    public boolean isInstructor(){
        return userType == EUserType.INSTRUCTOR;
    }
    public boolean isAdmin(){
        return userType == EUserType.ADMIM;
    }
    public boolean isAthlete(){
        return userType == EUserType.ATHLETE;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EUserType getUserType() {
        return userType;
    }

    public void setUserType(EUserType userType) {
        this.userType = userType;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}