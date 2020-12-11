package com.lampaderum.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.Roworder} entity.
 */
public class RoworderDTO implements Serializable {
    
    private Long id;

    private Double price;

    @Min(value = 0)
    private Integer quantite;


    private Long streetlampId;

    private Long sorderId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getStreetlampId() {
        return streetlampId;
    }

    public void setStreetlampId(Long streetlampId) {
        this.streetlampId = streetlampId;
    }

    public Long getSorderId() {
        return sorderId;
    }

    public void setSorderId(Long sOrderId) {
        this.sorderId = sOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoworderDTO)) {
            return false;
        }

        return id != null && id.equals(((RoworderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoworderDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", quantite=" + getQuantite() +
            ", streetlampId=" + getStreetlampId() +
            ", sorderId=" + getSorderId() +
            "}";
    }
}
