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


import static com.google.common.base.Strings.isNullOrEmpty;
import com.google.common.collect.EvictingQueue;
import lombok.Data;
import fr.norad.jmxzabbix.core.ZabbixRequest.ZabbixItem;

@Data
public class JmxToZabbixDaemon implements Runnable {

    private final JmxZabbixConfig config;
    private boolean interruptFlag = false;

    public JmxToZabbixDaemon(JmxZabbixConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
        EvictingQueue<ZabbixRequest> queue = EvictingQueue.create(config.getInMemoryMaxQueueSize());
        while (!interruptFlag) {
            try {
                Thread.sleep(config.getPushIntervalSecond() * 1000);
                if (isNullOrEmpty(config.getZabbix().getHost())) {
                    continue;
                }
                ZabbixRequest metrics = new JmxMetrics(config.getJmx(), config.getServerName()).getMetrics();
                if (metrics != null) {
                    queue.add(metrics);
                }
                new ZabbixClient(config.getZabbix()).send(queue);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
            ZabbixRequest zbxVersion = new ZabbixRequest();
            zbxVersion.getData().add(new ZabbixItem<>("jmx_zabbix.version", "0.0.9", config.getServerName()));
            queue.add(zbxVersion);
        }

    }
}
