package com.lampaderum.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class SOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SOrderDTO.class);
        SOrderDTO sOrderDTO1 = new SOrderDTO();
        sOrderDTO1.setId(1L);
        SOrderDTO sOrderDTO2 = new SOrderDTO();
        assertThat(sOrderDTO1).isNotEqualTo(sOrderDTO2);
        sOrderDTO2.setId(sOrderDTO1.getId());
        assertThat(sOrderDTO1).isEqualTo(sOrderDTO2);
        sOrderDTO2.setId(2L);
        assertThat(sOrderDTO1).isNotEqualTo(sOrderDTO2);
        sOrderDTO1.setId(null);
        assertThat(sOrderDTO1).isNotEqualTo(sOrderDTO2);
    }
}
