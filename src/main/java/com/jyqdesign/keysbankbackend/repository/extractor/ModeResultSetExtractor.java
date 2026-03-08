package com.jyqdesign.keysbankbackend.repository.extractor;

import com.jyqdesign.keysbankbackend.entity.ModeKey;
import com.jyqdesign.keysbankbackend.entity.OperationMode;
import com.jyqdesign.keysbankbackend.entity.enums.OpMode;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ModeResultSetExtractor implements ResultSetExtractor<List<OperationMode>> {

    @Override
    public List<OperationMode> extractData(ResultSet rs) throws SQLException {

        Map<Long, OperationMode> modes = new LinkedHashMap<>();

        while (rs.next()) {

            long modeId = rs.getLong("idOperationMode");

            OperationMode mode = modes.get(modeId);

            if (mode == null) {
                mode = new OperationMode();
                mode.setIdOperationMode(modeId);
                mode.setIdAccount(rs.getLong("idAccount"));
                mode.setLabel(rs.getString("label"));

                String value = rs.getString("value");
                if (value != null) {
                    mode.setValue(OpMode.valueOf(value));
                }

                mode.setColor(rs.getString("color"));
                mode.setIcon(rs.getString("icon"));
                mode.setKeys(new ArrayList<>());

                modes.put(modeId, mode);
            }

            long keyId = rs.getLong("keyId");

            if (!rs.wasNull()) {
                ModeKey key = new ModeKey();
                key.setIdKey(keyId);
                key.setIdMode(modeId);
                key.setKey(rs.getString("keyLabel"));

                mode.getKeys().add(key);
            }
        }

        return new ArrayList<>(modes.values());
    }
}