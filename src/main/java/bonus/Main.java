package bonus;

import bonus.problem.Problem;
import bonus.solution.Solution;
import optional.Demo;

public class Main {
    public static void main(String[] args) {
         /*image1 = "./demoFiles/meme.jpg";
        image2 = "./demoFiles/anotherMeme.jpg";
        book1 = "https://storiestogrowby.org/story/cinderella-fairy-tale-english-story-for-kids/";
        book2 ="https://storiestogrowby.org/story/snow-white-and-the-seven-dwarfs-bedtime-stories-for-kids/";*/
        Demo demo = new Demo();
        demo.displayCommands();
        demo.readCommands();
        Problem problem = new Problem(demo.getCatalog());
        Solution solution = new Solution(demo.getCatalog());
        solution.create();
        solution.display();
    }
}
