package com.lampaderum.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Streetlamp.
 */
@Entity
@Table(name = "streetlamp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Streetlamp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libstreetlamp")
    private String libstreetlamp;

    @Column(name = "modelestreetlamp")
    private String modelestreetlamp;

    @Column(name = "dureeviestreetlamp")
    private Double dureeviestreetlamp;

    @Column(name = "uniteviestreetlamp")
    private String uniteviestreetlamp;

    @Column(name = "materiaustreetlamp")
    private String materiaustreetlamp;

    @Column(name = "liblampe")
    private String liblampe;

    @Column(name = "pwlampe")
    private Double pwlampe;

    @Column(name = "formelampe")
    private String formelampe;

    @Column(name = "modelelampe")
    private String modelelampe;

    @Column(name = "dureevielampe")
    private Double dureevielampe;

    @Column(name = "unitevielampe")
    private String unitevielampe;

    @Column(name = "voltlampe")
    private Double voltlampe;

    @Column(name = "templampe")
    private Double templampe;

    @Column(name = "imagepathstreetlamp")
    private String imagepathstreetlamp;

    @Column(name = "stockstreetlamp")
    private Integer stockstreetlamp;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibstreetlamp() {
        return libstreetlamp;
    }

    public Streetlamp libstreetlamp(String libstreetlamp) {
        this.libstreetlamp = libstreetlamp;
        return this;
    }

    public void setLibstreetlamp(String libstreetlamp) {
        this.libstreetlamp = libstreetlamp;
    }

    public String getModelestreetlamp() {
        return modelestreetlamp;
    }

    public Streetlamp modelestreetlamp(String modelestreetlamp) {
        this.modelestreetlamp = modelestreetlamp;
        return this;
    }

    public void setModelestreetlamp(String modelestreetlamp) {
        this.modelestreetlamp = modelestreetlamp;
    }

    public Double getDureeviestreetlamp() {
        return dureeviestreetlamp;
    }

    public Streetlamp dureeviestreetlamp(Double dureeviestreetlamp) {
        this.dureeviestreetlamp = dureeviestreetlamp;
        return this;
    }

    public void setDureeviestreetlamp(Double dureeviestreetlamp) {
        this.dureeviestreetlamp = dureeviestreetlamp;
    }

    public String getUniteviestreetlamp() {
        return uniteviestreetlamp;
    }

    public Streetlamp uniteviestreetlamp(String uniteviestreetlamp) {
        this.uniteviestreetlamp = uniteviestreetlamp;
        return this;
    }

    public void setUniteviestreetlamp(String uniteviestreetlamp) {
        this.uniteviestreetlamp = uniteviestreetlamp;
    }

    public String getMateriaustreetlamp() {
        return materiaustreetlamp;
    }

    public Streetlamp materiaustreetlamp(String materiaustreetlamp) {
        this.materiaustreetlamp = materiaustreetlamp;
        return this;
    }

    public void setMateriaustreetlamp(String materiaustreetlamp) {
        this.materiaustreetlamp = materiaustreetlamp;
    }

    public String getLiblampe() {
        return liblampe;
    }

    public Streetlamp liblampe(String liblampe) {
        this.liblampe = liblampe;
        return this;
    }

    public void setLiblampe(String liblampe) {
        this.liblampe = liblampe;
    }

    public Double getPwlampe() {
        return pwlampe;
    }

    public Streetlamp pwlampe(Double pwlampe) {
        this.pwlampe = pwlampe;
        return this;
    }

    public void setPwlampe(Double pwlampe) {
        this.pwlampe = pwlampe;
    }

    public String getFormelampe() {
        return formelampe;
    }

    public Streetlamp formelampe(String formelampe) {
        this.formelampe = formelampe;
        return this;
    }

    public void setFormelampe(String formelampe) {
        this.formelampe = formelampe;
    }

    public String getModelelampe() {
        return modelelampe;
    }

    public Streetlamp modelelampe(String modelelampe) {
        this.modelelampe = modelelampe;
        return this;
    }

    public void setModelelampe(String modelelampe) {
        this.modelelampe = modelelampe;
    }

    public Double getDureevielampe() {
        return dureevielampe;
    }

    public Streetlamp dureevielampe(Double dureevielampe) {
        this.dureevielampe = dureevielampe;
        return this;
    }

    public void setDureevielampe(Double dureevielampe) {
        this.dureevielampe = dureevielampe;
    }

    public String getUnitevielampe() {
        return unitevielampe;
    }

    public Streetlamp unitevielampe(String unitevielampe) {
        this.unitevielampe = unitevielampe;
        return this;
    }

    public void setUnitevielampe(String unitevielampe) {
        this.unitevielampe = unitevielampe;
    }

    public Double getVoltlampe() {
        return voltlampe;
    }

    public Streetlamp voltlampe(Double voltlampe) {
        this.voltlampe = voltlampe;
        return this;
    }

    public void setVoltlampe(Double voltlampe) {
        this.voltlampe = voltlampe;
    }

    public Double getTemplampe() {
        return templampe;
    }

    public Streetlamp templampe(Double templampe) {
        this.templampe = templampe;
        return this;
    }

    public void setTemplampe(Double templampe) {
        this.templampe = templampe;
    }

    public String getImagepathstreetlamp() {
        return imagepathstreetlamp;
    }

    public Streetlamp imagepathstreetlamp(String imagepathstreetlamp) {
        this.imagepathstreetlamp = imagepathstreetlamp;
        return this;
    }

    public void setImagepathstreetlamp(String imagepathstreetlamp) {
        this.imagepathstreetlamp = imagepathstreetlamp;
    }

    public Integer getStockstreetlamp() {
        return stockstreetlamp;
    }

    public Streetlamp stockstreetlamp(Integer stockstreetlamp) {
        this.stockstreetlamp = stockstreetlamp;
        return this;
    }

    public void setStockstreetlamp(Integer stockstreetlamp) {
        this.stockstreetlamp = stockstreetlamp;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Streetlamp)) {
            return false;
        }
        return id != null && id.equals(((Streetlamp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Streetlamp{" +
            "id=" + getId() +
            ", libstreetlamp='" + getLibstreetlamp() + "'" +
            ", modelestreetlamp='" + getModelestreetlamp() + "'" +
            ", dureeviestreetlamp=" + getDureeviestreetlamp() +
            ", uniteviestreetlamp='" + getUniteviestreetlamp() + "'" +
            ", materiaustreetlamp='" + getMateriaustreetlamp() + "'" +
            ", liblampe='" + getLiblampe() + "'" +
            ", pwlampe=" + getPwlampe() +
            ", formelampe='" + getFormelampe() + "'" +
            ", modelelampe='" + getModelelampe() + "'" +
            ", dureevielampe=" + getDureevielampe() +
            ", unitevielampe='" + getUnitevielampe() + "'" +
            ", voltlampe=" + getVoltlampe() +
            ", templampe=" + getTemplampe() +
            ", imagepathstreetlamp='" + getImagepathstreetlamp() + "'" +
            ", stockstreetlamp=" + getStockstreetlamp() +
            "}";
    }
}
