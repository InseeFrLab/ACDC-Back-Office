package com.inseefr.acdc.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionUserAttributePairValue {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    private String type;
    private String media;
    //TODO: enum
    private String paperQuestionnaire;

    @Override
    public String toString() {
        return "CollectionUserAttributePairValue{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", media='" + media + '\'' +
                ", paperQuestionnaire='" + paperQuestionnaire + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionUserAttributePairValue that = (CollectionUserAttributePairValue) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(media, that.media) && Objects.equals(paperQuestionnaire, that.paperQuestionnaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, media, paperQuestionnaire);
    }
}
