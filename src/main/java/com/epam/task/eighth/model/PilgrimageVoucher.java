package com.epam.task.eighth.model;

import java.util.StringJoiner;

public class PilgrimageVoucher extends AbstractTouristVoucher {
    private boolean guide;
    private String mainAttraction;

    public PilgrimageVoucher() {
    }

    public PilgrimageVoucher(String id, TransportType transport, double cost, int day, String country, boolean guide, String mainAttraction) {
        super(id, transport, cost, day, country);
        this.guide = guide;
        this.mainAttraction = mainAttraction;
    }

    public boolean isGuide() {
        return guide;
    }

    public void setGuide(boolean guide) {
        this.guide = guide;
    }

    public String getMainAttraction() {
        return mainAttraction;
    }

    public void setMainAttraction(String mainAttraction) {
        this.mainAttraction = mainAttraction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PilgrimageVoucher that = (PilgrimageVoucher) o;

        if (guide != that.guide) return false;
        return mainAttraction != null ? mainAttraction.equals(that.mainAttraction) : that.mainAttraction == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (guide ? 1 : 0);
        result = 31 * result + (mainAttraction != null ? mainAttraction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PilgrimageVoucher.class.getSimpleName() + "[", "]")
                .add("guide=" + guide)
                .add("mainAttraction='" + mainAttraction + "'")
                .toString();
    }
}
