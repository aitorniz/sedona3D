# How to get informations about the size of files
## Using the classic `ls`
RUn the following:
```
ls -al
```
## Using `du` command
I mainly used [forum about du](https://www.geeksforgeeks.org/du-command-linux-examples/)
The most useful I found was the following:
```
du --time -c -hs you_directory
```
##Using `file`
You can also run the following in order to get many informations, among what, the size:
```
file fileName
```
