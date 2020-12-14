package com.lampaderum.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class StreetlampDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StreetlampDTO.class);
        StreetlampDTO streetlampDTO1 = new StreetlampDTO();
        streetlampDTO1.setId(1L);
        StreetlampDTO streetlampDTO2 = new StreetlampDTO();
        assertThat(streetlampDTO1).isNotEqualTo(streetlampDTO2);
        streetlampDTO2.setId(streetlampDTO1.getId());
        assertThat(streetlampDTO1).isEqualTo(streetlampDTO2);
        streetlampDTO2.setId(2L);
        assertThat(streetlampDTO1).isNotEqualTo(streetlampDTO2);
        streetlampDTO1.setId(null);
        assertThat(streetlampDTO1).isNotEqualTo(streetlampDTO2);
    }
}
