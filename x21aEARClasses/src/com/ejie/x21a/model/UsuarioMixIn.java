package com.ejie.x21a.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



public interface UsuarioMixIn {
    @JsonProperty("nombre") int nombre();
    @JsonProperty("apellido1") int apellido1();
    @JsonIgnore int getApellido2();
}
