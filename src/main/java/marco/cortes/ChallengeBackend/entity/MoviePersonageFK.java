package marco.cortes.ChallengeBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePersonageFK implements Serializable {

    @Column(name="personage_id")
    private Long personageId;

    @Column(name="movie_id")
    private Long movieId;
}
