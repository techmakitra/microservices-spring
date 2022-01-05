#!/bin/sh

echo "Running service: $SERVICE_NAME"
echo "System Time: $(date)"
echo "Working directory: $PWD"

#########################################
#Command accumulator
COMMAND=""
APP_DIR="/app"

#########################################
#Default java defined in PATH
JAVA="java"
eval "$JAVA -version"
COMMAND="$COMMAND $JAVA"

#########################################
# Open Telemetry java agent
if [ "$OTEL_JAVA_AGENT_ENABLED" == "true" ]; then
  OTEL_TRACES_EXPORTER=jaeger
  OTEL_JAVA_AGENT="$APP_DIR/agents/opentelemetry-javaagent.jar"

  echo "Open Telemetry Java Agent: $OTEL_TRACES_EXPORTER $OTEL_EXPORTER_JAEGER_ENDPOINT"
  COMMAND="$COMMAND -javaagent:$OTEL_JAVA_AGENT"
  COMMAND="$COMMAND -Dotel.resource.attributes=service.name=$SERVICE_NAME"
  COMMAND="$COMMAND -Dotel.traces.exporter=$OTEL_TRACES_EXPORTER"
  if [ "$OTEL_JAVA_AGENT_DEBUG" == "true" ]; then
    COMMAND="$COMMAND -Dotel.javaagent.debug=true"
  fi
  COMMAND="$COMMAND -javaagent:$OTEL_JAVA_AGENT"
fi


#########################################
# Service Jar file.
# ! MUST BE LAST STEP
SERVICE_JAR="$SERVICE_NAME.jar"
COMMAND="$COMMAND -jar $SERVICE_JAR"

#########################################
echo "Command: $COMMAND"
eval "$COMMAND"


echo "Terminated service: $SERVICE_NAME"