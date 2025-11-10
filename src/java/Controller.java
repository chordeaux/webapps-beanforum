/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;


@WebServlet(
  name = "Controller",
  loadOnStartup = 1,
  urlPatterns =
  {
    "/index",
    "/loginRedirect",
    "/register",
    "/discussionsPage",
    "/accessPost",
    "/login",
    "/postPage",
    "/createPost",
    "/logout",
    "/submitComment",
    "/deletePost"
  }
)
public class Controller extends HttpServlet
{

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter())
    {
      /* TODO output your page here. You may use following sample code. */
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet Controller</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet Controller at " + request.getContextPath() + "</h1>");
      out.println("</body>");
      out.println("</html>");
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    //session object to communicate data
    HttpSession session = request.getSession();
    //key word sent by the client page
    String userPath = request.getServletPath();
    //processRequest(request, response);
    if (userPath.equals("/index"))
    {
      String myUrl = userPath + ".jsp";
      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }

    else if (userPath.equals("/login"))
    {
      String myUrl = "login.jsp";
      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/register"))
    {
      String myUrl = "register.jsp";
      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    System.out.println("BALLS");
    //session object to communicate data
    HttpSession session = request.getSession();
    //key word sent by the client page
    String userPath = request.getServletPath();
    //processRequest(request, response);
    if (userPath.equals("/discussionsPage"))
    {
      try
      {
        //bring from main method whatever is tested
        //dbinfo
        String dbName = "ForumDB";
        String tableName = "Posts";
        String[] columnNames =
        {
          "postId", "title", "content", "dateCreated", "poster", "category"
        };

        JavaDatabase objDb = new JavaDatabase(dbName);
        //questions
        ArrayList<ArrayList<String>> questionData
          = objDb.getCategoryData(tableName, columnNames, "Question");
        System.out.println(questionData);
        session.setAttribute("questionData", questionData);

        //discussions
        ArrayList<ArrayList<String>> discussionData
          = objDb.getCategoryData(tableName, columnNames, "Discussion");
        System.out.println(discussionData);
        session.setAttribute("discussionData", discussionData);

        //showcases
        ArrayList<ArrayList<String>> showcaseData
          = objDb.getCategoryData(tableName, columnNames, "Showcase");
        System.out.println(showcaseData);
        session.setAttribute("showcaseData", showcaseData);

        ArrayList<ArrayList<String>> announcmentData
          = objDb.getCategoryData(tableName, columnNames, "Announcement");
        System.out.println(announcmentData);
        session.setAttribute("announcementData", announcmentData);
        //url to go back to
        //url would be /WEB-INF/destination.jsp
        String myUrl = "/WEB-INF" + userPath + ".jsp";
        try
        {
          //redirect back to new URL with new data
          request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception e)
        {
          request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }

      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/loginRedirect"))
    {
      String dbName = "ForumDB";
      String tableName = "Posts";
      String[] columnNames =
      {
        "postId", "title", "content", "dateCreated", "poster", "category"
      };
      boolean loggedIn;
      if (session.getAttribute("loggedIn") == null)
      {
        loggedIn = false;
      }
      else
      {
        loggedIn = (boolean) session.getAttribute("loggedIn");
      }

      String myUrl;

      if (loggedIn)
      {
        JavaDatabase objDb = new JavaDatabase(dbName);
        myUrl = "/WEB-INF/accountPage.jsp";
        String username = (String) session.getAttribute("user");
        ArrayList<ArrayList<String>> userPosts
          = objDb.getUserPosts(tableName, columnNames, username);
        System.out.println(userPosts);
        session.setAttribute("userPosts", userPosts);
      }
      else
      {
        session.setAttribute("loginStatus", "");
        myUrl = "login.jsp";
      }

      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }

    else if (userPath.equals("/accessPost"))
    {
      String postId = request.getParameter("postId");
      session.setAttribute("postId", postId);
      try
      {
        //bring from main method whatever is tested
        //dbinfo
        String dbName = "ForumDB";
        String tableName = "Posts";
        String[] columnNames =
        {
          "postId", "title", "content", "dateCreated", "poster", "category"
        };

        JavaDatabase objDb = new JavaDatabase(dbName);
        ArrayList<ArrayList<String>> data
          = objDb.getPost(tableName, columnNames, postId);

        session.setAttribute("title", data.get(0).get(1));
        session.setAttribute("content", data.get(0).get(2));

        String[] commentColumnNames =
        {
          "content", "dateCreated", "poster"
        };
        System.out.println("should be getting comments now");
        String commentsTableName = postId + "Comments";
        session.setAttribute("commentTable", commentsTableName);

        System.out.println(commentsTableName);
        ArrayList<ArrayList<String>> commentData
          = objDb.getData(commentsTableName, commentColumnNames);
        System.out.println(commentData);
        session.setAttribute("comments", commentData);
        //url to go back to
        //url would be /WEB-INF/destination.jsp
        String myUrl = "/postPage.jsp";
        try
        {
          //redirect back to new URL with new data
          request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception e)
        {
          request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }

      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/login"))
    {
      //db info
      String dbName = "ForumDB";
      String tableName = "UserList";
      String[] columnNames =
      {
        "username", "password"
      };
      String postTable = "Posts";
      String[] postsColumnNames =
      {
        "postId", "title", "content", "dateCreated", "poster", "category"
      };
      boolean match;

      // connect to db
      JavaDatabase objDb = new JavaDatabase(dbName);

      String username = request.getParameter("username");
      session.setAttribute("username", username);
      String password = request.getParameter("password");
      session.setAttribute("password", password);

      match = objDb.authenticate(tableName, username, password);

      if (match)
      {
        String myUrl = "/WEB-INF/accountPage.jsp";
        session.setAttribute("loggedIn", true);
        session.setAttribute("user", username);
        ArrayList<ArrayList<String>> userPosts
          = objDb.getUserPosts(postTable, postsColumnNames, username);
        System.out.println(userPosts);
        session.setAttribute("userPosts", userPosts);

        try
        {
          //redirect back to new URL with new data
          request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception e)
        {
          request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }
      }
      else if (!match)
      {
        session.setAttribute("loginStatus", "Username or password is incorrect");
        String myUrl = "login.jsp";
        try
        {
          //redirect back to new URL with new data
          request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception e)
        {
          request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }
      }
      else
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/register"))
    {
      String myUrl = "register.jsp";

      //db info
      int status;
      String dbName = "ForumDB";
      String tableName = "UserList";

      // connect to db
      JavaDatabase objDb = new JavaDatabase(dbName);

      // data to be entered
      String username = request.getParameter("username");
      session.setAttribute("username", username);
      String password = request.getParameter("password");
      session.setAttribute("password", password);

      //register account
      status = objDb.register(tableName, username, password);

      switch (status)
      {
        case 1:
          session.setAttribute("registerSuccessful", "Username already exists");
          break;
        case 2:
          myUrl = "login.jsp";
          session.setAttribute("registerSuccessful", "");
          break;
        case 3:
          myUrl = "/WEB-INF/Error.jsp";
          break;
      }

      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/createPost"))
    {
      System.out.println("MAKING POST");
      try
      {
        //bring from main method whatever is tested
        //dbinfo
        String dbName = "ForumDB";
        String tableName = "Posts";
        String[] columnNames =
        {
          "postId", "title", "content", "dateCreated", "poster", "category"
        };

        JavaDatabase objDb = new JavaDatabase(dbName);
        String category = request.getParameter("category");
        System.out.println(category);
        String username = request.getParameter("username");
        System.out.println(username);
        String content = request.getParameter("content");
        System.out.println(content);
        String title = request.getParameter("title");
        System.out.println(title);
        String newPostId = objDb.addPost(tableName, username, content, title, columnNames, category);
        System.out.println("created post?");
        //url to go back to
        //url would be /WEB-INF/destinat
        session.setAttribute("postId", newPostId);

        ArrayList<ArrayList<String>> data
          = objDb.getPost(tableName, columnNames, newPostId);

        session.setAttribute("title", data.get(0).get(1));
        session.setAttribute("content", data.get(0).get(2));

        String[] commentColumnNames =
        {
          "content", "dateCreated", "poster"
        };
        System.out.println("should be getting comments now");
        String commentsTableName = newPostId + "Comments";
        session.setAttribute("commentTable", commentsTableName);

        System.out.println(commentsTableName);
        ArrayList<ArrayList<String>> commentData
          = objDb.getData(commentsTableName, commentColumnNames);
        System.out.println(commentData);
        session.setAttribute("comments", commentData);
        //url to go back to
        //url would be /WEB-INF/destination.jsp

        String myUrl = "postPage.jsp";
        try
        {
          //redirect back to new URL with new data
          request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception e)
        {
          request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }

      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/postPage"))
    {

      boolean loggedIn;
      if (session.getAttribute("loggedIn") == null)
      {
        loggedIn = false;
      }
      else
      {
        loggedIn = (boolean) session.getAttribute("loggedIn");
      }

      String myUrl;

      if (loggedIn)
      {
        myUrl = "/WEB-INF/createPost.jsp";
      }
      else
      {
        session.setAttribute("loginStatus", "");
        myUrl = "login.jsp";
      }

      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/logout"))
    {
      session.setAttribute("loggedIn", false);
      String myUrl = "login.jsp";
      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/submitComment"))
    {
      System.out.println("inserting comment");
      String postId = request.getParameter("postId");
      System.out.println(postId);
      String dbName = "ForumDB";
      String tableName = postId + "Comments";
      String[] columnNames =
      {
        "content", "dateCreated", "poster",
      };

      String username = request.getParameter("user");
      String content = request.getParameter("commentInput");

      System.out.println(username);
      System.out.println(tableName);
      System.out.println(content);

      JavaDatabase objDb = new JavaDatabase(dbName);
      objDb.addComment(tableName, username, content);

      String myUrl = "postPage.jsp";
      String[] commentColumnNames =
      {
        "content", "dateCreated", "poster"
      };
      System.out.println("should be getting comments now");
      String commentsTableName = postId + "Comments";
      session.setAttribute("commentTable", commentsTableName);

      System.out.println(commentsTableName);
      ArrayList<ArrayList<String>> commentData
        = objDb.getData(commentsTableName, commentColumnNames);
      System.out.println(commentData);
      session.setAttribute("comments", commentData);
      try
      {
        //redirect back to new URL with new data
        request.getRequestDispatcher(myUrl).forward(request, response);
      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    else if (userPath.equals("/deletePost"))
    {
      System.out.println("DLEETING POST");
      try
      {
        //bring from main method whatever is tested
        //dbinfo
        String dbName = "ForumDB";
        String tableName = "Posts";
        String[] columnNames =
        {
          "postId", "title", "content", "dateCreated", "poster", "category"
        };

        JavaDatabase objDb = new JavaDatabase(dbName);
        String postId=request.getParameter("postId");
        objDb.deletePost(tableName, postId);
        System.out.println("deleted post?");
        


      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
      
      try
      {
        //bring from main method whatever is tested
        //dbinfo
        String dbName = "ForumDB";
        String tableName = "Posts";
        String[] columnNames =
        {
          "postId", "title", "content", "dateCreated", "poster", "category"
        };

        JavaDatabase objDb = new JavaDatabase(dbName);
        //questions
        ArrayList<ArrayList<String>> questionData
          = objDb.getCategoryData(tableName, columnNames, "Question");
        System.out.println(questionData);
        session.setAttribute("questionData", questionData);

        //discussions
        ArrayList<ArrayList<String>> discussionData
          = objDb.getCategoryData(tableName, columnNames, "Discussion");
        System.out.println(discussionData);
        session.setAttribute("discussionData", discussionData);

        //showcases
        ArrayList<ArrayList<String>> showcaseData
          = objDb.getCategoryData(tableName, columnNames, "Showcase");
        System.out.println(showcaseData);
        session.setAttribute("showcaseData", showcaseData);

        ArrayList<ArrayList<String>> announcmentData
          = objDb.getCategoryData(tableName, columnNames, "Anncouncement");
        System.out.println(announcmentData);
        session.setAttribute("announcementData", announcmentData);
        //url to go back to
        //url would be /WEB-INF/destination.jsp
        String myUrl = "/WEB-INF/discussionsPage.jsp";
        try
        {
          //redirect back to new URL with new data
          request.getRequestDispatcher(myUrl).forward(request, response);
        }
        catch (Exception e)
        {
          request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }

      }
      catch (Exception e)
      {
        request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
      }
    }
    
    
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo()
  {
    return "Short description";
  }// </editor-fold>

}