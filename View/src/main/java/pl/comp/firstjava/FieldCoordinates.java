package pl.comp.firstjava;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FieldCoordinates {

    private int axisX;
    private int axisY;

    public FieldCoordinates(int axisX, int axisY) {
        this.axisX = axisX;
        this.axisY = axisY;
    }

    public int getAxisX() {
        return axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("axisX", axisX).append("axisY", axisY).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FieldCoordinates that = (FieldCoordinates) o;

        return new EqualsBuilder().append(axisX, that.axisX).append(axisY, that.axisY).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17,37).append(axisX).append(axisY).toHashCode();
    }
}
