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

package com.github.housepower.jdbc.data.type.complex;

import com.github.housepower.jdbc.connect.PhysicalInfo;
import com.github.housepower.jdbc.data.IDataType;
import com.github.housepower.jdbc.misc.SQLLexer;
import com.github.housepower.jdbc.misc.Validate;
import com.github.housepower.jdbc.serializer.BinaryDeserializer;
import com.github.housepower.jdbc.serializer.BinarySerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Types;

public class DataTypeFixedString implements IDataType {

    public static IDataType createFixedStringType(SQLLexer lexer,
                                                  PhysicalInfo.ServerInfo serverInfo) throws SQLException {
        Validate.isTrue(lexer.character() == '(');
        Number fixedStringN = lexer.numberLiteral();
        Validate.isTrue(lexer.character() == ')');
        return new DataTypeFixedString("FixedString(" + fixedStringN.intValue() + ")", fixedStringN.intValue());
    }

    private final int n;
    private final String name;
    private final String defaultValue;

    public DataTypeFixedString(String name, int n) {
        this.n = n;
        this.name = name;

        char[] data = new char[n];
        for (int i = 0; i < n; i++) {
            data[i] = '\u0000';
        }
        this.defaultValue = new String(data);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int sqlTypeId() {
        return Types.VARCHAR;
    }

    @Override
    public Object defaultValue() {
        return defaultValue;
    }

    @Override
    public Class javaTypeClass() {
        return String.class;
    }

    @Override
    public boolean nullable() {
        return false;
    }

    @Override
    public int getPrecision() {
        return n;
    }

    @Override
    public int getScale() {
        return 0;
    }

    @Override
    public Object deserializeTextQuoted(SQLLexer lexer) throws SQLException {
        return lexer.stringLiteral();
    }

    @Override
    public void serializeBinary(Object data, BinarySerializer serializer)
            throws SQLException, IOException {
        writeBytes(((String) data).getBytes(StandardCharsets.UTF_8), serializer);
    }

    private void writeBytes(byte[] bs, BinarySerializer serializer)
            throws IOException, SQLException {
        byte[] res;
        if (bs.length > n) {
            throw new SQLException("The size of FixString column is too large, got " + bs.length);
        }
        if (bs.length == n) {
            res = bs;
        } else {
            res = new byte[n];
            System.arraycopy(bs, 0, res, 0, bs.length);
        }
        serializer.writeBytes(res);
    }

    @Override
    public Object deserializeBinary(BinaryDeserializer deserializer) throws SQLException, IOException {
        return new String(deserializer.readBytes(n), StandardCharsets.UTF_8);
    }

    @Override
    public Object[] deserializeBinaryBulk(int rows, BinaryDeserializer deserializer) throws SQLException, IOException {
        String[] data = new String[rows];
        for (int row = 0; row < rows; row++) {
            data[row] = new String(deserializer.readBytes(n), StandardCharsets.UTF_8);
        }
        return data;
    }
}
