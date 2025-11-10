/*
 * NOT Mansoor Ansari, Summer of 1997
 * To access Database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class JavaDatabase
{

  private String dbName;
  private Connection dbConn;
  private ArrayList<ArrayList<String>> data;
  private int postNum;

  public JavaDatabase()
  {
    dbName = "";
    dbConn = null;
    data = null;
  }

  public JavaDatabase(String dbName)
  {
    setDbName(dbName);
    setDbConn();
    data = null;
  }

  public String getDbName()
  {
    return dbName;
  }

  public void setDbName(String dbName)
  {
    this.dbName = dbName;
  }

  public Connection getDbConn()
  {
    return dbConn;
  }

  public void setDbConn()
  {
    String connectionURL = "jdbc:mysql://localhost:3306/" + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check Library");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error!");
    }
  }

  public void closeDbConn()
  {
    try
    {
      this.dbConn.close();
    }
    catch (Exception err)
    {
      System.out.println("DB closing error.");
    }
  }

  //code to generate random id for 
  public String generateID(int length, String tableName, String tableHeaders)
  {
    System.out.println("what is going on here");
    String id = "";
    Random random = new Random();
    String alphaNumerics = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    ArrayList<ArrayList<String>> data = new ArrayList<>();
    for (int i = 0; i < length; i++)
    {
      id = id + alphaNumerics.charAt(random.nextInt((alphaNumerics.length() - 1)));

      System.out.println("what are the ids sir");
      data = getPostId(tableName);
      System.out.println(data);
      System.out.println("goddam");

      if (data.size() != 0)
      {
        for (int j = 0; j < data.get(0).size(); j++)
        {
          System.out.println(j + "th time running through loop");
          if (id.equals(data.get(0).get(j)))
          {
            System.out.println("oh no!");
            id = "";
            i = 0;
          }
          System.out.println("chekc hfiefhiehfiehf");
        }
        System.out.println("oh no here we go again");
      }
      System.out.println("is there a problem here officer");

    }
    return id;
  }

  public ArrayList<ArrayList<String>> getData(String tableName,
                                              String[] tableHeaders)
  {
    int columnCount = tableHeaders.length;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName;
    this.data = new ArrayList<>();
    // read the data
    try
    {
      // send the query and receive data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);

      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // read cell i
          // example: String cell = rs.getString("Name");
          // reads the cell in column Name
          // tableHeader={"Name", "Age", "Color"}
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row
          // example row.add("Vinny");
          row.add(cell);
        }
        // add the row to the data
        // example: data.add "Vinny",15,"Pink"
        this.data.add(row);
      }
    }
    catch (SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }

  public ArrayList<ArrayList<String>> getCategoryData(String tableName,
                                                      String[] tableHeaders, String category)
  {
    int columnCount = tableHeaders.length;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName + " WHERE category = '" + category + "'";
    this.data = new ArrayList<>();
    // read the data
    try
    {
      // send the query and receive data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);

      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // read cell i
          // example: String cell = rs.getString("Name");
          // reads the cell in column Name
          // tableHeader={"Name", "Age", "Color"}
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row
          // example row.add("Vinny");
          row.add(cell);
        }
        // add the row to the data
        // example: data.add "Vinny",15,"Pink"
        this.data.add(row);
      }
    }
    catch (SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }

  public ArrayList<ArrayList<String>> getPostId(String tableName)
  {
    int columnCount = 1;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT postId FROM " + tableName;
    this.data = new ArrayList<>();
    // read the data
    try
    {
      // send the query and receive data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);

      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // read cell i
          // example: String cell = rs.getString("Name");
          // reads the cell in column Name
          // tableHeader={"Name", "Age", "Color"}
          String cell = rs.getString(1);
          // add the cell to the row
          // example row.add("Vinny");
          row.add(cell);
        }
        // add the row to the data
        // example: data.add "Vinny",15,"Pink"
        this.data.add(row);
      }
    }
    catch (SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }

  public void setData(ArrayList<ArrayList<String>> data)
  {
    this.data = data;
  }

  public void createDb(String newDbName)
  {
    setDbName(newDbName);
    Connection newConn;
    // create a db if not existing
    String connectionURL = "jdbc:mysql://localhost:3306/";
    String query = "CREATE DATABASE " + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      newConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
      Statement s = newConn.createStatement();
      s.executeUpdate(query);
      System.out.println("new db created");
      newConn.close();
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check library");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error, Db was not created!");
    }
  }

  public void createTable(String newTable, String dbName)
  {
    System.out.println(newTable);
    setDbName(dbName);
    setDbConn();
    Statement s;
    try
    {
      s = this.dbConn.createStatement();
      s.execute(newTable);
      System.out.println("New table created!");
      this.dbConn.close();
    }
    catch (SQLException se)
    {
      System.out.println("Error creating table " + newTable);
    }
  }
// to conver a 2d arraylist to 2d array:

  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    if (data.size() == 0) // empty table
    {
      Object[][] dataList = new Object[0][0];
      return dataList;
    }
    else // table w existing data
    {
      int columnCount = data.get(0).size(); // number of columns
      Object[][] dataList = new Object[data.size()][columnCount];
      // read each cell of each row into array
      for (int r = 0; r < data.size(); r++)
      {
        ArrayList<String> row = data.get(r); // get the row
        for (int c = 0; c < columnCount; c++)
        {
          dataList[r][c] = row.get(c); // get the cell
        }
      }
      return dataList;
    }
  }

  //method to search db for login info
  public boolean authenticate(String tableName,
                              String username,
                              String password)
  {
    boolean match = false;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName + " WHERE username = ? AND"
      + " password = ?";

    // read the data
    // connect to db
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();

    try
    {
      // prepare statement
      PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
      // enter data into query
      ps.setString(1, username);
      ps.setString(2, password);

      // execute the query
      rs = ps.executeQuery();
      match = rs.next();

    }
    catch (SQLException se)
    {
      System.out.println("Error getting data");
    }
    return match;
  }

  public int register(String tableName, String username, String password)
  {
    int status;
    ResultSet rs;
    boolean match = false;

    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    //query for prepared statement
    String selectQuery = "SELECT * FROM " + tableName + " WHERE username = ?";
    String insertQuery = "INSERT INTO " + tableName + " VALUES (?,?)";

    try
    {
      // prepare statement
      PreparedStatement ps = myDbConn.prepareStatement(selectQuery);
      // enter data into query
      ps.setString(1, username);
      // execute the query
      rs = ps.executeQuery();
      match = rs.next();
    }
    catch (SQLException se)
    {
      status = 3;
      System.out.println("Error inserting data");
    }

    if (match)
    {
      status = 1;
      System.out.println("username already exists");
    }
    else if (!match)
    {
      try
      {
        // prepare statement
        PreparedStatement ps = myDbConn.prepareStatement(insertQuery);
        // enter data into query
        ps.setString(1, username);
        ps.setString(2, password);
        // execute the query
        ps.executeUpdate();
        System.out.println("Data inserted successfully");
        status = 2;
      }
      catch (SQLException se)
      {
        status = 3;
        System.out.println("Error inserting data");
      }
    }
    else
    {
      status = 3;
    }
    return status;
  }

  //not finished still don't know what format to send text in
  public String addPost(String tableName, String username, String content, String title, String[] columnNames, String category)
  {
    System.out.println("MAKING POSt");
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    //query for prepared statement
    String postID = generateID(10, tableName, "postID");
    String insertQuery = "INSERT INTO " + tableName + " VALUES (?,?,?,curdate(),?,?)";
    try
    {

      System.out.println("insert heheheha");
      System.out.println(postID + "," + title + "," + content + "," + username + "," + category);

      // prepare statement
      PreparedStatement ps = myDbConn.prepareStatement(insertQuery);
      // enter data into query
      ps.setString(1, postID);
      ps.setString(2, title);
      ps.setString(3, content);
      ps.setString(4, username);
      ps.setString(5, category);
      // execute the query
      ps.executeUpdate();
      System.out.println("Data inserted successfully");

      String commentsTable = "CREATE TABLE " + postID + "Comments(content LONGTEXT,"
        + " dateCreated DATE, poster varchar(50))";
      objDb.createTable(commentsTable, dbName);
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data");
    }

    return postID;
  }

  public ArrayList<ArrayList<String>> getPost(String tableName,
                                              String[] tableHeaders, String postId)
  {
    int columnCount = tableHeaders.length;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName + " WHERE postId = '" + postId + "'";
    this.data = new ArrayList<>();
    // read the data
    try
    {
      // send the query and receive data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);

      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // read cell i
          // example: String cell = rs.getString("Name");
          // reads the cell in column Name
          // tableHeader={"Name", "Age", "Color"}
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row
          // example row.add("Vinny");
          row.add(cell);
        }
        // add the row to the data
        // example: data.add "Vinny",15,"Pink"
        this.data.add(row);
      }
    }
    catch (SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }

  public void addComment(String tableName, String username, String content)
  {

    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    //query for prepared statement
    String insertQuery = "INSERT INTO " + tableName + " VALUES (?,curdate(),?)";
    try
    {
      // prepare statement
      PreparedStatement ps = myDbConn.prepareStatement(insertQuery);
      // enter data into query
      ps.setString(1, content);
      ps.setString(2, username);
      // execute the query
      ps.executeUpdate();
      System.out.println("Data inserted successfully");
    }
    catch (SQLException se)
    {
      System.out.println("Error inserting data");
    }

  }

  public void editPost(String tableName, String content, String postId)
  {

    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    //query for prepared statement
    String updateQuery = "UPDATE " + tableName + " SET content = ? WHERE postID=?";
    try
    {
      // prepare statement
      PreparedStatement ps = myDbConn.prepareStatement(updateQuery);
      // enter data into query
      ps.setString(1, content);
      ps.setString(2, postId);

      // execute the query
      ps.executeUpdate();
      System.out.println("Post updated");
    }
    catch (SQLException se)
    {
      System.out.println("error updating post");
    }

  }

  public void deletePost(String tableName, String postId)
  {

    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    //query for prepared statement
    String insertQuery = "DELETE FROM " + tableName + " WHERE postID=?";
    String deleteCommentTable="DROP TABLE "+postId+"Comments";
    try
    {
      // prepare statement
      PreparedStatement ps = myDbConn.prepareStatement(insertQuery);
      // enter data into query
      ps.setString(1, postId);

      // execute the query
      ps.executeUpdate();
      System.out.println("Post gone.");
    }
    catch (SQLException se)
    {
      System.out.println("error goneifying post");
    }
    
    setDbConn();
    Statement s;
    try
    {
      s = this.dbConn.createStatement();
      s.execute(deleteCommentTable);
      System.out.println("comments deleted");
      this.dbConn.close();
    }
    catch (SQLException se)
    {
      System.out.println("Error deleting comment table");
    }

  }

  public ArrayList<ArrayList<String>> getUserPosts(String tableName,
                                                   String[] tableHeaders, String username)
  {
    int columnCount = tableHeaders.length;
    Statement s = null;
    ResultSet rs = null;
    String dbQuery = "SELECT * FROM " + tableName + " WHERE poster = '" + username + "'";
    this.data = new ArrayList<>();
    // read the data
    try
    {
      // send the query and receive data
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);

      // read the data using rs and store in ArrayList data
      while (rs.next())
      {
        // row object to hold one row data
        ArrayList<String> row = new ArrayList<>();
        // go through the row and read each cell
        for (int i = 0; i < columnCount; i++)
        {
          // read cell i
          // example: String cell = rs.getString("Name");
          // reads the cell in column Name
          // tableHeader={"Name", "Age", "Color"}
          String cell = rs.getString(tableHeaders[i]);
          // add the cell to the row
          // example row.add("Vinny");
          row.add(cell);
        }
        // add the row to the data
        // example: data.add "Vinny",15,"Pink"
        this.data.add(row);
      }
    }
    catch (SQLException se)
    {
      System.out.println("SQL Error: Not able to get data");
    }
    return data;
  }
  //add post method
//  public static void main(String[] args)
//  {
//
//    //db info
//    String dbName = "ForumDb";
//    String tableName = "Posts";
//    String[] columnNames =
//    {
//      "postId", "title", "content", "dateCreated", "poster"
//    };
//
//
//    
//    // data to be entered
//    String title = "the power of shock and regret";
//    String content = "i hate life <br> adding a line break for no reason";
//    String poster = "troy_the";
//    String category = "Question";
//
//    // connect to db
//    JavaDatabase objDb = new JavaDatabase(dbName);
//
//    //add post method
//    objDb.addPost(tableName, poster, content, title, columnNames, category);
//    
//    
//    
//    System.out.println(objDb.getData(tableName, columnNames));
//    
//
//  }

  //comments
  public static void main(String[] args)
  {

    //db info
    String dbName = "ForumDb";
    String tableName = "MRJXZVbufoComments";
    String[] columnNames =
    {
      "content", "dateCreated", "poster"
    };

    boolean match;
    int status;

    String content = "i hate life as well!";
    String poster = "troy_the";
    String postId = "MRJXZVbufo";

    //connect to db
    JavaDatabase objDb = new JavaDatabase(dbName);

    objDb.addComment(tableName, poster, content);
    System.out.println(objDb.getData(tableName, columnNames));
    //objDb.deleteComment(tableName, postId);
  }
//  
//  //accounts
//    public static void main(String[] args)
//  {
//    //db info
//    String dbName = "ForumDb";
//    String tableName = "UserList";
//    String[] columnNames =
//    {
//      "username","password"
//    };
//    
//    boolean match;
//    int status;
//    
//    String  password= "pain";
//    String username = "troy_the";
//
//    //connect to db
//    JavaDatabase objDb = new JavaDatabase(dbName);
//
//    objDb.authenticate(tableName,username,password);
//    //objDb.register(tableName, username, password);
//    
//  }

//  public static void main(String[] args)
//  {
//    //db info
//    String dbName = "ForumDB";
//    String tableName = "Posts";
//    String[] columnNames =
//    {
//      "postId", "title", "content", "dateCreated", "poster", "category"
//    };
//
//    String password = "pain";
//    String username = "troy_the";
//
//    //connect to db
//    JavaDatabase objDb = new JavaDatabase(dbName);
//
//    System.out.println(objDb.getCategoryData(tableName, columnNames, "Question"));
//    System.out.println(objDb.getUserPosts(tableName, columnNames,username));
//  }
}
