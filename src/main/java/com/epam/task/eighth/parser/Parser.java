package com.epam.task.eighth.parser;

import com.epam.task.eighth.model.AbstractTouristVoucher;

import java.util.List;

public interface Parser {
    List<AbstractTouristVoucher> parse(String file) throws ParserException;
}
