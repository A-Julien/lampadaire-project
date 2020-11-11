package com.lampaderum.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.Streetlamp} entity.
 */
public class StreetlampDTO implements Serializable {
    
    private Long id;

    private String libstreetlamp;

    private String modelestreetlamp;

    private Double dureeviestreetlamp;

    private String uniteviestreetlamp;

    private String materiaustreetlamp;

    private String liblampe;

    private Double pwlampe;

    private String formelampe;

    private String modelelampe;

    private Double dureevielampe;

    private String unitevielampe;

    private Double voltlampe;

    private Double templampe;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibstreetlamp() {
        return libstreetlamp;
    }

    public void setLibstreetlamp(String libstreetlamp) {
        this.libstreetlamp = libstreetlamp;
    }

    public String getModelestreetlamp() {
        return modelestreetlamp;
    }

    public void setModelestreetlamp(String modelestreetlamp) {
        this.modelestreetlamp = modelestreetlamp;
    }

    public Double getDureeviestreetlamp() {
        return dureeviestreetlamp;
    }

    public void setDureeviestreetlamp(Double dureeviestreetlamp) {
        this.dureeviestreetlamp = dureeviestreetlamp;
    }

    public String getUniteviestreetlamp() {
        return uniteviestreetlamp;
    }

    public void setUniteviestreetlamp(String uniteviestreetlamp) {
        this.uniteviestreetlamp = uniteviestreetlamp;
    }

    public String getMateriaustreetlamp() {
        return materiaustreetlamp;
    }

    public void setMateriaustreetlamp(String materiaustreetlamp) {
        this.materiaustreetlamp = materiaustreetlamp;
    }

    public String getLiblampe() {
        return liblampe;
    }

    public void setLiblampe(String liblampe) {
        this.liblampe = liblampe;
    }

    public Double getPwlampe() {
        return pwlampe;
    }

    public void setPwlampe(Double pwlampe) {
        this.pwlampe = pwlampe;
    }

    public String getFormelampe() {
        return formelampe;
    }

    public void setFormelampe(String formelampe) {
        this.formelampe = formelampe;
    }

    public String getModelelampe() {
        return modelelampe;
    }

    public void setModelelampe(String modelelampe) {
        this.modelelampe = modelelampe;
    }

    public Double getDureevielampe() {
        return dureevielampe;
    }

    public void setDureevielampe(Double dureevielampe) {
        this.dureevielampe = dureevielampe;
    }

    public String getUnitevielampe() {
        return unitevielampe;
    }

    public void setUnitevielampe(String unitevielampe) {
        this.unitevielampe = unitevielampe;
    }

    public Double getVoltlampe() {
        return voltlampe;
    }

    public void setVoltlampe(Double voltlampe) {
        this.voltlampe = voltlampe;
    }

    public Double getTemplampe() {
        return templampe;
    }

    public void setTemplampe(Double templampe) {
        this.templampe = templampe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StreetlampDTO)) {
            return false;
        }

        return id != null && id.equals(((StreetlampDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StreetlampDTO{" +
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
            "}";
    }
}
