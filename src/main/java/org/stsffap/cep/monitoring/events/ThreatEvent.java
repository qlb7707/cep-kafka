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

package org.stsffap.cep.monitoring.events;

public class ThreatEvent {
    //private double temperature;
    private int id;
    private int flag_mask;
    private int agg_id;
    private int defender_id;
    private int real_defender;
    private int pri_type;
    private int sec_type;
    private long begin_time;
    private long end_time;
    private int severity;
    private int confidence;
    private int app_id;
    private String app_name;
    private int protocol;
    private int policy_id;
    private int profile_id;
    private int action_id;
    private int stage_id;
    private int count;
    private String event_name;
    private int event_status;
    private int event_interv;
    private String interv_comments;
    private int src_vsysid;
    private String src_vsysname;
    private int src_vrid;
    private String src_vrname;
    private int src_interfaceid;
    private String src_interfacename;
    private int src_zoneid;
    private String src_zonename;
    private long src_ip;
    private int src_ip_mask_len;
    private int src_port;
    private int src_hostindex;
    private String src_hostname;
    private String src_country;
    private String src_region;
    private String src_city;
    private int dst_vsysid;
    private String dst_vsysname;
    private int dst_vrid;
    private String dst_vrname;
    private int dst_interfaceid;
    private String dst_interfacename;
    private int dst_zoneid;
    private String dst_zonename;
    private long dst_ip;
    private int dst_ip_mask_len;
    private int dst_port;
    private int dst_hostindex;
    private String dst_hostname;
    private String dst_country;
    private String dst_region;
    private String dst_city;
    private String priv_data;
    private int is_ioc;
    private int need_show;
    private int threat_cat;
    private int category_type;
    private int hscc;
    private int correlate_id;
    private String src_serversubnet;
    private String dst_serversubnet;


    public ThreatEvent(int id, int flag_mask, int agg_id, int defender_id, int real_defender
            , int pri_type, int sec_type, long begin_time, long end_time, int severity, int confidence
            , int app_id, String app_name, int protocol, int policy_id, int profile_id, int action_id
            , int stage_id, int count, String event_name, int event_status, int event_interv, String interv_comments
            , int src_vsysid, String src_vsysname, int src_vrid, String src_vrname, int src_interfaceid
            , String src_interfacename, int src_zoneid, String src_zonename, long src_ip, int src_ip_mask_len
            , int src_port, int src_hostindex, String src_hostname, String src_country, String src_region
            , String src_city, int dst_vsysid, String dst_vsysname, int dst_vrid, String dst_vrname
            , int dst_interfaceid, String dst_interfacename, int dst_zoneid, String dst_zonename, long dst_ip
            , int dst_ip_mask_len, int dst_port, int dst_hostindex, String dst_hostname, String dst_country
            , String dst_region, String dst_city, String priv_data, int is_ioc, int need_show, int threat_cat
            , int category_type, int hscc, int correlate_id, String src_serversubnet, String dst_serversubnet) {
        //super(sensorID);

        this.defender_id = defender_id;
        this.pri_type = pri_type;
        this.sec_type = sec_type;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.severity = severity;
        this.confidence = confidence;
        this.event_name = event_name;
        this.src_ip = src_ip;
        this.dst_ip = dst_ip;
        this.is_ioc = is_ioc;
        this.priv_data = priv_data;
        this.need_show = need_show;
        this.threat_cat = threat_cat;
        this.category_type = category_type;
        this.hscc = hscc;
        this.correlate_id = correlate_id;
    }

