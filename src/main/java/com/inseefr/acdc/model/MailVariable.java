package com.inseefr.acdc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MailVariable {
    @JsonProperty("Enq_AnneeVisa")
    private String Enq_AnneeVisa;

    @JsonProperty("Enq_MinistereTutelle")
    private String Enq_MinistereTutelle;

    @JsonProperty("Enq_ParutionJo")
    private boolean Enq_ParutionJo;

    @JsonProperty("Enq_DateParutionJo")
    private String Enq_DateParutionJo;

    @JsonProperty("Enq_ServiceCollecteurSignataireNom")
    private String Enq_ServiceCollecteurSignataireNom;

    @JsonProperty("Enq_ServiceCollecteurSignataireFonction")
    private String Enq_ServiceCollecteurSignataireFonction;

    @JsonProperty("Enq_MailRespOperationnel")
    private String Enq_MailRespOperationnel;

    @Override
    public String toString() {
        return "MailVariable{" +
                "Enq_AnneeVisa='" + Enq_AnneeVisa + '\'' +
                ", Enq_MinistereTutelle='" + Enq_MinistereTutelle + '\'' +
                ", Enq_ParutionJo=" + Enq_ParutionJo +
                ", Enq_DateParutionJo='" + Enq_DateParutionJo + '\'' +
                ", Enq_ServiceCollecteurSignataireNom='" + Enq_ServiceCollecteurSignataireNom + '\'' +
                ", Enq_ServiceCollecteurSignataireFonction='" + Enq_ServiceCollecteurSignataireFonction + '\'' +
                ", Enq_MailRespOperationnel='" + Enq_MailRespOperationnel + '\'' +
                '}';
    }
}
