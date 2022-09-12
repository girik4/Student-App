/**
 * Interface for implementing student data objects for other team members to use
 * Also used for the loader class
 *
 * Implements comparable so student data objects can be compared
 * This comparison is required to be put into a tree
 *
 */
interface StudentDataInterface extends Comparable<StudentDataInterface>{
  public String getName();
  public Integer getID();
  public int getYear();
  public String getMajor();
}

/**
 * Main student data class
 * Contains fields for all important student data
 * Accessors are created for each field
 */
public class StudentData implements StudentDataInterface{
  
  String studentName;
  Integer studentID;
  int studentYear;
  String studentMajor;
  
  /**
   * Constructor for studentData object
   * Takes in all key fields
   *
   * @param name - Student name
   * @param id - Student id
   * @param year - Student current year in school
   * @param major - Studen tmajor
   */
  public StudentData(String name, Integer id, int year, String major){
    studentName = name;
    studentID = id;
    studentYear = year;
    studentMajor = major;
  }


  /**
   * Accessor for name field
   *
   * @return String containing student name
   */
  @Override
  public String getName() {
    return studentName;
  }

  /**
   * Accessor for id field
   *
   * @return Integer containing student id
   */
  @Override
  public Integer getID() {
    return studentID;
  }

  /**
   * Accessor for year field
   *
   * @return int containing student grade between 1 and 4
   */
  @Override
  public int getYear() {
    return studentYear;
  }

  /**
   * Accessor for major field
   *
   * @return String containing student major
   */
  @Override
  public String getMajor() {
    return studentMajor;
  }

  /**
   * Required method to implement comparable interface
   * Directly compares student ID values to determine the greater of the two
   *
   * @return Positive if greater than, negative if less than, 0 if equal
   */
  @Override
  public int compareTo(StudentDataInterface o) {
    return this.studentID - o.getID();
  }

  /**
   * Overrides object equals method
   * Used extensively in testing, so it was best to implement the method directly
   * Compares each field and confirms values are identical
   *
   * @return true if two student data objects are identical
   */
  @Override
  public boolean equals(Object other){
	  if(!(other instanceof StudentDataInterface)){
		return false;
	  }
	  StudentDataInterface otherStudent = (StudentDataInterface) other;
	  if(!(otherStudent.getName().equals(this.studentName))){
		return false;
	  }
	  if(!(otherStudent.getID().equals(this.studentID))){
		  return false;
	  }
	  if(!(otherStudent.getYear() == this.studentYear)){
		  return false;
	  }
	  if(!(otherStudent.getMajor().equals(this.studentMajor))){
		  return false;
	  }
	  return true;
  }
}


/**
 * Sample student for use in testing
 */
class StudentDataPlaceholderA implements StudentDataInterface {
  
  /**
   * Sample compareTo method that is always greater than compared item
   *
   * @return 1 meaning this object is greater than parameter
   */
  @Override
  public int compareTo(StudentDataInterface o) {
    return 1; //Will be greater than anything compared to
  }

  /**
   * Sample getName() method
   *
   * @return student name
   */
  @Override
  public String getName() {
    return "Chris";
  }

  /**
   * Sample getID() method
   *
   * @return sample student id
   */
  @Override
  public Integer getID() {
    return 334455;
  }

  /**
   * Sample getYear() method
   *
   * @return sample student year
   */
  @Override
  public int getYear() {
    return 2; //Years go from 1 - 4 corresponding to grade
  }

  /**
   * Sample getMajor() method
   *
   * @return sample major
   */
  @Override
  public String getMajor() {
    return "Computer Engineering";
  }
}


/**
 * Sample student for use in testing
 */
class StudentDataPlaceholderB implements StudentDataInterface {
  
  /**
   * Sample compareTo method
   *
   * @return -1 meaning this student is less than the object it is compared to
   */
  @Override
  public int compareTo(StudentDataInterface o) {
    return -1; //Will be smaller than anything compared to
  }

  /**
   * Sample getName() major
   *
   * @return sample name
   */
  @Override
  public String getName() {
    return "Dan";
  }

  /**
   * Sample getID() method
   *
   * @return sample ID
   */
  @Override
  public Integer getID() {
    return 111222;
  }

  /**
   * Sample getYear() method
   *
   * @return sample year
   */
  @Override
  public int getYear() {
    return 1; 
  }

  /**
   * Sample getMajor() method
   *
   * @return sample major
   */
  @Override
  public String getMajor() {
    return "Computer Science";
  }
}


/**
 * Sample student for use in tasting
 */
class StudentDataPlaceholderC implements StudentDataInterface {
  
  /**
   * Sample compareTo method
   * Always returns 0 meaning equality between objects
   *
   * @return 0 signifying an equal object
   */
  @Override
  public int compareTo(StudentDataInterface o) {
    return 0; //Will be equal to anything compared to
  }

  /**
   * Sample getName() method
   *
   * @return sample name
   */
  @Override
  public String getName() {
    return "Jim";
  }

  /**
   * Sample getID() method
   *
   * @return sample ID
   */
  @Override
  public Integer getID() {
    return 444555;
  }

  /**
   * Sample getYear() method
   *
   * @return sample year
   */
  @Override
  public int getYear() {
    return 4; 
  }

  /**
   * Sample getMajor() method
   *
   * @return sample major
   */
  @Override
  public String getMajor() {
    return "Data Science";
  }
}

