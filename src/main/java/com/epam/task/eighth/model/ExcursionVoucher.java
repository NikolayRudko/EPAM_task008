package com.epam.task.eighth.model;

import java.util.StringJoiner;

public class ExcursionVoucher extends AbstractTouristVoucher {
    private double costBonusService;
    private int numbersStop;

    public ExcursionVoucher() {
    }

    public ExcursionVoucher(String id, TransportType transport, double cost, int day, String country, double costBonusService,int numbersStop) {
        super(id, transport, cost, day, country);
        this.costBonusService = costBonusService;
        this.numbersStop = numbersStop;
    }

    public double getCostBonusService() {
        return costBonusService;
    }

    public void setCostBonusService(double costBonusService) {
        this.costBonusService = costBonusService;
    }

    public int getNumbersStop() {
        return numbersStop;
    }

    public void setNumbersStop(int numbersStop) {
        this.numbersStop = numbersStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExcursionVoucher that = (ExcursionVoucher) o;

        if (Double.compare(that.costBonusService, costBonusService) != 0) return false;
        return numbersStop == that.numbersStop;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(costBonusService);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numbersStop;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ExcursionVoucher.class.getSimpleName() + "[", "]")
                .add("costBonusService=" + costBonusService)
                .add("numbersStop=" + numbersStop)
                .toString();
    }
}
