[![Build Status](https://travis-ci.org/n0rad/jmx-zabbix.png)](https://travis-ci.org/n0rad/jmx-zabbix)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/fr.norad.jmxzabbix/jmx-zabbix-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/fr.norad.jmxzabbix/jmx-zabbix-core)

# DESCRIPTION:

Service that load jmx metrics periodically and push to zabbix in one shot

# REQUIREMENTS:

* a jmx server to connect to
* a zabbix server to connect to
* java >= 7

# ATTRIBUTES:

see configuration file example cli/src/deb/config/jmx-zabbix.yaml

# Build

You need java and maven

``
$ mvn clean verify
``

# USAGE:

in cli :

``
$ java -jar jmx-zabbix.jar config.yaml
``

Or you can include the core library directly in your application
