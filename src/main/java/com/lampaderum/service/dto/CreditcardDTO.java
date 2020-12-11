package com.lampaderum.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.lampaderum.domain.Creditcard} entity.
 */
public class CreditcardDTO implements Serializable {
    
    private Long id;

    private String numcarte;

    private LocalDate dateexpiration;

    private String code;


    private Long applicationUserId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(String numcarte) {
        this.numcarte = numcarte;
    }

    public LocalDate getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(LocalDate dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(o instanceof CreditcardDTO)) {
            return false;
        }

        return id != null && id.equals(((CreditcardDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditcardDTO{" +
            "id=" + getId() +
            ", numcarte='" + getNumcarte() + "'" +
            ", dateexpiration='" + getDateexpiration() + "'" +
            ", code='" + getCode() + "'" +
            ", applicationUserId=" + getApplicationUserId() +
            "}";
    }
}
