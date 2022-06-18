package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Image is required")
    @Column(nullable = false)
    private String image;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Age is required")
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "Weight is required")
    @Column(nullable = false)
    private Float weight;

    @NotBlank(message = "History is required")
    @Lob @Basic (fetch = FetchType.LAZY)
    @Column(nullable = false, columnDefinition = "text")
    private String history;
}
