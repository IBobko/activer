/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 NBCO Yandex.Money LLC
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

package com.yandex.money.api.typeadapters.methods;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.yandex.money.api.methods.OperationHistory;
import com.yandex.money.api.model.Error;
import com.yandex.money.api.typeadapters.BaseTypeAdapter;
import com.yandex.money.api.typeadapters.model.OperationTypeAdapter;

import java.lang.reflect.Type;

import static com.yandex.money.api.typeadapters.JsonUtils.getString;

/**
 * Type adapter for {@link OperationHistory}.
 *
 * @author Anton Ermak (ermak@yamoney.ru)
 */
public final class OperationHistoryTypeAdapter extends BaseTypeAdapter<OperationHistory> {

    private static final OperationHistoryTypeAdapter INSTANCE = new OperationHistoryTypeAdapter();

    private static final String MEMBER_ERROR = "error";
    private static final String MEMBER_NEXT_RECORD = "next_record";
    private static final String MEMBER_OPERATIONS = "operations";

    private OperationHistoryTypeAdapter() {
    }

    /**
     * @return instance of this class
     */
    public static OperationHistoryTypeAdapter getInstance() {
        return INSTANCE;
    }

    @Override
    public OperationHistory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject object = json.getAsJsonObject();
        return new OperationHistory(Error.parse(getString(object, MEMBER_ERROR)),
                getString(object, MEMBER_NEXT_RECORD),
                toEmptyListIfNull(OperationTypeAdapter.getInstance().fromJson(object.getAsJsonArray(MEMBER_OPERATIONS))));
    }

    @Override
    public JsonElement serialize(OperationHistory src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        if (src.error != null) {
            object.addProperty(MEMBER_ERROR, src.error.code);
        } else {
            object.add(MEMBER_OPERATIONS, OperationTypeAdapter.getInstance().toJsonArray(src.operations));
            object.addProperty(MEMBER_NEXT_RECORD, src.nextRecord);
        }
        return object;
    }

    @Override
    protected Class<OperationHistory> getType() {
        return OperationHistory.class;
    }
}
