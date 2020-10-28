package com.epam.task.eighth.model;


import java.util.StringJoiner;

public abstract class AbstractTouristVoucher {
    private String id;
    private TransportType transport;
    private double cost;
    private int day;
    private String country;

    public AbstractTouristVoucher() {
    }

    public AbstractTouristVoucher(String id, TransportType transport, double cost, int day, String country) {
        this.id = id;
        this.transport = transport;
        this.cost = cost;
        this.day = day;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) {
        this.transport = transport;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTouristVoucher that = (AbstractTouristVoucher) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (day != that.day) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transport != that.transport) return false;
        return country != null ? country.equals(that.country) : that.country == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transport != null ? transport.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + day;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AbstractTouristVoucher.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("transport=" + transport)
                .add("cost=" + cost)
                .add("day=" + day)
                .add("country='" + country + "'")
                .toString();
    }
}
