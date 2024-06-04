package br.com.ricardo.model;
import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercise")
    private int id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private int repetition;

    @Column(nullable = false)
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    public Exercise(int id, String name, int repetition, Double weight, Card card) {
        this.id = id;
        this.name = name;
        this.repetition = repetition;
        this.weight = weight;
        this.card = card;
    }

    public Exercise() {
    }

    public int getId() {
        return id;
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

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
