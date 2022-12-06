# Public-Library

<h2>Project Description</h2>
A booking system has been developed for a public library. The system should be able to function as a record keeper of the books owned by the library as well as a service console for both 

**Major Features:**
- Searching and locating books for staff and library users
- Sorting books based on predefined categories
- Checking out books
- Keeping the track of inventory 
- Log-in System
- Admin panel

<h2> **New Features** </h2>
<h3>Feature 1- Book to TV Series Search</h3>
Hollywood often gets inspiration from books, and users will be able to explore that. This feature will allow users to search for a book that they may have read or are interested in reading, and the results will show whether or not the book has become a TV show. Information includes the genre, average runtime, average rating, the date the show first premiered, and the description of the show. Google Books API was implemented. 

<h3>Feature 2- Book Description</h3>
Before a user decides to check out a book, they have the opportunity to see the description of the book within the system. This way, they can find out if they could be interested in the storyline. TVMaze API was implemented. 

<h3>Feature 3 (minor)- Jokes</h3>
Start the day with some humor! Every time a user logs in, a joke will appear at the top of the dashboard. The JokesAPI was implemented. 

<h2> How The GUI Works</h2>

JavaFX: We utilized the JavaFX public library and its companion program, Scene Builder, to create the visual design of the graphic interface. The library utlizes java classes and XML files to showcase the screens.

Screens: Each screen has a java class which operates its functions and methods and variables. The attached XML file will include the code for how the screen ought to look. JavaFX methods were used to switch between screens and showcase alerts. These classes take care of reading inputs of the user in the form of buttons and text fields.

<h2>How The SQL Datbase is Designed - Relational Model</h2>
<img width="949" alt="Screen Shot 2022-11-08 at 1 13 08 PM" src="https://user-images.githubusercontent.com/65127296/200677982-6cfa3b41-b80d-44b8-abc9-c4262b82163f.png">

<h2>How the Functionalities Operate</h2>
<ul>
  <li><strong>Log In:</strong> many systems may need a simple log in function to have a wall of security behind the system</li>
  <li><strong>Check Out:</strong> whenever objects are assigned to user’s (such as orders, check outs, and buy outs) this function can help assign an object in a database to a user
</li>
  <li><strong>Return:</strong> systems which expan upon the check out and involve a form of return will use this method to return the ‘taken out’ object back into the inventory within the database
</li>
  <li><strong>Search:</strong> the search method is broad and may be utilized by any system that wishes to get result sets from a SQL database
</li>
  <li><strong>Create Users:</strong> any system that has users with distinct data sets has to be able to create new users, thus making use of our method which creates an object and saves its data into an SQL table
</li>
</ul>

<h2>How To Compile and Run The Code</h2>
At the moment, you can clone the repository onto your device. Once you have access to the directory, load it on the IntelliJ IDE platform and run the "Login.java" file from the "BookManagementProject" folder. MySQL will also need to be running locally with the database set.
