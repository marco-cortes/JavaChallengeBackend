package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = false)
    private Integer score;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Genre genre;

    @ManyToMany
    private List<Personage> personages;
}
