package org.example.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor
public class Client extends Utulisateur{

private String email,cin,tel,adresse;
private Sexe sexe;

    @Override
    public String toString() {
        return "Client{" +
                "email='" + email + '\'' +
                ", cin='" + cin + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", sexe=" + sexe +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
