package com.lampaderum.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.ApplicationUser} entity.
 */
public class ApplicationUserDTO implements Serializable {
    
    private Long id;

    private String siret;


    private Long userId;

    private String userLogin;

    private Long cartpersiId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getCartpersiId() {
        return cartpersiId;
    }

    public void setCartpersiId(Long cartpersiId) {
        this.cartpersiId = cartpersiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUserDTO)) {
            return false;
        }

        return id != null && id.equals(((ApplicationUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationUserDTO{" +
            "id=" + getId() +
            ", siret='" + getSiret() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", cartpersiId=" + getCartpersiId() +
            "}";
    }
}
