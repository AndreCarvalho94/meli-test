package br.com.acdev.melisimian.core.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dna")
public class DnaEntity {

    @Lob
    @Column
    private Blob sequenciamento;

    @Column(unique = true)
    private String hash;

    @Column(name = "is_simian")
    private boolean isSimio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
