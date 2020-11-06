FROM node:14.1-alpine AS builder

WORKDIR /app
COPY package*.json /app/

RUN npm install
RUN npm install -g @angular/cli

COPY ./ /app/

RUN npm run webpack:prod

RUN ls && cd target && ls

FROM nginx:1.17-alpine

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/target/classes/static/* /usr/share/nginx/html/
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
