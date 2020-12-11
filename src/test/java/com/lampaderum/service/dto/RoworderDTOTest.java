package com.lampaderum.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class RoworderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RoworderDTO.class);
        RoworderDTO roworderDTO1 = new RoworderDTO();
        roworderDTO1.setId(1L);
        RoworderDTO roworderDTO2 = new RoworderDTO();
        assertThat(roworderDTO1).isNotEqualTo(roworderDTO2);
        roworderDTO2.setId(roworderDTO1.getId());
        assertThat(roworderDTO1).isEqualTo(roworderDTO2);
        roworderDTO2.setId(2L);
        assertThat(roworderDTO1).isNotEqualTo(roworderDTO2);
        roworderDTO1.setId(null);
        assertThat(roworderDTO1).isNotEqualTo(roworderDTO2);
    }
}
