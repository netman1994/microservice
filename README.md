

### docker 部署 ELK
```dockerfile
version: '3.8'

services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:7.9.3
    container_name: elasticsearch
    environment:
 #     - node.name=es01
 #     - cluster.name=es-docker-cluster
      - discovery.type=single-node
 #     - bootstrap.memory_lock=true
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ulimits:
      memlock:
        soft: -1
        hard: -1
    volumes:
      - esdata:/usr/share/elasticsearch/data
    ports:
      - 9200:9200
    networks:
      - elastic

  logstash:
    image: docker.elastic.co/logstash/logstash:7.17.5
    container_name: logstash
    volumes:
      - D:/docker/mount/elk/logstash/conf/logstash.conf:/usr/share/logstash/pipeline/logstash.conf
#      - ./logstash/pipeline:/usr/share/logstash/pipeline
    ports:
      - 5044:5044
      - 9600:9600
    environment:
      LS_JAVA_OPTS: "-Xmx256m -Xms256m"
    networks:
      - elastic
    depends_on:
      - elasticsearch

  kibana:
    image: docker.elastic.co/kibana/kibana:7.9.3
    container_name: kibana
    ports:
      - 5601:5601
    networks:
      - elastic
    depends_on:
      - elasticsearch

volumes:
  esdata:
    driver: local

networks:
  elastic:
    driver: bridge
```

#### Logstash.conf 内容
```shell
input{
	tcp{
		port => 5044
		# 输出为 json 数据
		codec => json_lines
	}
}

filter {
	ruby{
		# code => "event.set('timestamp', event.get('@timestamp').time.localtime + 8*60*60)"
		code => "event.set('timestamp', event.get('@timestamp').time.localtime)"
	}
	ruby{
		code => "event.set('@timestamp',event.get('timestamp'))"
	}
	mutate{
		remove_field => ["timestamp"]
	}
}

output{
	# 这个是 logstash 的控制台打印（进行安装调试的时候开始，成功后去掉这个配置即可）
	stdout{
		codec => rubydebug
	}
	# elasticsearch配置
	elasticsearch {
	  # 配置 elasticsearch IP 和端口
		hosts => ["xxx.xxx.xxx.xxx:xxxx"]
		# 索引名称，没有会自动创建
		index => "logstash-%{[server_name]}-%{+YYYY-MM-dd}"
	}
}
```
### logback-spring.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%yellow(%d{yyyy-MM-dd HH:mm:ss.SSS}) %green([%thread]) %-5level %highlight(%logger) Line:%-3L - %msg%n</pattern>
            <charset>utf-8</charset>
        </encoder>
        <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>info</level>
        </filter>
    </appender>

    <appender name="STASH" class="net.logstash.logback.appender.LogstashAccessTcpSocketAppender">
        <!--Logstash的 IP 和端口-->
        <destination>xxx.xxx.xxx.xxx:xxxx</destination>
        <encoder class="net.logstash.logback.encoder.LogstashEncoder">
            <!--官方不建议在生产环境中开启默认是false-->
            <includeCallerData>true</includeCallerData>
            <timeZone>UTC</timeZone>
            <customFields>{"server_name":"demo-server"}</customFields>
        </encoder>
    </appender>

    <root level="info">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="STASH"/>
    </root>
</configuration>
```