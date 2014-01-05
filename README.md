ConsoleDraw
===========

Some hacking around command line drawing

+ git clone git@github.com:howzat/ConsoleDraw.git console-draw
+ cd console-draw
+ mvn clean install
+ ./run.sh

<pre>
Enter command:C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------
Enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------
Enter command:L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------
Enter command:R 16 1 20 3
----------------------
|               xxxxx|
|xxxxxx         x   x|
|     x         xxxxx|
|     x              |
----------------------
Enter command:B 10 3 o
----------------------
|oooooooooooooooxxxxx|
|xxxxxxooooooooox   x|
|     xoooooooooxxxxx|
|     xoooooooooooooo|
----------------------
Enter command:Q
</pre>














