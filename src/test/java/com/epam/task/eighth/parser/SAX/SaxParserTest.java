package com.epam.task.eighth.parser.SAX;

import com.epam.task.eighth.model.*;
import com.epam.task.eighth.parser.Parser;
import com.epam.task.eighth.parser.ParserException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SaxParserTest {
   // private final static String FILE_VALID = "src/test/resources/vouchers.xml";
    private final static String FILE_VALID = "src/main/resources/vouchers_test.xml";
    private final static String FILE_INVALID = "src/test/resources/vouchers_broken.xml";

    private final static AbstractTouristVoucher FIRST_VOUCHER =
            new VocationVoucher("ID1", TransportType.AIRPLANE, 2000, 10, "Turkey", HotelStar.FOUR, 600);
    private final static AbstractTouristVoucher SECOND_VOUCHER =
            new VocationVoucher("ID2", TransportType.SEA_LINER, 1200, 8, "Morocco", HotelStar.THREE, 400);
    private final static AbstractTouristVoucher THIRD_VOUCHER =
            new ExcursionVoucher("ID3", TransportType.BUS, 800, 4, "Belarus", 50, 6);
    private final static AbstractTouristVoucher FOUR_VOUCHER =
            new ExcursionVoucher("ID4", TransportType.BUS, 600, 6, "Russia", 120, 8);
    private final static AbstractTouristVoucher FIVE_VOUCHER =
            new PilgrimageVoucher("ID5", TransportType.TRAIN, 200,4,"Ukraine",false,"Zhytomyr monastery");
    private final static AbstractTouristVoucher SIX_VOUCHER =
            new PilgrimageVoucher("ID6", TransportType.BUS, 300,5,"Russia",true,"Saint Isaac's Cathedral");

    private final static List<AbstractTouristVoucher> EXPECTED_VOUCHERS = Arrays.asList(
            FIRST_VOUCHER, SECOND_VOUCHER, THIRD_VOUCHER, FOUR_VOUCHER, FIVE_VOUCHER, SIX_VOUCHER
    );

    @Test
    public void testParseShouldReturnListWhenDataIsCorrect() throws ParserException {
        //when
        Parser parser = new SaxParser();

        //given
        List<AbstractTouristVoucher> actual = parser.parse( FILE_VALID);

        //then
        Assert.assertEquals(EXPECTED_VOUCHERS ,actual);

    }

    @Test(expected = ParserException.class)
    public void testParseShouldThrowExceptionWhenFileNotExists() throws ParserException {
        Parser parser = new SaxParser();

        parser.parse(FILE_INVALID);
    }

}