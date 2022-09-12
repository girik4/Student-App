import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// interface (implemented with proposal)
/**
 * Interface defining necessary methods for the student loader class
 * These methods must be implemented for StudentLoader.java to function correctly.
 */
interface StudentLoaderInterface {
    public List<StudentDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;
    public List<StudentDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException;
}

// public class for StudentLoader
/**
 * Student Loader creates a class that parses data from a and puts it into a convinient form for usage in the back end
 */
public class StudentLoader implements StudentLoaderInterface {

  private String[] majorKeys = new String[40];
  private String[] majors = new String[40];

  /**
   * Loads a data file and parses it for student information
   * Uses the structure of the file to separate out each field that must be 
   * used to construct a new student.
   *
   * @param - File path of file to parse
   * @return - LinkedList of students
   */
  @Override
  public List<StudentDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
    File csvFile = new File(csvFilePath);
    List<StudentDataInterface> studentList = new LinkedList<StudentDataInterface>();
    //Confirms the loaded file is valid
    if(csvFile.isFile()) {
      Scanner scnr = new Scanner(csvFile);
      String[] elements;
      String nextStudent = scnr.nextLine();
      //Due to structure of csv, leading null value was found that must be removed
      nextStudent = nextStudent.substring(1);
      //Iterates through csv
      while(scnr.hasNext()) { 
        elements = nextStudent.split(",");

        //Gets and formats name element
        String name = elements[2];
        
        //Gets and formats id element
        String stringID = elements[0];
        stringID = stringID.substring(3, stringID.length());
        Integer id = 0;
	
	//Confirms id element is valid
	try {
          id = Integer.parseInt(stringID);
        } catch (Exception excpt) {
          System.out.println("Error in parsing id string");
	  throw new FileNotFoundException();
        }
	
        
        //Gets and formats year element
        String stringYear = elements[3];
        int year = 0;

	//Confirms year element is valid
        try {
          year = Integer.parseInt(stringYear);
        } catch (Exception excpt) {
          System.out.println("Error in parsing year");
	  throw new FileNotFoundException();
        }
        
        //Gets and formats major element
	String major = elements[1];
	major = getStudentMajor(major);

	//Once all data found, student is constructed and added to list
	StudentData student = new StudentData(name, id, year, major);
	studentList.add(student);
     
	nextStudent = scnr.nextLine();
      }
      return studentList;
    }
    throw new FileNotFoundException();
  }
  
  /**
   * Formats the student majors
   * Because the data came with key codes, a 1-1 array conversion is done
   * First student major code is found in the array
   * Then the corresponding major as a string is returned
   *
   * @param - Code to use for finding student major in normal text
   * @return - Student major as normal text
   */
  private String getStudentMajor(String majorCode) {
    for(int i = 0; i < majorKeys.length; i++){
	    if(majorCode.equals(majorKeys[i])){
		    return majors[i];
	    }
    }

    return majorCode;
  }

  /**
   * Loads all files in directory and appropriately handles them
   * For the majors file, the codes are parsed and related to their value
   * For student file, the loadFile method is used
   * This returns the final linked list of all students with their fields correctly updated
   *
   * @param - Directory path to load all files from
   * @return - LinkedList of students
   */
  @Override
  public List<StudentDataInterface> loadAllFilesInDirectory(String directoryPath) 
      throws FileNotFoundException {
    //Loads directory
    File directory = new File(directoryPath);
    if(!directory.isDirectory()){
	    throw new FileNotFoundException();
    }

    //Starts by parsing labels file
    File labelsFile = new File(directoryPath + "/Major_Information.csv");
    Scanner scnr = new Scanner(labelsFile);
    String currentLine = scnr.nextLine();
    //Takes in every value and fills the majorKeys array with keys
    //Fills the majors array with corresponding major string at same index
    for(int i = 0; i < majors.length - 1; i++){
	    String[] columns = currentLine.split(",");
	    majorKeys[i] = columns[0];
	    majors[i] = columns[1];
	    currentLine = scnr.nextLine();
    }

    //Generates file path for loadFile() method
    String filePath = directoryPath + "/Student_Information.csv";
    //Uses load file method to create linked list of students
    List<StudentDataInterface> studentInfo = loadFile(filePath);
    return studentInfo;
  }
}

/**
 * Sample StudentLoader class that simulates loading data by adding placeholders to the lists
 */
class StudentLoaderPlaceholder implements StudentLoaderInterface {
 
  /**
   * Adds sample students to the linked list to simulate parsing students from the data
   *
   * @return LinkedList of student placeholders
   */
  public List<StudentDataInterface> loadFile(String csvFilePath) throws FileNotFoundException{
      List<StudentDataInterface> list = new LinkedList<>();
      list.add(new StudentDataPlaceholderA());
      list.add(new StudentDataPlaceholderB());
      return list;
  }

  /**
   * Adds sample students to the linked list to simulate the loadAllFilesInDirectory method adding students from data
   *
   * @return LinkedList of student placeholder
   */
  public List<StudentDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException{
      List<StudentDataInterface> list = new LinkedList<>();
      list.add(new StudentDataPlaceholderA());
      list.add(new StudentDataPlaceholderB());
      list.add(new StudentDataPlaceholderC());
      return list;
  }
}
