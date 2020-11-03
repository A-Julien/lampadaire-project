FROM node:10
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

WORKDIR /app

#COPY package.json ./
#RUN npm install -g @angular/cli && npm install

#RUN npm ci --quiet


EXPOSE 4200
EXPOSE 9000
EXPOSE 9060

#CMD ["npm", "start", "--host", "0.0.0.0"]
#CMD ["pm", "start", "--host", "0.0.0.0"]
#CMD ["npm", "start", "--", "--host", "0.0.0.0"]
#, "--", "--host", "0.0.0.0", "--poll", "500"]

#FROM nginx:1.17.1-alpine
#COPY --from=build-step /app /usr/share/nginx/html
#WORKDIR /usr/share/nginx/html


CMD ["sh", "-c", "cd /app ; npm ci ; npm start"]

