package com.lampaderum.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.Cartpersi} entity.
 */
public class CartpersiDTO implements Serializable {
    
    private Long id;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartpersiDTO)) {
            return false;
        }

        return id != null && id.equals(((CartpersiDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartpersiDTO{" +
            "id=" + getId() +
            "}";
    }
}
