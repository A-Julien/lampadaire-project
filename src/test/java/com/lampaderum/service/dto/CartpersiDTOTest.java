package com.lampaderum.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class CartpersiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CartpersiDTO.class);
        CartpersiDTO cartpersiDTO1 = new CartpersiDTO();
        cartpersiDTO1.setId(1L);
        CartpersiDTO cartpersiDTO2 = new CartpersiDTO();
        assertThat(cartpersiDTO1).isNotEqualTo(cartpersiDTO2);
        cartpersiDTO2.setId(cartpersiDTO1.getId());
        assertThat(cartpersiDTO1).isEqualTo(cartpersiDTO2);
        cartpersiDTO2.setId(2L);
        assertThat(cartpersiDTO1).isNotEqualTo(cartpersiDTO2);
        cartpersiDTO1.setId(null);
        assertThat(cartpersiDTO1).isNotEqualTo(cartpersiDTO2);
    }
}
