/**
 *
 *     Copyright (C) norad.fr
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
package fr.norad.jmxzabbix.core;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ZabbixRequest {

    private String request = "sender data";

    private List<ZabbixItem> data = new ArrayList<>();
    private Date clock = new Date();
    private Long ns;

    @Data
    @NoArgsConstructor
    public static class ZabbixItem<T> {
        private String host;
        private String key;
        private T value;
        private Date clock;
        private Long ns;

        public ZabbixItem(String key, T value, String host) {
            this.key = key;
            this.value = value;
            this.host = host;
        }

    }
}
