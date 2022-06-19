package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Image is required")
    @Column(nullable = false)
    private String image;

    @NotNull(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Age is required")
    @Column(nullable = false)
    private Integer age;

    @NotNull(message = "Weight is required")
    @Column(nullable = false)
    private Float weight;

    @NotNull(message = "History is required")
    @Lob @Basic (fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "text")
    private String history;
}
