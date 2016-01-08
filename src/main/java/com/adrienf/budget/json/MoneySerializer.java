package com.adrienf.budget.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

import java.io.IOException;

public class MoneySerializer extends JsonSerializer<Money> {

    @Override
    public void serialize(Money value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        MoneyFormatter euroMoneyFormatter = new MoneyFormatterBuilder().appendAmount().appendLiteral(" ").appendCurrencySymbolLocalized().toFormatter();
        MoneyFormatter defaultMoneyFormatter = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmount().toFormatter();
        jgen.writeStartObject();
        {
            jgen.writeNumberField("amount", value.getAmountMinorInt());
            if (value.getCurrencyUnit() == CurrencyUnit.EUR) {
                jgen.writeStringField("display", euroMoneyFormatter.print(value));
            } else {
                jgen.writeStringField("display", defaultMoneyFormatter.print(value));
            }
        }
        jgen.writeEndObject();
    }
}
