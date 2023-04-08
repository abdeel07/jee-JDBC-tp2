package org.example.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Utulisateur {
    protected Long id;
    protected String login,motDePass,nom,prenom;
    protected Role role;

    public String nomComplet()
    {
        return prenom+" "+nom;
    }
}
