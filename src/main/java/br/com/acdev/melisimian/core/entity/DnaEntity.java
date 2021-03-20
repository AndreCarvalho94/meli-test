package br.com.acdev.melisimian.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "dna")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DnaEntity {

    @Column(unique = true)
    private String sequenciamento;

    @Column(name = "is_simian")
    private boolean isSimio;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
