package dev.codenation.challenge.caesarcipher.api_client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChallengeModel {

    @JsonProperty("numero_casas")
    private int numeroCasas;
    private String token;
    private String cifrado;
    private String decifrado;
    @JsonProperty("resumo_criptografico")
    private String resumoCriptografico;

}


