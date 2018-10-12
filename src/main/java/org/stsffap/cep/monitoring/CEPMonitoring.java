/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.stsffap.cep.monitoring;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.api.java.io.PojoCsvInputFormat;
import org.apache.flink.api.java.typeutils.PojoTypeInfo;
import org.apache.flink.api.java.typeutils.PojoField;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.core.fs.Path;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.IngestionTimeExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.stsffap.cep.monitoring.sources.ThreatEventWatermarkEmitter;
import org.stsffap.cep.monitoring.sources.EventDeserializationSchema;
import org.stsffap.cep.monitoring.events.Event;
import org.stsffap.cep.monitoring.events.ThreatEvent;
import org.stsffap.cep.monitoring.events.ThreatEventWarning;

//import org.stsffap.cep.monitoring.events.TemperatureAlert;
//import org.stsffap.cep.monitoring.events.TemperatureWarning;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.lang.reflect.Field;
import java.util.Properties;
/**
 * CEP example monitoring program
 *
 * This example program generates a stream of monitoring events which are analyzed using Flink's CEP library.
 * The input event stream consists of temperature and power events from a set of racks. The goal is to detect
 * when a rack is about to overheat. In order to do that, we create a CEP pattern which generates a
 * TemperatureWarning whenever it sees two consecutive temperature events in a given time interval whose temperatures
 * are higher than a given threshold value. A warning itself is not critical but if we see two warning for the same rack
 * whose temperatures are rising, we want to generate an alert. This is achieved by defining another CEP pattern which
 * analyzes the stream of generated temperature warnings.
 */
public class CEPMonitoring {

    private static String[] field_names = {"id", "flag_mask", "agg_id", "defender_id", "real_defender"
                                    , "pri_type", "sec_type", "begin_time", "end_time", "severity"
                                    , "confidence", "app_id", "app_name", "protocol", "policy_id"
                                    , "profile_id", "action_id", "stage_id", "count", "event_name"
                                    , "event_status", "event_interv", "interv_comments", "src_vsysid"
                                    , "src_vsysname", "src_vrid", "src_vrname", "src_interfaceid", "src_interfacename"
                                    , "src_zoneid", "src_zonename", "src_ip", "src_ip_mask_len", "src_port"
                                    , "src_hostindex", "src_hostname", "src_country", "src_region", "src_city"
                                    , "dst_vsysid", "dst_vsysname", "dst_vrid", "dst_vrname", "dst_interfaceid"
                                    , "dst_interfacename", "dst_zoneid", "dst_zonename", "dst_ip", "dst_ip_mask_len"
                                    , "dst_port", "dst_hostindex", "dst_hostname", "dst_country", "dst_region"
                                    , "dst_city", "priv_data", "is_ioc", "need_show", "threat_cat", "category_type"
                                    , "hscc", "correlate_id", "src_serversubnet", "dst_serversubnet"};

    public static List<PojoField> getPojoFields(Class cls) {
        List<PojoField> pojoFields = new ArrayList<PojoField>();
        //Field[] fields = cls.getDeclaredFields();
        int length = field_names.length;
        for(int i = 0 ; i < length; i++) {
            try {
                Field f = cls.getDeclaredField(field_names[i]);
//                System.out.printf("%s,%s\n", field_names[i], f.getType());
                pojoFields.add(new PojoField(f, TypeInformation.of(f.getType())));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pojoFields;

    }


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Use ingestion time => TimeCharacteristic == EventTime + IngestionTimeExtractor
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
	Properties properties = new Properties();
	properties.setProperty("bootstrap.servers", "localhost:9092");
	properties.setProperty("group.id", "test");
        // Input stream of monitoring events
        DataStream<ThreatEvent> inputEventStream = env
		.addSource(new FlinkKafkaConsumer010<ThreatEvent>("test", new EventDeserializationSchema(), properties))
		.assignTimestampsAndWatermarks(new ThreatEventWatermarkEmitter());


        Pattern<ThreatEvent, ?> warningPattern = Pattern.<ThreatEvent>begin("first")
                .subtype(ThreatEvent.class)
                .where(new IterativeCondition<ThreatEvent>() {
                    private static final long serialVersionUID = -6301755149429716724L;

                    @Override
                    public boolean filter(ThreatEvent value, Context<ThreatEvent> ctx) throws Exception {
                         return value.getEvent_name().equals(new String("\"HTTP X-Sinkhole Response\""));
                         //return value.getConfidence() >=50;
                    }
                })
                .followedBy("second")
                .subtype(ThreatEvent.class)
                .where(new IterativeCondition<ThreatEvent>() {
                    private static final long serialVersionUID = 2392863109523984059L;

                    @Override
                    public boolean filter(ThreatEvent value, Context<ThreatEvent> ctx) throws Exception {
                        //return value.getConfidence() >= 50;
			Iterator<ThreatEvent> it = ctx.getEventsForPattern("first").iterator();
			long dst = it.next().getDst_ip();
                        return value.getEvent_name().equals(new String("\"HTTP Header Contain No Browser Information\"")) && value.getSrc_ip() == dst;
                    }
                })
                .within(Time.seconds(300));

        // Create a pattern stream from our warning pattern
        PatternStream<ThreatEvent> rule1Stream = CEP.pattern(
                inputEventStream,
                warningPattern);

        // Generate temperature warnings for each matched warning pattern
        DataStream<ThreatEventWarning> warnings = rule1Stream.select(
            (Map<String, List<ThreatEvent>> pattern) -> {
                ThreatEvent first = pattern.get("first").get(0);
                ThreatEvent second = pattern.get("second").get(0);

                return new ThreatEventWarning(first.getId(),second.getId(), first.getEvent_name(),second.getEvent_name());
            }
        );

        inputEventStream.print();

        warnings.print();
        


        env.execute("CEP kafka job");
    }
}
