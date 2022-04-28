package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "request")
public class Request {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "number")
    private int number;
    @OneToOne(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Response response;
}
