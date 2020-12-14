package com.lampaderum.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class StreetlampTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Streetlamp.class);
        Streetlamp streetlamp1 = new Streetlamp();
        streetlamp1.setId(1L);
        Streetlamp streetlamp2 = new Streetlamp();
        streetlamp2.setId(streetlamp1.getId());
        assertThat(streetlamp1).isEqualTo(streetlamp2);
        streetlamp2.setId(2L);
        assertThat(streetlamp1).isNotEqualTo(streetlamp2);
        streetlamp1.setId(null);
        assertThat(streetlamp1).isNotEqualTo(streetlamp2);
    }
}
