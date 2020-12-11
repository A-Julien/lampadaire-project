package com.lampaderum.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.Rowcart} entity.
 */
public class RowcartDTO implements Serializable {
    
    private Long id;

    private Integer quantity;


    private Long streetlampId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getStreetlampId() {
        return streetlampId;
    }

    public void setStreetlampId(Long streetlampId) {
        this.streetlampId = streetlampId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RowcartDTO)) {
            return false;
        }

        return id != null && id.equals(((RowcartDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RowcartDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", streetlampId=" + getStreetlampId() +
            "}";
    }
}
