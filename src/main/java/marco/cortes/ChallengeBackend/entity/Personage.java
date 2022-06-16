package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private Integer age;
    private Float weight;

    @Lob @Basic (fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "text")
    private String history;
}
