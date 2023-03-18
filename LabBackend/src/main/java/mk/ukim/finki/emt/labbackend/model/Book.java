package mk.ukim.finki.emt.labbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.labbackend.model.enumarations.Category;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Book {
    /**
     * id (Long), name (String),
     * category (enum), author (Author), availableCopies (Integer)
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
