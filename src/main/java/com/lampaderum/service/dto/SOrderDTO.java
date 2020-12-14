package com.lampaderum.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.SOrder} entity.
 */
public class SOrderDTO implements Serializable {
    
    private Long id;

    private LocalDate datecommande;


    private Long applicationUserId;
    
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

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
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
            ", applicationUserId=" + getApplicationUserId() +
            "}";
    }
}
