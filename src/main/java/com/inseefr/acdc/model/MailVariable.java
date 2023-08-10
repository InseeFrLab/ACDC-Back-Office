package com.inseefr.acdc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MailVariable {
    private String Enq_AnneeVisa;
    private String Enq_MinistereTutelle;
    private boolean Enq_ParutionJo;
    private String Enq_DateParutionJo;
    private String Enq_ServiceCollecteurSignataireNom;
    private String Enq_ServiceCollecteurSignataireFonction;
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
