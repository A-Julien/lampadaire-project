FROM lampaderum
LABEL maintainer = "julien.alaimo@gmail.com"
ENV \
    INITSYSTEM on \
        DEBIAN_FRONTEND=noninteractive
        # Basic build-time metadata as defined at http://label-schema.org
        ARG BUILD_DATE
        ARG VCS_REF
	LABEL org.label-schema.build-date=$BUILD_DATE \
    	org.label-schema.docker.dockerfile="/Dockerfile" \
    	org.label-schema.name="LAMPADERUM-DEV-BUILD" \
    SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""

RUN echo '{ "allow_root": true }' > /root/.bowerrc && \
    rm -Rf /code/target /code/node_modules
WORKDIR /app
COPY ./ .
CMD ["./mvnw", "-P-webpack"]
