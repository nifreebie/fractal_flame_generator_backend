package nifreebie.fractal_flame_generator_backend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @Column
    private String name;

    @Column(length = 1024)
    private String s3Url;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
