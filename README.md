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
#!/bin/bash

$SPARK_HOME/bin/spark-submit \
  --master local[2]\
  --driver-memory=2g\
  --executor-memory=2g\
  --packages org.apache.sedona:sedona-spark-shaded-3.0_2.12:1.6.0,org.datasyslab:geotools-wrapper:1.6.0-28.2\
  --class Main original-sedona-spark-example-1.0.0.jar
```
Nota bene: according if you want to launch an example which use your modified Sedona code, you will need to change this file and the pom.xml file to explain to the compiler what piece of code does it has to take into account.
Therefore, you will just launch this `.sh` file:
```
./spark-submit.sh
```

Now, you must have launched your first Sedona example.
You can now retry with a modified version !
