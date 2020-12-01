package com.lampaderum.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ApplicationUser.
 */
@Entity
@Table(name = "application_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "siret")
    private String siret;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "applicationUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<SOrder> sorders = new HashSet<>();

    @OneToMany(mappedBy = "applicationUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Creditcard> creditcards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiret() {
        return siret;
    }

    public ApplicationUser siret(String siret) {
        this.siret = siret;
        return this;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public User getUser() {
        return user;
    }

    public ApplicationUser user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<SOrder> getSorders() {
        return sorders;
    }

    public ApplicationUser sorders(Set<SOrder> sOrders) {
        this.sorders = sOrders;
        return this;
    }

    public ApplicationUser addSorder(SOrder sOrder) {
        this.sorders.add(sOrder);
        sOrder.setApplicationUser(this);
        return this;
    }

    public ApplicationUser removeSorder(SOrder sOrder) {
        this.sorders.remove(sOrder);
        sOrder.setApplicationUser(null);
        return this;
    }

    public void setSorders(Set<SOrder> sOrders) {
        this.sorders = sOrders;
    }

    public Set<Creditcard> getCreditcards() {
        return creditcards;
    }

    public ApplicationUser creditcards(Set<Creditcard> creditcards) {
        this.creditcards = creditcards;
        return this;
    }

    public ApplicationUser addCreditcard(Creditcard creditcard) {
        this.creditcards.add(creditcard);
        creditcard.setApplicationUser(this);
        return this;
    }

    public ApplicationUser removeCreditcard(Creditcard creditcard) {
        this.creditcards.remove(creditcard);
        creditcard.setApplicationUser(null);
        return this;
    }

    public void setCreditcards(Set<Creditcard> creditcards) {
        this.creditcards = creditcards;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser)) {
            return false;
        }
        return id != null && id.equals(((ApplicationUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + getId() +
            ", siret='" + getSiret() + "'" +
            "}";
    }
}
