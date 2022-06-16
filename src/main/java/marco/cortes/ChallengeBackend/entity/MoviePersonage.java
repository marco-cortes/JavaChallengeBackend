package marco.cortes.ChallengeBackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MoviePersonage {

    @EmbeddedId
    private MoviePersonageFK id;

    @JoinColumn(name="personage_id")
    @MapsId("personageId")
    @ManyToOne
    private Personage personage;

    @JoinColumn(name="movie_id")
    @MapsId("movieId")
    @ManyToOne
    private Movie movie;
}
