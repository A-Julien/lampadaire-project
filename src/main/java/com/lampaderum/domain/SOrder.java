package com.lampaderum.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A SOrder.
 */
@Entity
@Table(name = "sorder")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "datecommande")
    private LocalDate datecommande;

    @OneToMany(mappedBy = "sorder")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Roworder> roworders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatecommande() {
        return datecommande;
    }

    public SOrder datecommande(LocalDate datecommande) {
        this.datecommande = datecommande;
        return this;
    }

    public void setDatecommande(LocalDate datecommande) {
        this.datecommande = datecommande;
    }

    public Set<Roworder> getRoworders() {
        return roworders;
    }

    public SOrder roworders(Set<Roworder> roworders) {
        this.roworders = roworders;
        return this;
    }

    public SOrder addRoworder(Roworder roworder) {
        this.roworders.add(roworder);
        roworder.setSorder(this);
        return this;
    }

    public SOrder removeRoworder(Roworder roworder) {
        this.roworders.remove(roworder);
        roworder.setSorder(null);
        return this;
    }

    public void setRoworders(Set<Roworder> roworders) {
        this.roworders = roworders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SOrder)) {
            return false;
        }
        return id != null && id.equals(((SOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SOrder{" +
            "id=" + getId() +
            ", datecommande='" + getDatecommande() + "'" +
            "}";
    }
}
