/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.housepower.jdbc.protocol;

import com.github.housepower.jdbc.ClickHouseSQLException;
import com.github.housepower.jdbc.serializer.BinaryDeserializer;
import com.github.housepower.jdbc.serializer.BinarySerializer;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionResponse extends RequestOrResponse {

    ExceptionResponse() {
        super(ProtocolType.RESPONSE_Exception);
    }

    @Override
    public void writeImpl(BinarySerializer serializer) throws IOException {
        throw new UnsupportedOperationException("ExceptionResponse Cannot write to Server.");
    }

    public static SQLException readExceptionFrom(BinaryDeserializer deserializer) throws IOException {
        int code = deserializer.readInt();
        String name = deserializer.readStringBinary();
        String message = deserializer.readStringBinary();
        String stackTrace = deserializer.readStringBinary();

        if (deserializer.readBoolean()) {
            return new ClickHouseSQLException(
                code, name + message + ". Stack trace:\n\n" + stackTrace, readExceptionFrom(deserializer));
        }

        return new ClickHouseSQLException(code, name + message + ". Stack trace:\n\n" + stackTrace);
    }
}
