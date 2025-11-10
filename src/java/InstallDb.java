/*
 * Dimas Deffieux
 * To Install database 
 */


public class InstallDb
{ 
    public static void main(String[] args)
    {
        String dbName = "ForumDB";
        // Creating an object of DB class
        JavaDatabase objDb = new JavaDatabase();
        // creating a new database
        //objDb.createDb(dbName);
        // creating a new table
        
        //userlist Table 
//        String usersTable = "CREATE TABLE UserList(username varchar(50),"
//                        + "password varchar(50))";
//        objDb.createTable(usersTable, dbName); 

          //pages table
//          String pagesTable = "CREATE TABLE Posts(postId varchar(10), title varchar(100), "
//            + "content LONGTEXT, dateCreated DATE, poster varchar(50), category varchar(50))";
//          objDb.createTable(pagesTable, dbName);
          
          //comments table
//          String commentsTable = "CREATE TABLE XXXXXXXXXXComments(content LONGTEXT,"
//            + " dateCreated DATE, poster varchar(50))";
//          objDb.createTable(commentsTable, dbName);
  

        //tableList:
        //UserList, Pages, Comments, 
        // creating a second table
//        String myNewTable = "CREATE TABLE Cars2 (Name2 varchar(50),"
//                        + "Price2 int, Color2 varchar(50) )";
//        objDb.createTable(myNewTable, dbName); 
        
    }
}

