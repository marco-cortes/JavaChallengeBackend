package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Image is required")
    @Column(nullable = false)
    private String image;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date createdAt;

    @NotNull(message = "Score is required")
    @Min(value = 1, message = "Age must be greater than 1 ")
    @Max(value = 5, message = "Age must be less than 5")
    @Column(nullable = false)
    private Integer score;

    @NotNull(message = "Genre is required")
    @JoinColumn(nullable = false)
    @ManyToOne
    private Genre genre;

    @ManyToMany
    private List<Personage> personages;
}
