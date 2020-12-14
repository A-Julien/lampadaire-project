package com.lampaderum.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class SOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SOrder.class);
        SOrder sOrder1 = new SOrder();
        sOrder1.setId(1L);
        SOrder sOrder2 = new SOrder();
        sOrder2.setId(sOrder1.getId());
        assertThat(sOrder1).isEqualTo(sOrder2);
        sOrder2.setId(2L);
        assertThat(sOrder1).isNotEqualTo(sOrder2);
        sOrder1.setId(null);
        assertThat(sOrder1).isNotEqualTo(sOrder2);
    }
}
