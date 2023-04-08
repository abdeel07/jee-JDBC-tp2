package org.example.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private Long id;
    private Double capital_Emprunté;
    private Integer nbr_Mois;
    private Double taux_Mensuel;
    private Client demandeur;
    private Double mensualité;
}
