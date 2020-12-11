package com.lampaderum.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Roworder.
 */
@Entity
@Table(name = "roworder")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Roworder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Min(value = 0)
    @Column(name = "quantite")
    private Integer quantite;

    @ManyToOne
    @JsonIgnoreProperties(value = "roworders", allowSetters = true)
    private Streetlamp streetlamp;

    @ManyToOne
    @JsonIgnoreProperties(value = "roworders", allowSetters = true)
    private SOrder sorder;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public Roworder price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Roworder quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Streetlamp getStreetlamp() {
        return streetlamp;
    }

    public Roworder streetlamp(Streetlamp streetlamp) {
        this.streetlamp = streetlamp;
        return this;
    }

    public void setStreetlamp(Streetlamp streetlamp) {
        this.streetlamp = streetlamp;
    }

    public SOrder getSorder() {
        return sorder;
    }

    public Roworder sorder(SOrder sOrder) {
        this.sorder = sOrder;
        return this;
    }

    public void setSorder(SOrder sOrder) {
        this.sorder = sOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Roworder)) {
            return false;
        }
        return id != null && id.equals(((Roworder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Roworder{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", quantite=" + getQuantite() +
            "}";
    }
}
