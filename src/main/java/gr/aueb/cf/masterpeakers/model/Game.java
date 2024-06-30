package gr.aueb.cf.masterpeakers.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "games")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Game extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String title;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String platform;

    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private double price;

    @EqualsAndHashCode.Include
    private String imageName;
}
