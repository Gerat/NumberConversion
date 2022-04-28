package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "response")
public class Response {
    @Id
    @Column(name = "id")
    @JsonProperty(value = "id")
    private UUID id;
    @Column(name = "number")
    @JsonProperty(value = "number")
    private int number;
    @Column(name = "string_presentation")
    @JsonProperty(value = "string_presentation")
    private String stringPresentation;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private Request request;
}
