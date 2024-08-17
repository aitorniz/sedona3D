# How to start working with Sedona
What do you need to install ?
### Easiest way, create a container to work inside
You can use the Dockerfile in the repo
If you haven't docker, install it:
```
sudo apt-get install docker
```
Then you will be able to create a iso image. You could have more details [https://phoenixnap.com/kb/create-docker-images-with-dockerfile](there)
```
docker build -t <name you choose for the image> path/to/Dockerfile

```
After that, you can check if you created an iso image successfully by running the following command:
```
docker images
```
You must found something like this:
```
REPOSITORY    TAG        IMAGE ID       CREATED         SIZE
boussole      v3         91e4191c2521   4 weeks ago     2.34GB
boussole      v1         e4d216782d8a   2 months ago    1.79GB
```
In my case, I do Geomatics so I originally choose `boussole` who
means compass in french. Do as you prefer.

Now, you have to start your docker. You can use the following:
```
docker run -it -v $PWD:/path/to/your/host/machine/working/directory/boussole/ boussole:v1 bash
```
You could find explanations [https://www.howtogeek.com/733522/docker-for-beginners-everything-you-need-to-know/](there) but the idea is to start your
container in you working directory in order to safe all you
created inside your container in your host machine. DOn't hesitate touse [https://excalidraw.com/](Excalidraw) to understand what you're doing.
Therefore, you must see something like this:
```
root@8a61c067bf09:~/projects/boussole#
```
### Clone Sedona
Now, we have working environment. Let's use it to start working with Sedona. But before, we need the Sedona code. To do so, start by forking the official
repo. Go [https://github.com/apache/sedona](there). At the upper right side of the page, you will found a `Fork` button. Click on it
and select "New fork" or something like that.
Congratulations, you have your own fork of the Sedona project.
Now, clone it in your host machine
```
git@github.com:apache/sedona.git
```
### Create your so-called fat jar
Go in `Examples`directory of the Sedona source code.
There, run:
```
mvn clean package
```
This command will take the examples codes and create a `.jar`binary file after compiling it.

### Submit your fat jar
Go in `/target/` directory and run your spark-submit file
which could seems like this:
```

```
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
## Now we have to deploy this image inside a container to start working on Sedona
For that, run the following command:
```
docker run
```
### What's the difference between `docker run` and `docker exec` ?
While `docker run` creates a new container by specifying the image, options and other
parameters, `docker exec` makes you able to execute a command inside and existing
docker container. Secund command is useful to modify things in container without
killing them.
