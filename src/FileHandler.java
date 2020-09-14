import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
  private final String fileName = "storeTheData.txt";

  public void readFile(){
    try {
      Path filePath = Paths.get(fileName);
      listTasks(filePath);
    } catch (IOException e1){
      System.out.println("file not available");
      e1.printStackTrace();
    }
  }

  private void listTasks(Path filePath) throws IOException {
    List<String> lines = new ArrayList<>(Files.readAllLines(filePath));
    if (lines.size() == 0) {
      System.out.println("No todos for today! :)");
    } else {
      for (int i = 0; i < lines.size(); i++) {
        if (lines.get(i).startsWith("X ")) {
          System.out.println((i + 1) + " [X] " + lines.get(i).substring(2));
        }
        if (!lines.get(i).startsWith("X ")) {
          System.out.println((i + 1) + " [ ] " + lines.get(i));
        }
      }
    }
  }

  public void addNewTaskToFile(String[] args){
    if (args.length == 1){
      System.out.println("nothing to add, nothing provided");
    } else {
      try {
        Path filePath = Paths.get(fileName);
        List<String> lines = Files.readAllLines(filePath);
        lines.add(args[1]);
        Files.write(filePath, lines);
      } catch (Exception e) {
        System.out.println("No match.");
      }
    }
  }

  public void removeTaskFromFile(String[] args) {
    int indexFromArgument = Integer.parseInt(args[1]);
    Path filePath = Paths.get(fileName);
    int listSize = listLength(filePath);
    if (args.length == 1){
      System.out.println("Unable to remove: no index provided");
    } else if (Integer.parseInt(args[1]) > listSize){
      System.out.println("Unable to remove: index is out of bound");
    } else {
      try {
        removeTask(filePath, indexFromArgument);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private int listLength(Path filePath) {
    List<String> tasks = null;
    try {
      tasks = new ArrayList<>(Files.readAllLines(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tasks.size();

  }

  private void removeTask(Path filePath, int indexFromArgument) throws IOException {
    List<String> lines = new ArrayList<>(Files.readAllLines(filePath));
    lines.remove(indexFromArgument - 1);
    Files.write(filePath, lines);
  }

  public void completeTaksInFile(String[] args) {
    int indexFromArgument = -1;
    try{
      indexFromArgument = Integer.parseInt(args[1])-1;
    } catch (NumberFormatException e1) {
      System.out.println("Unable to check: index is not a number");
    }

    Path filePath = Paths.get(fileName);
    if (args.length == 1){
      System.out.println("Unable to check: no index provided");
      return;
    }
    try {
      List<String> lines = new ArrayList<>(Files.readAllLines(filePath));
      if (indexFromArgument > lines.size()){
        System.out.println("Unable to check: index is out of bound");
        return;
      }
      lines.set(indexFromArgument, "X " + lines.get(indexFromArgument));
      Files.write(filePath, lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}