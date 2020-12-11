package com.lampaderum.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class RowcartTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rowcart.class);
        Rowcart rowcart1 = new Rowcart();
        rowcart1.setId(1L);
        Rowcart rowcart2 = new Rowcart();
        rowcart2.setId(rowcart1.getId());
        assertThat(rowcart1).isEqualTo(rowcart2);
        rowcart2.setId(2L);
        assertThat(rowcart1).isNotEqualTo(rowcart2);
        rowcart1.setId(null);
        assertThat(rowcart1).isNotEqualTo(rowcart2);
    }
}
