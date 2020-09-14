public class ArgumentHandler {

  FileHandler fileHandler = new FileHandler();

  public void checkArgument(String[] args){
    if (args.length == 0) {
      printUsage();
    } else if (args[0].equals("-l")) {
      fileHandler.readFile();
    } else if (args[0].equals("-a")) {
      fileHandler.addNewTaskToFile(args);
    } else if (args[0].equals("-r")) {
      fileHandler.removeTaskFromFile(args);
    } else if (args[0].equals("-c")) {
      fileHandler.completeTaksInFile(args);
    } else {
      System.out.println("Unsupported argument");
      printUsage();
    }
  }

  private void printUsage() {
    System.out.println("\n" +
            "Command Line Todo application\n" +
            "=============================\n" +
            "\n" +
            "Command line arguments:\n" +
            "    -l   Lists all the tasks\n" +
            "    -a   Adds a new task\n" +
            "    -r   Removes an task\n" +
            "    -c   Completes an task");
  }
}