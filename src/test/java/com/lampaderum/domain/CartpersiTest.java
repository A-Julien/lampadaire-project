package com.lampaderum.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class CartpersiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cartpersi.class);
        Cartpersi cartpersi1 = new Cartpersi();
        cartpersi1.setId(1L);
        Cartpersi cartpersi2 = new Cartpersi();
        cartpersi2.setId(cartpersi1.getId());
        assertThat(cartpersi1).isEqualTo(cartpersi2);
        cartpersi2.setId(2L);
        assertThat(cartpersi1).isNotEqualTo(cartpersi2);
        cartpersi1.setId(null);
        assertThat(cartpersi1).isNotEqualTo(cartpersi2);
    }
}
