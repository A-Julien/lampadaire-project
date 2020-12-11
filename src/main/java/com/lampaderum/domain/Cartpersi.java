package com.lampaderum.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cartpersi.
 */
@Entity
@Table(name = "cartpersi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cartpersi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToMany(mappedBy = "cartpersi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Rowcart> rowcarts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Rowcart> getRowcarts() {
        return rowcarts;
    }

    public Cartpersi rowcarts(Set<Rowcart> rowcarts) {
        this.rowcarts = rowcarts;
        return this;
    }

    public Cartpersi addRowcart(Rowcart rowcart) {
        this.rowcarts.add(rowcart);
        rowcart.setCartpersi(this);
        return this;
    }

    public Cartpersi removeRowcart(Rowcart rowcart) {
        this.rowcarts.remove(rowcart);
        rowcart.setCartpersi(null);
        return this;
    }

    public void setRowcarts(Set<Rowcart> rowcarts) {
        this.rowcarts = rowcarts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cartpersi)) {
            return false;
        }
        return id != null && id.equals(((Cartpersi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cartpersi{" +
            "id=" + getId() +
            "}";
    }
}
