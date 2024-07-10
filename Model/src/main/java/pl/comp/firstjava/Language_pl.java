package pl.comp.firstjava;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class Language_pl extends ListResourceBundle implements Serializable {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"_wrongFieldValue", "Musi być <1,9>"},
                {"_wrongLength", "Dlugosc musi byc rowna 9"}
        };
    }
}
