package mk.ukim.finki.emt.labbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.labbackend.model.enumarations.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private String name;
    private Category category;
    private Long authorId;
    private Integer availableCopies;
}
