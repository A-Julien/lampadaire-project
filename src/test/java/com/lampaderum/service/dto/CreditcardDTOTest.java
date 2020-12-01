package com.lampaderum.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class CreditcardDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditcardDTO.class);
        CreditcardDTO creditcardDTO1 = new CreditcardDTO();
        creditcardDTO1.setId(1L);
        CreditcardDTO creditcardDTO2 = new CreditcardDTO();
        assertThat(creditcardDTO1).isNotEqualTo(creditcardDTO2);
        creditcardDTO2.setId(creditcardDTO1.getId());
        assertThat(creditcardDTO1).isEqualTo(creditcardDTO2);
        creditcardDTO2.setId(2L);
        assertThat(creditcardDTO1).isNotEqualTo(creditcardDTO2);
        creditcardDTO1.setId(null);
        assertThat(creditcardDTO1).isNotEqualTo(creditcardDTO2);
    }
}
