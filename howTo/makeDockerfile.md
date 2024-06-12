***Create Dockerfile easily***

FROM alpine:latest
#in order to have a very light OS

RUN dnf update && dnf upgrade -y --no-install-recommends \
    curl \
    wget \
    tar \
    gzip \
    && rm -rf /var/lib/apt/lists/*
    && apt-get install apt-transport-https curl gnupg -yqq \
    && echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list \
    && echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list \
    && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | gpg --no-default-keyring --keyring gnupg-ring:/etc/apt/trusted.gpg.d/scalasbt-release.gpg --import \
    && chmod 644 /etc/apt/trusted.gpg.d/scalasbt-release.gpg \
    && apt-get update \
    && apt-get install sbt




ENV SCALA-VERSION=2.13.8
ENV SBT-VERSION=1.5.5
ENV SPARK-VERSION=3.3.1
