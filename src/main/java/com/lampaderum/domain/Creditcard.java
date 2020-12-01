package com.lampaderum.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Creditcard.
 */
@Entity
@Table(name = "creditcard")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Creditcard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numcarte")
    private String numcarte;

    @Column(name = "dateexpiration")
    private LocalDate dateexpiration;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JsonIgnoreProperties(value = "creditcards", allowSetters = true)
    private ApplicationUser applicationUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumcarte() {
        return numcarte;
    }

    public Creditcard numcarte(String numcarte) {
        this.numcarte = numcarte;
        return this;
    }

    public void setNumcarte(String numcarte) {
        this.numcarte = numcarte;
    }

    public LocalDate getDateexpiration() {
        return dateexpiration;
    }

    public Creditcard dateexpiration(LocalDate dateexpiration) {
        this.dateexpiration = dateexpiration;
        return this;
    }

    public void setDateexpiration(LocalDate dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getCode() {
        return code;
    }

    public Creditcard code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public Creditcard applicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
        return this;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Creditcard)) {
            return false;
        }
        return id != null && id.equals(((Creditcard) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Creditcard{" +
            "id=" + getId() +
            ", numcarte='" + getNumcarte() + "'" +
            ", dateexpiration='" + getDateexpiration() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
