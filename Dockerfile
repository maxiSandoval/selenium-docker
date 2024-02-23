FROM bellsoft/liberica-openjdk-alpine:17-cds

# Install curl jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# add the required files
ADD target/docker-resources ./
ADD runner.sh               runner.sh
RUN dos2unix                runner.sh

# Enviroment Variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# run the tests
ENTRYPOINT sh runner.sh