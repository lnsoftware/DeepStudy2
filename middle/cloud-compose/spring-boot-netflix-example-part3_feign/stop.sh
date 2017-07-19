#!/usr/bin/env bash
kill $(cat notifications-service/notification-micro-service.pid)
kill $(cat eureka-server/eureka-server.pid)
kill $(cat api-service/application.pid)