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

public class ThreatEventWarning {

    private int threat_id1;
    private int threat_id2;
    private String name1;
    private String name2;

    //private double averageTemperature;

    public ThreatEventWarning(int threat_id1, int threat_id2, String name1, String name2) {
        this.threat_id1 = threat_id1;
        this.threat_id2 = threat_id2;
        this.name1 = name1;
        this.name2 = name2;
    }

    public ThreatEventWarning() {
        this(0, 0,"", "");
    }


    public int getThreat_id1() {
        return threat_id1;
    }

    public void setThreat_id1(int threat_id1) {
        this.threat_id1 = threat_id1;
    }

    public int getThreat_id2() {
        return threat_id2;
    }

    public void setThreat_id2(int threat_id2) {
        this.threat_id2 = threat_id2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ThreatEventWarning) {
            ThreatEventWarning other = (ThreatEventWarning) obj;

            return threat_id1 == other.threat_id1 && threat_id2 == other.threat_id2
                    && name1 == other.name1 && name2 == other.name2;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(threat_id1) + Integer.hashCode(threat_id2)
                + name1.hashCode() + name2.hashCode();
    }

    @Override
    public String toString() {
        return "ThreatEventWarning(" + getThreat_id1() + "," + getThreat_id2() + ","
                + getName1() + "," + getName2()
                + ")";
    }
}
