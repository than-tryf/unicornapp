#!/bin/bash

nohup java -jar /home/dummy-unicorn-app-0.0.1-SNAPSHOT.jar 2000 1>/dev/null 2>/dev/null &
/usr/local/bin/envoy -c /etc/envoy.json