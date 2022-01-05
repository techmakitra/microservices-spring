FROM openjdk:17-oracle


RUN groupadd app
RUN useradd -rm -d /home/app -s /bin/bash -g app -G app -u 1001 app

RUN mkdir /app
WORKDIR /app

RUN mkdir ./agents
RUN curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.9.1/opentelemetry-javaagent.jar \
    --output ./agents/opentelemetry-javaagent.jar

ENV PATH="/usr/java/default/bin:/app:${PATH}"

COPY start.sh /app
# Chown all the files to the app user.
RUN chmod a+x *.sh

ENTRYPOINT start.sh

ONBUILD RUN chmod a-w -R /app
ONBUILD USER app

