package com.anypluspay.payment.types.std;

import com.anypluspay.payment.types.status.PayProcessStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 *
 * @author wxj
 * 2025/3/19
 */
public class PayProcessStatusSerializer extends StdSerializer<PayProcessStatus> {
    protected PayProcessStatusSerializer() {
        super(PayProcessStatus.class);
    }

    @Override
    public void serialize(PayProcessStatus value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getCode());
    }
}