    public ThreatEvent() {
        //super();
        this.defender_id = 0;
        this.pri_type = -1;
        this.sec_type = -1;
        this.begin_time = 0;
        this.end_time = 0;
        this.severity = -1;
        this.confidence = -1;
        this.event_name = "";
        this.src_ip = 0;
        this.dst_ip = 0;
        this.is_ioc = 0;
        this.priv_data = "";
        this.need_show = 0;
        this.threat_cat = 0;
        this.category_type = -1;
        this.hscc = 0;
        this.correlate_id = 0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getSrc_ip() {
        return src_ip;
    }

    public void setSrc_ip(long src_ip) {
        this.src_ip = src_ip;
    }

    public long getDst_ip() {
        return dst_ip;
    }

    public void setDst_ip(long dst_ip) {
        this.dst_ip = dst_ip;
    }

    public int getDefender_id() {
        return defender_id;
    }

    public void setDefender_id(int defender_id) {
        this.defender_id = defender_id;
    }

    public long getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(long begin_time) {
        this.begin_time = begin_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public int getPri_type() {
        return pri_type;
    }

    public void setPri_type(int pri_type) {
        this.pri_type = pri_type;
    }

    public int getSec_type() {
        return sec_type;
    }

    public void setSec_type(int sec_type) {
        this.sec_type = sec_type;
    }

    public int getIs_ioc() {
        return is_ioc;
    }

    public void setIs_ioc(int is_ioc) {
        this.is_ioc = is_ioc;
    }

    public int getNeed_show() {
        return need_show;
    }

    public void setNeed_show(int need_show) {
        this.need_show = need_show;
    }

    public int getHscc() {
        return hscc;
    }

    public void setHscc(int hscc) {
        this.hscc = hscc;
    }

    public int getThreat_cat() {
        return threat_cat;
    }

    public void setThreat_cat(int threat_cat) {
        this.threat_cat = threat_cat;
    }

    public int getCategory_type() {
        return category_type;
    }

    public void setCategory_type(int category_type) {
        this.category_type = category_type;
    }

    public int getCorrelate_id() {
        return correlate_id;
    }

    public void setCorrelate_id(int correlate_id) {
        this.correlate_id = correlate_id;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getPriv_data() {
        return priv_data;
    }

    public void setPriv_data(String priv_data) {
        this.priv_data = priv_data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreatEvent) {
            ThreatEvent other = (ThreatEvent) obj;

            return other.canEquals(this) && super.equals(other) && defender_id == other.defender_id
                    && src_ip == other.src_ip && dst_ip == other.dst_ip && event_name == other.event_name
                    && pri_type == other.pri_type && sec_type == other.sec_type && is_ioc == other.is_ioc
                    && need_show == other.need_show && severity == other.severity && confidence == other.confidence
                    && threat_cat == other.threat_cat && category_type == other.category_type && hscc == other.hscc
                    && begin_time == other.begin_time && end_time == other.end_time && correlate_id == other.correlate_id
                    && priv_data == other.priv_data;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(defender_id) + Long.hashCode(src_ip) + Long.hashCode(dst_ip)
                + event_name.hashCode() + Integer.hashCode(pri_type) + Integer.hashCode(sec_type) + Integer.hashCode(is_ioc)
                + Integer.hashCode(need_show) + Integer.hashCode(severity) + Integer.hashCode(confidence) + Integer.hashCode(threat_cat)
                + Integer.hashCode(category_type) + Integer.hashCode(hscc) + Long.hashCode(begin_time) + Long.hashCode(end_time)
                + Integer.hashCode(correlate_id) + priv_data.hashCode();
    }


    public boolean canEquals(Object obj){
        return obj instanceof ThreatEvent;
    }

    @Override
    public String toString() {
        return "ThreatEvent{ "
                + "defender_id: " + getDefender_id() + ","
                + "src_ip: " + getSrc_ip() + ","
                + "dst_ip: " + getDst_ip() + ","
                + "event_name: " + getEvent_name() + ","
                + "pri_type: " + getPri_type() + ","
                + "sec_type: " + getSec_type() + ","
                + "is_ioc: " + getIs_ioc() + ","
                + "need_show: " + getNeed_show() + ","
                + "severity: " + getSeverity() + ","
                + "confidence: " + getConfidence() + ","
                + "threat_cat: " + getThreat_cat() + ","
                + "category_type: " + getCategory_type() + ","
                + "hscc: " + getHscc() + ","
                + "begin_time: " + getBegin_time() + ","
                + "end_time: " + getEnd_time() + ","
                + "correlate_id: " + getCorrelate_id() + ","
                + "priv_data: " + getPriv_data() + ","
                + " }";
    }
}
