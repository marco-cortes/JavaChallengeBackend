package marco.cortes.ChallengeBackend.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MovieInfo {
    private Long id;
    private String image;
    private String title;
    private Date createdAt;
}
