/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 NBCO Yandex.Money LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.yandex.money.api.typeadapters.model.showcase;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.yandex.money.api.model.showcase.AmountType;
import com.yandex.money.api.model.showcase.Fee;
import com.yandex.money.api.model.showcase.StdFee;
import com.yandex.money.api.typeadapters.BaseTypeAdapter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import static com.yandex.money.api.typeadapters.JsonUtils.getBigDecimal;
import static com.yandex.money.api.typeadapters.JsonUtils.getString;

/**
 * Type adapter for {@link Fee}.
 *
 * @author Slava Yasevich (vyasevich@yamoney.ru)
 */
public final class StdFeeTypeAdapter extends BaseTypeAdapter<StdFee> {

    private static final StdFeeTypeAdapter INSTANCE = new StdFeeTypeAdapter();

    private static final String MEMBER_A = "a";
    private static final String MEMBER_AMOUNT_TYPE = "amount_type";
    private static final String MEMBER_B = "b";
    private static final String MEMBER_C = "c";
    private static final String MEMBER_D = "d";

    private StdFeeTypeAdapter() {
    }

    /**
     * @return instance of this class
     */
    public static StdFeeTypeAdapter getInstance() {
        return INSTANCE;
    }

    @Override
    public StdFee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject object = json.getAsJsonObject();
        FeeTypeAdapter.Delegate.checkFeeType(object, getType());
        return new StdFee(getValueOrZero(object, MEMBER_A), getValueOrZero(object, MEMBER_B),
                getValueOrZero(object, MEMBER_C), getBigDecimal(object, MEMBER_D),
                AmountType.parse(getString(object, MEMBER_AMOUNT_TYPE)));
    }

    @Override
    public JsonElement serialize(StdFee src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        FeeTypeAdapter.Delegate.serialize(object, getType());
        object.addProperty(MEMBER_A, src.a);
        object.addProperty(MEMBER_B, src.b);
        object.addProperty(MEMBER_C, src.c);
        object.addProperty(MEMBER_D, src.d);
        object.addProperty(MEMBER_AMOUNT_TYPE, src.getAmountType().code);
        return object;
    }

    @Override
    protected Class<StdFee> getType() {
        return StdFee.class;
    }

    private static BigDecimal getValueOrZero(JsonObject object, String member) {
        BigDecimal value = getBigDecimal(object, member);
        return value == null ? BigDecimal.ZERO : value;
    }
}
