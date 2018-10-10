package org.stsffap.cep.monitoring.sources;
import net.sf.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.stsffap.cep.monitoring.events.ThreatEvent;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;

public class EventDeserializationSchema implements DeserializationSchema<ThreatEvent> {

	public TypeInformation<ThreatEvent> getProducedType() {
		return TypeExtractor.getForClass(ThreatEvent.class);
	}

	public ThreatEvent deserialize(byte[] arg0) throws IOException {
		String str = new String(arg0, StandardCharsets.UTF_8);
		JSONObject jsonObject = JSONObject.fromObject(str);
		ThreatEvent e = (ThreatEvent) JSONObject.toBean(jsonObject, ThreatEvent.class);
		return e;
	}

	public boolean isEndOfStream(ThreatEvent arg0) {
		return false;
	}

}
