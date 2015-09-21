package com.ejie.x21a.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public interface UsuarioMixIn {
    @JsonProperty("nombre") int nombre();
    @JsonProperty("apellido1") int apellido1();
    @JsonIgnore int getApellido2();
}
