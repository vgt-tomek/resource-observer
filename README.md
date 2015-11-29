resource-observer
===

Summary
---
JEE web application for monitoring resource changes

Installation
---

Requirements:
- Wildfly
- MySQL
- java mysql connector


1. Add mysql connector to Wildfy modules
2. create database "resource_observer"
3. Use schema.sql file to create tables.
4. Add required sections to Wildfly standalone.xml file (see below).
5. Start Wildfly
6. Application should be available: http://localhost:8080/resource-observer/app

Standalone.xml changes:
---

**System property for "FROM" address in e-mail notifications.**
```
<system-properties>
   <property name="pl.vgtworld.resourceobserver.mail.from" value="from@example.com"/>
</system-properties>
```

**Datasource and driver for database.**
```
<datasource jndi-name="java:/resource-observer" pool-name="resource-observer-pool" enabled="true">
 <connection-url>jdbc:mysql://localhost:3306/resource_observer</connection-url>
 <driver>com.mysql</driver>
 <pool>
   <max-pool-size>30</max-pool-size>
 </pool>
 <security>
   <user-name>username</user-name>
   <password>secret</password>
 </security>
</datasource>
<driver name="com.mysql" module="com.mysql">
 <driver-class>com.mysql.jdbc.Driver</driver-class>
</driver>
```

**Mail session for sending e-mail notifications.**
```
<mail-session name="resource-observer-mail" jndi-name="java:jboss/mail/resource-observer-mail">
 <smtp-server outbound-socket-binding-ref="mail-smtp-resource-observer" ssl="false" username="username" password="secret"/>
</mail-session>
<outbound-socket-binding name="mail-smtp-resource-observer">
 <remote-destination host="smtp.example.com" port="25"/>
</outbound-socket-binding>
```
