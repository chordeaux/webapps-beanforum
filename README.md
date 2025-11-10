# Bean Forum

A web-based forum application built with Jakarta EE, featuring user authentication, post creation, comments, and multiple discussion categories.

## Features

- User registration and authentication
- Create posts in multiple categories (Questions, Discussions, Showcases, Announcements)
- Comment on posts
- User account page with post management
- Delete posts functionality

## Prerequisites

Before you begin, ensure you have the following installed:

- **NetBeans IDE** (16 or later recommended)
- **GlassFish Server 7.x** (Jakarta EE 9.1 compatible)
- **Java JDK 11** or later
- **MySQL Server 8.0** or later
- **MySQL Connector/J 8.0.25** (JDBC driver)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/chordeaux/webapps-beanforum.git
cd webapps-beanforum
```

### 2. Configure GlassFish Server in NetBeans

1. Open the project in NetBeans
2. If prompted to resolve missing server:
   - Click **Resolve Problems**
   - Select or add your GlassFish server installation
3. Or manually add server:
   - Go to **Tools → Servers**
   - Click **Add Server**
   - Select **GlassFish Server**
   - Browse to your GlassFish installation directory

### 3. Configure MySQL Connector

1. Download MySQL Connector/J 8.0.25 if you don't have it
2. Update the path in NetBeans:
   - Right-click project → **Properties**
   - Go to **Libraries**
   - Update the MySQL connector JAR path to match your system

Or manually edit `nbproject/project.properties`:
```properties
file.reference.mysql-connector-java-8.0.25.jar=C:\\path\\to\\your\\mysql-connector-java-8.0.25.jar
```

### 4. Set Up the Database

1. Ensure MySQL Server is running
2. Update database credentials (if needed):
   - Edit `JavaDatabase.java` with your MySQL username/password

3. Run the database installer:
   - Right-click `InstallDb.java` → **Run File**
   - This will create the `ForumDB` database with required tables

### 5. Build and Run

1. Clean and build the project: **Run → Clean and Build Project**
2. Run the application: **Run → Run Project**
3. The application will open in your default browser

## Project Structure

```
webapps-beanforum/
├── src/java/
│   ├── Controller.java       # Main servlet handling all routes
│   ├── JavaDatabase.java     # Database operations
│   └── InstallDb.java        # Database setup script
├── web/
│   ├── index.jsp             # Landing page
│   ├── login.jsp             # Login page
│   ├── register.jsp          # Registration page
│   ├── postPage.jsp          # Individual post view
│   ├── forumCSS.css          # Styles
│   ├── ForumJS.js            # Client-side scripts
│   └── WEB-INF/
│       ├── discussionsPage.jsp
│       ├── accountPage.jsp
│       ├── createPost.jsp
│       └── Error.jsp
└── nbproject/                # NetBeans project config (private/ is git-ignored)
```

## Database Schema

The application uses the following MySQL tables:

### UserList
- `username` (VARCHAR 50)
- `password` (VARCHAR 50)

### Posts
- `postId` (VARCHAR 10)
- `title` (VARCHAR 100)
- `content` (LONGTEXT)
- `dateCreated` (DATE)
- `poster` (VARCHAR 50)
- `category` (VARCHAR 50)

### [PostId]Comments (dynamic tables)
- `content` (LONGTEXT)
- `dateCreated` (DATE)
- `poster` (VARCHAR 50)

## Application Routes

- `/index` - Home page
- `/login` - Login page
- `/register` - Registration
- `/discussionsPage` - Main forum page with all categories
- `/accessPost` - View individual post
- `/createPost` - Create new post
- `/submitComment` - Add comment to post
- `/deletePost` - Delete a post
- `/logout` - Log out user

## Troubleshooting

### "Server classpath not correctly set up"
- Configure the GlassFish server in NetBeans: **Tools → Servers → Add Server**

### Build fails with classpath errors
- Ensure MySQL connector path is correct in project properties
- Verify GlassFish server is properly configured

### Database connection errors
- Check MySQL Server is running
- Verify database credentials in `JavaDatabase.java`
- Ensure `ForumDB` database exists (run `InstallDb.java`)

### Port conflicts (default 8080)
- Change GlassFish port in server configuration
- Or stop other applications using port 8080

## Technologies Used

- **Backend**: Jakarta Servlet API (Jakarta EE 9.1)
- **Server**: GlassFish 7.x
- **Database**: MySQL 8.0
- **Frontend**: JSP, HTML, CSS, JavaScript
- **Build Tool**: Apache Ant (via NetBeans)

## Development Notes

- Session management for user authentication
- MVC pattern with Controller servlet
- Dynamic comment tables created per post
- Password stored in plain text (TODO: implement hashing for production)

## TODO / Future Enhancements

- [ ] Implement password hashing (bcrypt/PBKDF2)
- [ ] Add input validation and sanitization
- [ ] Add search functionality
- [ ] Email verification for registration
- [ ] Rich text editor for posts
- [ ] User profile pages
- [ ] Post editing functionality
- [ ] Improvement of site visuals
- [ ] Image capabilities in posts

## Author

Dimas Deffieux
