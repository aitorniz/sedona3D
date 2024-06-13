FROM alpine:latest

WORKDIR /opt/workspace/penduick/

# install necessary tools

RUN apk update && apk add --no-cache \
        nano \
        git \
        vim \
        curl \
        wget \
        tar \
        gzip \

# install sbt
RUN sudo rm -f /etc/yum.repos.d/bintray-rpm.repo || true \
    curl -L https://www.scala-sbt.org/sbt-rpm.repo > sbt-rpm.repo \
    sudo mv sbt-rpm.repo /etc/yum.repos.d/ \
    sudo yum install sbt \

# put spark in a more general directory
WORKDIR /opt/workspace/

# install spark
RUN wget "https://dlcdn.apache.org/spark/spark-3.4.3/spark-3.4.3-bin-hadoop3.tgz" && tar -xf spark-3.4.3-bin-hadoop3

# set up some environments
ENV SPARK_HOME="/opt/workspace/"
ENV PATH=$PATH:$SPARK_HOME/bin
ENV PYTHONPATH=$PATH:$SPARK_HOME/Python

#Copy all files into the container
COPY .  /workspace

#Set the defaut command for running scala
CMD ["sbt", "run"]
