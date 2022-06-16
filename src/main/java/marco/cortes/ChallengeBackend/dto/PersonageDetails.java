package marco.cortes.ChallengeBackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonageDetails {
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Float weight;
    private String history;
    private List<MovieInfo> movies;
}
