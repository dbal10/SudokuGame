package pl.comp.firstjava;

import java.util.ListResourceBundle;

public class Authors_pl extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1.", "Anna Tomaszewska ",},
                {"2.", "Dawid Balcerak "}
        };
    }
}
