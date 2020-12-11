package com.lampaderum.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Rowcart.
 */
@Entity
@Table(name = "rowcart")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rowcart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JsonIgnoreProperties(value = "rowcarts", allowSetters = true)
    private Streetlamp streetlamp;

    @ManyToOne
    @JsonIgnoreProperties(value = "rowcarts", allowSetters = true)
    private Cartpersi cartpersi;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Rowcart quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Streetlamp getStreetlamp() {
        return streetlamp;
    }

    public Rowcart streetlamp(Streetlamp streetlamp) {
        this.streetlamp = streetlamp;
        return this;
    }

    public void setStreetlamp(Streetlamp streetlamp) {
        this.streetlamp = streetlamp;
    }

    public Cartpersi getCartpersi() {
        return cartpersi;
    }

    public Rowcart cartpersi(Cartpersi cartpersi) {
        this.cartpersi = cartpersi;
        return this;
    }

    public void setCartpersi(Cartpersi cartpersi) {
        this.cartpersi = cartpersi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rowcart)) {
            return false;
        }
        return id != null && id.equals(((Rowcart) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rowcart{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
