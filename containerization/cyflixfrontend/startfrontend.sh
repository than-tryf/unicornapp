#!/usr/bin/env bash

nohup java -jar /home/cyflix-frontend-1.0-SNAPSHOT.jar 2000 1>/dev/null 2>/dev/null &
/usr/local/bin/envoy -c /etc/envoy.json