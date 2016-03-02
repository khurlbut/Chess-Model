package onceagain.enums;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ColorTest {


    @Test
    public void color_name_format_is_as_follows() {
        Color white = Color.WHITE;
        assertThat(white.toString(), equalTo("w"));
    }

}
