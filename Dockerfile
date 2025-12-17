FROM alpine:latest
RUN apk update && apk add openjdk11
CMD ["java","-version"]
