package com.epam.task.eighth.parser.SAX;

import com.epam.task.eighth.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.List;

public class VoucherHandler extends DefaultHandler {
    private final static String EXCURSION_VOUCHER = "excursion-voucher";
    private final static String PILGRIMAGE_VOUCHER = "pilgrimage-voucher";
    private final static String VOCATION_VOUCHER = "vocation-voucher";

    private final static String ID = "id";
    private final static String TRANSPORT = "transport";
    private final static String TRANSPORT_TRAIN = "train";
    private final static String TRANSPORT_BUS = "bus";
    private final static String TRANSPORT_SEA_LINER = "sea-liner";
    private final static String TRANSPORT_AIRPLANE = "airplane";

    private final static String COST = "cost";
    private final static String DAY = "day";
    private final static String COUNTRY = "country";

    private final static String HOTEL_STAR = "hotel-star";
    private final static String HOTEL_STAR_ONE = "one";
    private final static String HOTEL_STAR_TWO = "two";
    private final static String HOTEL_STAR_THREE = "three";
    private final static String HOTEL_STAR_FOUR = "four";
    private final static String HOTEL_STAR_FIVE = "five";

    private final static String SEA_DISTANCE = "sea-distance";
    private final static String GUIDE = "guide";
    private final static String MAIN_ATTRACTION = "main-attraction";
    private final static String COST_BONUS_SERVICE = "cost-bonus-service";
    private final static String NUMBERS_STOP = "numbers-stop";
    public static final List<String> FIELD_LIST = Arrays.asList(
            ID,
            TRANSPORT,
            COST,
            DAY,
            COUNTRY,
            HOTEL_STAR,
            SEA_DISTANCE,
            GUIDE,
            MAIN_ATTRACTION,
            COST_BONUS_SERVICE,
            NUMBERS_STOP);

    private List<AbstractTouristVoucher> vouchers;
    private AbstractTouristVoucher currentVoucher = null;
    private String currentVoucherField = null;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (isVoucher(localName)) {
            currentVoucher = createVoucher(localName);
            currentVoucher.setId(attributes.getValue(ID));
        } else {
            if (FIELD_LIST.contains(localName)) {
                currentVoucherField = localName;
            }
        }
    }

    private boolean isVoucher(String localName) {
        return EXCURSION_VOUCHER.equals(localName) |
                PILGRIMAGE_VOUCHER.equals(localName) |
                VOCATION_VOUCHER.equals(localName);
    }

    private AbstractTouristVoucher createVoucher(String tagName) {
        switch (tagName) {
            case EXCURSION_VOUCHER:
                return new ExcursionVoucher();
            case PILGRIMAGE_VOUCHER:
                return new PilgrimageVoucher();
            case VOCATION_VOUCHER:
                return new VocationVoucher();
            default:
                throw new IllegalArgumentException("Unknown tag");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (isVoucher(localName)) {
            vouchers.add(currentVoucher);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).intern().trim();
        VocationVoucher vocationVoucher;
        PilgrimageVoucher pilgrimageVoucher;
        ExcursionVoucher excursionVoucher;
        if (currentVoucherField != null) {
            switch (currentVoucherField) {
              case TRANSPORT:
                    TransportType transportType = getTransportType(value);
                    currentVoucher.setTransport(transportType);
                    break;
                case COST:
                    currentVoucher.setCost(Double.parseDouble(value));
                    break;
                case DAY:
                    currentVoucher.setDay(Integer.parseInt(value));
                    break;
                case COUNTRY:
                    currentVoucher.setCountry(value);
                    break;
                case HOTEL_STAR:
                    vocationVoucher = (VocationVoucher) currentVoucher;
                    HotelStar hotelStar = HotelStar.valueOf(value);
                    vocationVoucher.setStar(hotelStar);
                    currentVoucher = vocationVoucher;
                    break;
                case SEA_DISTANCE:
                    vocationVoucher = (VocationVoucher) currentVoucher;
                    vocationVoucher.setSeaDistance(Double.parseDouble(currentVoucherField));
                    currentVoucher = vocationVoucher;
                    break;
                case GUIDE:
                    pilgrimageVoucher = (PilgrimageVoucher) currentVoucher;
                    pilgrimageVoucher.setGuide(Boolean.parseBoolean(currentVoucherField));
                    currentVoucher = pilgrimageVoucher;
                    break;
                case MAIN_ATTRACTION:
                    pilgrimageVoucher = (PilgrimageVoucher) currentVoucher;
                    pilgrimageVoucher.setMainAttraction(currentVoucherField);
                    break;
                case COST_BONUS_SERVICE:
                    excursionVoucher = (ExcursionVoucher) currentVoucher;
                    excursionVoucher.setCostBonusService(Double.parseDouble(currentVoucherField));
                    break;
                case NUMBERS_STOP:
                    excursionVoucher = (ExcursionVoucher) currentVoucher;
                    excursionVoucher.setNumbersStop(Integer.parseInt(currentVoucherField));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown tag");
            }
        }

    }

    private TransportType getTransportType(String type) {
        switch (type) {
            case TRANSPORT_TRAIN:
                return TransportType.TRAIN;
            case TRANSPORT_BUS:
                return TransportType.BUS;
            case TRANSPORT_SEA_LINER:
                return TransportType.SEA_LINER;
            case TRANSPORT_AIRPLANE:
                return TransportType.AIRPLANE;
            default:
                throw new IllegalArgumentException("Unknown tag");
        }
    }

    private HotelStar getHotelStar(String star) {
        switch (star) {
            case HOTEL_STAR_ONE:
                return HotelStar.ONE;
            case HOTEL_STAR_TWO:
                return HotelStar.TWO;
            case HOTEL_STAR_THREE:
                return HotelStar.THREE;
            case HOTEL_STAR_FOUR:
                return HotelStar.FOUR;
            case HOTEL_STAR_FIVE:
                return HotelStar.FIVE;
            default:
                throw new IllegalArgumentException("Unknown tag");
        }
    }

    public List<AbstractTouristVoucher> getVouchers() {
        return vouchers;
    }

}
