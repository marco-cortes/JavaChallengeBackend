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

    private String image;
    private String Title;
    private Date createdAt;
    private Integer score;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Genre genre;
}
