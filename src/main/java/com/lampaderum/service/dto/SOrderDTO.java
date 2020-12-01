package com.lampaderum.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.SOrder} entity.
 */
public class SOrderDTO implements Serializable {
    
    private Long id;

    private LocalDate datecommande;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(LocalDate datecommande) {
        this.datecommande = datecommande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((SOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SOrderDTO{" +
            "id=" + getId() +
            ", datecommande='" + getDatecommande() + "'" +
            "}";
    }
}
