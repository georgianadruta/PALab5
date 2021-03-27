# Laboratory 5
## Media Catalog
This repository contains all the problems proposed for the fifth laboratory in Advanced Programming course solved by me.

# Essentials tools
You need to have Java RE or JDK >= 8 installed on your computer.

# Build and run the above programs
Launch IntelliJ IDEA and click ▶️ in the gutter and select Run 'Main()' in the popup. The IDE starts compiling your code. When the compilation is complete, the Run tool window opens at the bottom of the screen

# Tasks
Write an application that can manage a catalog of multimedia items. An entry in this catalog might be a song, a movie, a book, an image or any item that has at least a name and a path in the local file system. (We may also consider specifying a release year, a rating and other additional data, for example the author of the book, etc.)

The main specifications of the application are:
# Compulsory
- [x] Create an object-oriented model of the problem. You should have at least the following classes: Catalog and two item classes at your choice. Consider using an interface or an abstract class in order to describe the items in a catalog.
- [x] Implement the following methods representing commands that will manage the content of the catalog:
- add: adds a new entry into the catalog;
- list: prints the content of the catalog on the screen;
- play: playback using the native operating system application (see the Desktop class);
- save: saves the catalog to an external file (either as a text or binary, using object serialization);
- load: loads the catalog from an external file.
- [x] The application will signal invalid data (year, path, etc.) using a custom exception.
## Output
```
Create...
File meme created successfully.
File anotherMeme created successfully.
File Cinderella story created successfully.
File Snow White and the Seven Dwarfs created successfully.

Save...

Content of the catalog:
2 anotherMeme
1 meme
3 Cinderella story
4 Snow White and the Seven Dwarfs

Load...
IOException in load method.

Open...

Open...

Open...

Open...

Program executed successfully.
```

# Optional
- [x] Create a shell that allows reading commands from the keyboard, together with their arguments.
- [x] Represent the commands using classes instead of methods. Use an interface or an abstract class in order to desribe a generic command.
- [x] Implement the commands add, list, save, load, play (create the classes AddCommand, ListCommand, etc.).
- [x] Implement the command report: create (and open) an HTML report representing the content of the catalog.
- [x] Use a template engine such as FreeMarker or Velocity, in order to create the HTML report.
- [x] The application will signal the commands that are not valid using a custom exception.
- [ ] The final form of the application will be an executable JAR archive. Identify the generated archive and launch the application from the console, using the JAR.
## Output

```
00:31:51.967 [main] INFO  optional.Demo - 
Welcome to the shell :)
Attention! Values that represent variables and contain more than one word will contain '_' between words.
The commands are not sensitive case.
Commands to use:
1) add image name_of_image description id path: adds a new image into the catalog;
2) add book name_of_book authors_of_book id uri: adds a new book into the catalog;
2) list: prints the content of the catalog on the screen
3) play imageName: playback using the native operating system application
4) play videoName: playback using the native operating system application
5) save: saves the catalog to an external file
6) load: loads the catalog from an external file
7) report: create (and open) an HTML report representing the content of the catalog
8) info: extract and display metadata from catalog items
9) exit the shell

00:31:51.972 [main] INFO  optional.Demo - 
First command:
add image meme fun 1 ./demoFiles
00:32:00.058 [main] ERROR optional.commands.AddCommand - Invalid path to image
00:32:00.058 [main] INFO  optional.Demo - 
Next command:
add image meme fun 1 ./demoFiles/meme.jpg
File meme created successfully.
00:32:13.310 [main] INFO  optional.Demo - 
Next command:
play meme
File meme created successfully.
00:32:16.412 [main] INFO  optional.commands.PlayCommand - 
Open...
00:32:16.802 [main] INFO  optional.Demo - 
Next command:
add book book an 1 https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/
00:32:31.432 [main] ERROR optional.commands.AddCommand - Id is not unique.
00:32:31.432 [main] INFO  optional.Demo - 
Next command:
add book book an 2 https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/
File book created successfully.
00:32:40.605 [main] INFO  optional.Demo - 
Next command:
play book
File book created successfully.
00:32:43.532 [main] INFO  optional.commands.PlayCommand - 
Open...
00:32:43.790 [main] INFO  optional.Demo - 
Next command:
list
00:32:49.530 [main] INFO  optional.commands.ListCommand - 
Content of the catalog:
00:32:49.531 [main] INFO  optional.commands.ListCommand - 
1 meme
00:32:49.531 [main] INFO  optional.commands.ListCommand - 
2 book
00:32:49.532 [main] INFO  optional.Demo - 
Next command:
save file.txt
00:32:54.086 [main] INFO  optional.commands.SaveCommand - 
Save...
00:32:54.088 [main] INFO  optional.Demo - 
Next command:
report file.html
00:33:01.007 [main] INFO  optional.commands.ReportCommand - 
Create an HTML report ...
Next command:
exit

Process finished with exit code 0
```
![image](https://user-images.githubusercontent.com/75542257/112731192-7e9e9b00-8f3e-11eb-8010-9bc7548d059e.png)
# Bonus
- [x] Use Apache Tika in order to extract metadata from your catalog items and implement the command info in order to display them.
- [x] Create a graph G in which the catalog items represent the nodes, two nodes being connected if they share some common feature (for example, two songs that have the same artist, etc).
- [x] Suppose that you want to play all the items in the catalog in as few days as possible, so that in one day you will not play two items that are connected in G. Create an algorithm that generates the playlists for each day.
- [x] Create large graphs and test your algorithm.
## Output

```
00:47:32.038 [main] INFO  optional.Demo - 
Welcome to the shell :)
Attention! Values that represent variables and contain more than one word will contain '_' between words.
The commands are not sensitive case.
Commands to use:
1) add image name_of_image description id path: adds a new image into the catalog;
2) add book name_of_book authors_of_book id uri: adds a new book into the catalog;
2) list: prints the content of the catalog on the screen
3) play imageName: playback using the native operating system application
4) play videoName: playback using the native operating system application
5) save: saves the catalog to an external file
6) load: loads the catalog from an external file
7) report: create (and open) an HTML report representing the content of the catalog
8) info: extract and display metadata from catalog items
9) exit the shell

00:47:32.042 [main] INFO  optional.Demo - 
First command:
add image meme fun 1 ./demoFiles/meme.jpg
File meme created successfully.
00:47:54.417 [main] INFO  optional.Demo - 
Next command:
add image fun laugh 2 ./demoFiles/true.jpg
File fun created successfully.
00:48:10.241 [main] INFO  optional.Demo - 
Next command:
add book meme i 3 https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/
File meme created successfully.
00:48:26.437 [main] INFO  optional.Demo - 
Next command:
exit
00:48:28.670 [main] INFO  bonus.solution.Solution - Day 1: 
00:48:28.671 [main] INFO  bonus.solution.Solution - Item(id=1, name=meme)
00:48:28.671 [main] INFO  bonus.solution.Solution - Item(id=2, name=fun)
00:48:28.672 [main] INFO  bonus.solution.Solution - Day 2: 
00:48:28.672 [main] INFO  bonus.solution.Solution - Item(id=3, name=meme)

Process finished with exit code 0

```

