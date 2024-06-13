# sedona3D
Working on a Sedona upgraded version designed for 3D data

##Set up a VM from zero
Here are the first command to execute after creating your VM on a remote server

```
sudo dnf install git
```
knowing that `dnf` is the installer of almalinux

## How to create a fine grained token
Go on github.com >> 
Your profile >> 
Settings >> 
<>Developer settings >> 
Personal access tokens >> 
Fine-grained tokens >>
Generate new token >>
see [git doc page about fine-grained tokens](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens) for choosing your options;

Now you can clone your git repository:
```
git clone https://userName:nameOfMyFineGrainedToken@github.com/path/of/the/repo.git
```
## (Optional) Run a .sh script to install basic tools for the VM
## At least install vim and docker
```
sudo dnf install vim
sudo dnf install docker
```
## Configure your Dockerfile
First open it
```
vim Dockerfile
```
Now you can configure it. The following commands are the ingredients I choose:
```
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
RUN wget "https://dlcdn.apache.org/spark/spark-3.4.3/spark-3.4.3-bin-hadoop3.tgz" && tar -xvf spark-3.4.3-bin-hadoop3.tgz

WORKDIR /opt/workspace

# set up some environments
ENV SPARK_HOME="/opt/workspace/"
ENV PATH=$PATH:$SPARK_HOME/bin
ENV PYTHONPATH=$PATH:$SPARK_HOME/Python

#Copy all files into the container
COPY .  /workspace

#Set the defaut command for running scala
CMD ["sbt", "run"]
```
And run it:
```
docker build -t penduick:v1  .
```
Got you the reference ?
now you have to input a
ll the ingredients you need for your 3D-friendly sedona container
Now, you have your docker image ready for being deployed if your type:
```
docker images
```
You will get something like this:
```
REPOSITORY                TAG         IMAGE ID      CREATED             SIZE
localhost/penduick        v1          b4bc4bbb6ffd  About a minute ago  878 MB
docker.io/library/alpine  latest      1d34ffeaf190  3 weeks ago         8.08 MB
```
And nothing running `docker ps`:
```
Emulate Docker CLI using podman. Create /etc/containers/nodocker to quiet msg.
CONTAINER ID  IMAGE       COMMAND     CREATED     STATUS      PORTS       NAMES
```

