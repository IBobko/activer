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

package com.yandex.money.api.methods;

import com.yandex.money.api.model.Error;
import com.yandex.money.api.model.Operation;
import com.yandex.money.api.net.FirstApiRequest;
import com.yandex.money.api.net.providers.HostsProvider;
import com.yandex.money.api.typeadapters.methods.OperationDetailsTypeAdapter;

/**
 * Operation details result.
 *
 * @author Roman Tsirulnikov (romanvt@yamoney.ru)
 */
public class OperationDetails {

    public final Error error;
    public final Operation operation;

    /**
     * Constructor.
     *
     * @param error error code
     * @param operation operation
     */
    public OperationDetails(Error error, Operation operation) {
        this.error = error;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "OperationDetails{" +
                "error=" + error +
                ", operation=" + operation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationDetails that = (OperationDetails) o;

        return error == that.error &&
                !(operation != null ? !operation.equals(that.operation) : that.operation != null);
    }

    @Override
    public int hashCode() {
        int result = error != null ? error.hashCode() : 0;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        return result;
    }

    /**
     * Requests for specific operation details.
     * <p/>
     * Authorized session required.
     */
    public static class Request extends FirstApiRequest<OperationDetails> {

        /**
         * Constructor.
         *
         * @param operationId operation's id
         */
        public Request(String operationId) {
            super(OperationDetailsTypeAdapter.getInstance());
            addParameter("operation_id", operationId);
        }

        @Override
        protected String requestUrlBase(HostsProvider hostsProvider) {
            return hostsProvider.getMoneyApi() + "/operation-details";
        }
    }
}
