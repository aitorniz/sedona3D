# How to get informations about the size of files
## Using the classic `ls`
Run the following:
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

# How to get the path of a request
In order to k now by which places your
request go through, you can use tracepath.
For example, you can run:
```
tracepath ipAdressOfYourRemoteServer
```
# How to compress files with `tar`
You can use the following where:
-c: Create an archive.
-z: Compress the archive with gzip.
-v: Display progress in the terminal while creating the archive, also known as "verbose" mode. The v is always optional in these commands, but it's helpful.
-f: Allows you to specify the filename of the archive.
```
tar -czvf name-of-archive.tar.gz /path/to/directory-or-file
```

