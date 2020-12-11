package com.lampaderum.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class RowcartDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RowcartDTO.class);
        RowcartDTO rowcartDTO1 = new RowcartDTO();
        rowcartDTO1.setId(1L);
        RowcartDTO rowcartDTO2 = new RowcartDTO();
        assertThat(rowcartDTO1).isNotEqualTo(rowcartDTO2);
        rowcartDTO2.setId(rowcartDTO1.getId());
        assertThat(rowcartDTO1).isEqualTo(rowcartDTO2);
        rowcartDTO2.setId(2L);
        assertThat(rowcartDTO1).isNotEqualTo(rowcartDTO2);
        rowcartDTO1.setId(null);
        assertThat(rowcartDTO1).isNotEqualTo(rowcartDTO2);
    }
}
