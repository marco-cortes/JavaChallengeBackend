package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;


}
