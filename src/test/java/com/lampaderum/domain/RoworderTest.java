package com.lampaderum.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lampaderum.web.rest.TestUtil;

public class RoworderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Roworder.class);
        Roworder roworder1 = new Roworder();
        roworder1.setId(1L);
        Roworder roworder2 = new Roworder();
        roworder2.setId(roworder1.getId());
        assertThat(roworder1).isEqualTo(roworder2);
        roworder2.setId(2L);
        assertThat(roworder1).isNotEqualTo(roworder2);
        roworder1.setId(null);
        assertThat(roworder1).isNotEqualTo(roworder2);
    }
}
