package com.inseefr.acdc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colectica_json")
public class ColecticaDDITemp {
    @Id
    private String id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "json", length = 4096, columnDefinition = "jsonb")
    private Map<String, Object> json;
}
