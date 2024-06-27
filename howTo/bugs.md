Some bugs I had working on this projects and the way I fixed them (when I fixed then :) )

# How to push when it looks like your connection is not very good
## Warning: don't do multiple commits
If, like me, you try many ways to achieve pushing, you will got a problem.
In facts, while you haven't push, the commits stack. Therefore, each time
you try to push, git tryes to push every commit you did, including the wrong ones
that you tryed to fix doing other commits.
## What can I do if I have done many commits ?
### Easy way: clone, copy and push
First, create another directory:
```
cd path/to/the/directory
```
Then, clone there the Sedona3D repo:
```
git clone git@github.com:aitorniz/sedona3D.git
```
We must stop there to make sure that you understood 
what you're doing.
You have your most recent repo which is in your machine. You 
can't push it. And you have your less recent repo, in Git hub.
What we are doing is to clone again the older repo. Then, we
will add manually the created/modified/compressed files there. So
your older work will be updated by yourself. And, we will
push this to the Git hub repo.
Finally and only when you will have checked that the update
and the push are complete, you delete the newest repo.

Practically, I renamed the newer repo as `sedona3Dcrashed`
to avoid conflicts.
Therefore, you can copy the files:
```
cp -r path/to/the/sedona3Dcrashed/file/you/want/to/save path/to/sedona3D
```
Finally, you can just commit and push
```
git add fileYouWantToPush
git commit -m "A very clear message to explain what you're doing accurately"
git push
```

### Advanced way: modify the commit itself
I didn't try it

I got and error of size because I tryed to also push the fat jar of sedona's examples.
When 
In fact, I tryed many ways to fix the problem. Firstly, I compress it

