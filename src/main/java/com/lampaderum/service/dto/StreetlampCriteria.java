package com.lampaderum.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.lampaderum.domain.Streetlamp} entity. This class is used
 * in {@link com.lampaderum.web.rest.StreetlampResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /streetlamps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StreetlampCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libstreetlamp;

    private StringFilter modelestreetlamp;

    private DoubleFilter dureeviestreetlamp;

    private StringFilter uniteviestreetlamp;

    private StringFilter materiaustreetlamp;

    private StringFilter liblampe;

    private DoubleFilter pwlampe;

    private StringFilter formelampe;

    private StringFilter modelelampe;

    private DoubleFilter dureevielampe;

    private StringFilter unitevielampe;

    private DoubleFilter voltlampe;

    private DoubleFilter templampe;

    private StringFilter imagepathstreetlamp;

    private IntegerFilter stockstreetlamp;

    private DoubleFilter pricestreetlamp;

    public StreetlampCriteria() {
    }

    public StreetlampCriteria(StreetlampCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.libstreetlamp = other.libstreetlamp == null ? null : other.libstreetlamp.copy();
        this.modelestreetlamp = other.modelestreetlamp == null ? null : other.modelestreetlamp.copy();
        this.dureeviestreetlamp = other.dureeviestreetlamp == null ? null : other.dureeviestreetlamp.copy();
        this.uniteviestreetlamp = other.uniteviestreetlamp == null ? null : other.uniteviestreetlamp.copy();
        this.materiaustreetlamp = other.materiaustreetlamp == null ? null : other.materiaustreetlamp.copy();
        this.liblampe = other.liblampe == null ? null : other.liblampe.copy();
        this.pwlampe = other.pwlampe == null ? null : other.pwlampe.copy();
        this.formelampe = other.formelampe == null ? null : other.formelampe.copy();
        this.modelelampe = other.modelelampe == null ? null : other.modelelampe.copy();
        this.dureevielampe = other.dureevielampe == null ? null : other.dureevielampe.copy();
        this.unitevielampe = other.unitevielampe == null ? null : other.unitevielampe.copy();
        this.voltlampe = other.voltlampe == null ? null : other.voltlampe.copy();
        this.templampe = other.templampe == null ? null : other.templampe.copy();
        this.imagepathstreetlamp = other.imagepathstreetlamp == null ? null : other.imagepathstreetlamp.copy();
        this.stockstreetlamp = other.stockstreetlamp == null ? null : other.stockstreetlamp.copy();
        this.pricestreetlamp = other.pricestreetlamp == null ? null : other.pricestreetlamp.copy();
    }

    @Override
    public StreetlampCriteria copy() {
        return new StreetlampCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibstreetlamp() {
        return libstreetlamp;
    }

    public void setLibstreetlamp(StringFilter libstreetlamp) {
        this.libstreetlamp = libstreetlamp;
    }

    public StringFilter getModelestreetlamp() {
        return modelestreetlamp;
    }

    public void setModelestreetlamp(StringFilter modelestreetlamp) {
        this.modelestreetlamp = modelestreetlamp;
    }

    public DoubleFilter getDureeviestreetlamp() {
        return dureeviestreetlamp;
    }

    public void setDureeviestreetlamp(DoubleFilter dureeviestreetlamp) {
        this.dureeviestreetlamp = dureeviestreetlamp;
    }

    public StringFilter getUniteviestreetlamp() {
        return uniteviestreetlamp;
    }

    public void setUniteviestreetlamp(StringFilter uniteviestreetlamp) {
        this.uniteviestreetlamp = uniteviestreetlamp;
    }

    public StringFilter getMateriaustreetlamp() {
        return materiaustreetlamp;
    }

    public void setMateriaustreetlamp(StringFilter materiaustreetlamp) {
        this.materiaustreetlamp = materiaustreetlamp;
    }

    public StringFilter getLiblampe() {
        return liblampe;
    }

    public void setLiblampe(StringFilter liblampe) {
        this.liblampe = liblampe;
    }

    public DoubleFilter getPwlampe() {
        return pwlampe;
    }

    public void setPwlampe(DoubleFilter pwlampe) {
        this.pwlampe = pwlampe;
    }

    public StringFilter getFormelampe() {
        return formelampe;
    }

    public void setFormelampe(StringFilter formelampe) {
        this.formelampe = formelampe;
    }

    public StringFilter getModelelampe() {
        return modelelampe;
    }

    public void setModelelampe(StringFilter modelelampe) {
        this.modelelampe = modelelampe;
    }

    public DoubleFilter getDureevielampe() {
        return dureevielampe;
    }

    public void setDureevielampe(DoubleFilter dureevielampe) {
        this.dureevielampe = dureevielampe;
    }

    public StringFilter getUnitevielampe() {
        return unitevielampe;
    }

    public void setUnitevielampe(StringFilter unitevielampe) {
        this.unitevielampe = unitevielampe;
    }

    public DoubleFilter getVoltlampe() {
        return voltlampe;
    }

    public void setVoltlampe(DoubleFilter voltlampe) {
        this.voltlampe = voltlampe;
    }

    public DoubleFilter getTemplampe() {
        return templampe;
    }

    public void setTemplampe(DoubleFilter templampe) {
        this.templampe = templampe;
    }

    public StringFilter getImagepathstreetlamp() {
        return imagepathstreetlamp;
    }

    public void setImagepathstreetlamp(StringFilter imagepathstreetlamp) {
        this.imagepathstreetlamp = imagepathstreetlamp;
    }

    public IntegerFilter getStockstreetlamp() {
        return stockstreetlamp;
    }

    public void setStockstreetlamp(IntegerFilter stockstreetlamp) {
        this.stockstreetlamp = stockstreetlamp;
    }

    public DoubleFilter getPricestreetlamp() {
        return pricestreetlamp;
    }

    public void setPricestreetlamp(DoubleFilter pricestreetlamp) {
        this.pricestreetlamp = pricestreetlamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StreetlampCriteria that = (StreetlampCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libstreetlamp, that.libstreetlamp) &&
            Objects.equals(modelestreetlamp, that.modelestreetlamp) &&
            Objects.equals(dureeviestreetlamp, that.dureeviestreetlamp) &&
            Objects.equals(uniteviestreetlamp, that.uniteviestreetlamp) &&
            Objects.equals(materiaustreetlamp, that.materiaustreetlamp) &&
            Objects.equals(liblampe, that.liblampe) &&
            Objects.equals(pwlampe, that.pwlampe) &&
            Objects.equals(formelampe, that.formelampe) &&
            Objects.equals(modelelampe, that.modelelampe) &&
            Objects.equals(dureevielampe, that.dureevielampe) &&
            Objects.equals(unitevielampe, that.unitevielampe) &&
            Objects.equals(voltlampe, that.voltlampe) &&
            Objects.equals(templampe, that.templampe) &&
            Objects.equals(imagepathstreetlamp, that.imagepathstreetlamp) &&
            Objects.equals(stockstreetlamp, that.stockstreetlamp) &&
            Objects.equals(pricestreetlamp, that.pricestreetlamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libstreetlamp,
        modelestreetlamp,
        dureeviestreetlamp,
        uniteviestreetlamp,
        materiaustreetlamp,
        liblampe,
        pwlampe,
        formelampe,
        modelelampe,
        dureevielampe,
        unitevielampe,
        voltlampe,
        templampe,
        imagepathstreetlamp,
        stockstreetlamp,
        pricestreetlamp
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StreetlampCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libstreetlamp != null ? "libstreetlamp=" + libstreetlamp + ", " : "") +
                (modelestreetlamp != null ? "modelestreetlamp=" + modelestreetlamp + ", " : "") +
                (dureeviestreetlamp != null ? "dureeviestreetlamp=" + dureeviestreetlamp + ", " : "") +
                (uniteviestreetlamp != null ? "uniteviestreetlamp=" + uniteviestreetlamp + ", " : "") +
                (materiaustreetlamp != null ? "materiaustreetlamp=" + materiaustreetlamp + ", " : "") +
                (liblampe != null ? "liblampe=" + liblampe + ", " : "") +
                (pwlampe != null ? "pwlampe=" + pwlampe + ", " : "") +
                (formelampe != null ? "formelampe=" + formelampe + ", " : "") +
                (modelelampe != null ? "modelelampe=" + modelelampe + ", " : "") +
                (dureevielampe != null ? "dureevielampe=" + dureevielampe + ", " : "") +
                (unitevielampe != null ? "unitevielampe=" + unitevielampe + ", " : "") +
                (voltlampe != null ? "voltlampe=" + voltlampe + ", " : "") +
                (templampe != null ? "templampe=" + templampe + ", " : "") +
                (imagepathstreetlamp != null ? "imagepathstreetlamp=" + imagepathstreetlamp + ", " : "") +
                (stockstreetlamp != null ? "stockstreetlamp=" + stockstreetlamp + ", " : "") +
                (pricestreetlamp != null ? "pricestreetlamp=" + pricestreetlamp + ", " : "") +
            "}";
    }

}
