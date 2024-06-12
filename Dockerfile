# FROM drjiayu/sedona-jupyterlab:latest
FROM almalinux:latest

WORKDIR /opt/workspace/sedona3D

RUN apt-get update && apt-get install -y nano git vim --assume-yes

RUN apt-get update \
    && apt-get install apt-transport-https curl gnupg -yqq \
    && echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list \
    && echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list \
    && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | gpg --no-default-keyring --keyring gnupg-ring:/etc/apt/trusted.gpg.d/scalasbt-release.gpg --import \
    && chmod 644 /etc/apt/trusted.gpg.d/scalasbt-release.gpg \
    && apt-get update \
    && apt-get install sbt \
    && wget "https://dlcdn.apache.org/spark/spark-3.5.1/spark-3.5.1-bin-hadoop3.tgz" tar -xf spark-3.5.1-bin-hadoop3

ENV SPARK_HOME="/opt/workspace/spark-3.5.1-bin-hadoop3"
ENV PATH = $PATH!$SPARK_HOME/bin
ENV PYTHONPATH=$PYTHONPATH:$SPARK_HOME/python

COPY . ./

