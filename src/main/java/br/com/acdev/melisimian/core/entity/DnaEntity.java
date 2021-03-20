package br.com.acdev.melisimian.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dna")
public class DnaEntity {

    @Column(unique = true)
    private String sequenciamento;

    @Column(name = "is_simian")
    private boolean isSimio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
