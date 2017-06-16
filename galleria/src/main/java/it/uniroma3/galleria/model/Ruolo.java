package it.uniroma3.galleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_roles")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name="role")
    private String ruolo;
    @NotNull
    @Column(name="username")
    private String nome;
    
public Ruolo(){
this.ruolo = "ROLE_ADMIN";
}
public Ruolo(String nome){
this.nome = nome;
this.ruolo = "ROLE_ADMIN";
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getRuolo() {
return ruolo;
}

public void setRuolo(String ruolo) {
this.ruolo = ruolo;
}

public String getNome() {
return nome;
}

public void setNome(String nome) {
this.nome = nome;
}
}
