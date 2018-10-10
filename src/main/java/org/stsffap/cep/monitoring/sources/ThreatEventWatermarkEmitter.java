package org.stsffap.cep.monitoring.sources;

import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.stsffap.cep.monitoring.events.ThreatEvent;

/**
 * Custom Watermark Emitter. 
 * @author TDeshpande
 *
 */
public class ThreatEventWatermarkEmitter implements AssignerWithPunctuatedWatermarks<ThreatEvent> {

	
	private static final long serialVersionUID = 1L;

	@Override
	public long extractTimestamp(ThreatEvent arg0, long arg1) {
		if (null != arg0) {
			return 1000 * arg0.getEnd_time();
		}

		return 0;
	}

	@Override
	public Watermark checkAndGetNextWatermark(ThreatEvent arg0, long arg1) {
		if (null != arg0) {
			return new Watermark(1000 * arg0.getEnd_time() - 60000);
		}
		return null;
	}

}
