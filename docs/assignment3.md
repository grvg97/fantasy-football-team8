# Assignment 3

Maximum number of words for this document: 18000

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.    

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Summary of changes of Assignment 2
Author(s): Gilbert van Gerven, Ricardo Burgos, Mehmet Cetin, Sunny Diaram.

The following changes were implemented based on the feedback received for the second milestone:

* The implementation now consists of two packages: **GameLogic** and **UserInterface** packages now splits functionalities for business logic and GUI handling respectively. This allows consistence among classes and improves encapsulation.
* Multiple users can join and leave an existing league, but only the league administrator is able to delete a league created
by him. This fixed the multiplicity error the feedback highlighted.
* IO and API handling is now implemented through the **IOHandler** and **HandleAPI** classes in the **GameLogic package** respectively. These two classes belongs to **GameLogic** package and splits file and API handling to new exclusive classes.
* Multiple enumerations were implemented to handle data efficiently: **Positions** and **Formation** classes in GameLogic package.
* Updated UML diagrams: Class (1), Object (1), State-Machine (2) and Sequence (2).
* Since no comments were made to Team Creation sequence diagram, it was just slightly improved.
* Sequence diagram for league competition now clearly shows the relationship with classes **Team** and **Player**.
* Sequence diagram for league competition now shows API data source as found message.
* Sequence diagram for league competition now shows a loop for adding players to fill the team.
* **Game** class was deprecated, its functionality is now assumed by other suitable classes, more specific to their roles as recommended.

### Application of design patterns
Author(s): Mehmet Cetin, Sunny Diaram


| ID  | DP1  | 
|---|---| 
| **Design pattern**  | Iterator Behavioural Design Pattern |  
| **Problem**  |<p> 1) **PlayerMarket**'s attribute *players* contains an ArrayList of **Player** objects that will be viewed in the GUI classes **TransferWindow** and **TutorialWindow** in order to graphically represent a list of the available players for purchase in the market to the stakeholder of the system. We did not have a way to standardized method of iterating through the list of **Players** that does not expose the attribute of the **PlayerMarket** class </p>. <p> 2) The **Database** class attribute *db* will store **League** and **User** type objects, since **Database** is a generalized class the *db* attribute will be typed along with the class in the **IOHandler** class where the **Database** class is used to store **Users** that have signed up and **Created** leagues. Although since *db* has an ArrayList type, we could have just iterated through the attribute of **Database** via a public method that uses an enhanced for loop. However, we could not shake off the importance in hiding the implementation of the **Database** class as much as possible since it stores sensitive information such as the *password* of a **User**.  
| **Solution**  | <p>Implement Iterable interface on the **PlayerMarket** and **Database** classes, this enables for the *iterator()* method within the **Iterable** interface to be implemented, which returns an *iterator* object for the **PlayerMarket** class. This step is achieved through polymorphism as the **Iterable** interface is a generalization and accepts any object type as a parameter for the returning of an iterator object. Moreover, the **Iterator** methods are also accessible now that we have an **Iterator**  object, gaining access to methods *hasNext()* (checks for present element in list) and *next()* (iterates to next element) as we decoupled the behaviour of the interface from the implementation. </p> <p> We truly believed that these are necessary implementations as we want our client codes to iterate through the mentioned lists via another object (**Iterator**) rather than through the encapsulating object itself, breaking the private encapsulation we have placed on our attributes*. |  
| **Intended use**  | **1) TransferWindow** and **TutorialWindow** GUI classes can now iterate through the list of players encapsulated inside the **PlayerMarket** object via an **PlayerMarket** iterator and the methods *hasNext()* to check if there is an existing element in the next index and *next()* to traverse through the list, rather than a for or enhanced for loop, exposing the internal structure and implementation of the code. This helped us a lot with the dilemma of information hiding since we needed to display the **Player** objects that are inside a private Array List attribute. Furthermore, this also greatly improved the readability of the code since we did not have to repeat code to iterate through the list of players for every time a client code needs to do so.</p><p>2) With **Database** we intend to use the iterator in the same manner as **PlayerMarket**, when listing users who are taking part in a league for example. </p> |  
| **Constraints**  | The constraint of applying this design pattern is the same as the constraint of implementing any interface. That is a client code will have to depend on methods that they may or may never use throughout their life cycle. However, in our case we have to utilize both methods of the **Iterator** interface, therefore there is not much of a constraint for us. | 
| **Additional remarks**  | No remarks | 

  

| ID  | DP2  | 
|---|---| 
| **Design pattern**  | Singleton Creational Design Pattern | 
| **Problem**  | The **IOHandler** class's methods and needs to be accessed by a great number of different outside classes as the **IOHandler** is responsible for the creation of the *userDatabase* and *leagueDatabase* where **User** objects are store after a user has signed up and successfully created a team and when a league is create by a user. Our GUI classes depends on **IOHandler** to connect to the logics of other classes (i.e. when a user logs in, signs up, creates a team, creates a league, exits a league and joins a league). The events involved the alteration of the **Database** type attributes of *IOHandler** (*userDatabase* and *leagueDatabase*), we have already solved the issue of information hiding with the iterator design pattern. Now we have the issue of ensuring that there is only one instant of *IOHandler* since we wish for our classes to access and invoke modifications the instant of databases.| 
| **Solution**  | We have made the constuctor of *IOHandler* private, preventing any instants of the class from being made. Furthermore we have created a private static signle instance class to hold a signle instance of the **IOHandler** class named *IOHandlerHolder*. Finally we made a global access method *getInstance()*, this enables any client code to make a single global reference to the class without instantiating an object of the class. We found this solution necessary as we saw the need to maintain a global state of the **IOHandler** since we want all the outside classes to be accessing the same databases that the class is in charge of for synchronization purposes. | 
| **Intended use**  | The GUI classes **LoginWindow**, **TransferWindow**, **TutorialWindow** and **UserWindow** will make reference to an instant of the **IOHandler** so that they will be able to invoke methods within **IOHandler** via the for when a user signs up, a league is created or a user exits a league or deletes a league. The **User** class will also use the singleton **IOHandler** to invoke the **add()** and **remove()** methods when the methods of the **User** class is invoked by the GUI classes (i.e. *deleteLeague* involkes *IOHandler.getInstance().remove(league)*). | 
| **Constraints**  | The main constraint of this design pattern is that when we are testing each classes or modules separately, it is very hard to isolate them and to get an idea of which other classes they have dependencies with since we have introduced a global variable in **IOHandler** and many of our classes are dependent on this singleton class* | 
| **Additional remarks**  | No remarks| 


## Class diagram									
								
Authors: Mehmet Cetin, Gilbert van Gerven, Sunny Dairam

### **Introduction:**

The UML class Diagram we have included in this document is the closest representation of the codes that we have implemented to create our system. The class diagram is the most crucial diagram in terms of setting the standards for consistency between all UML diagrams and representing the structure of our classes, their attributes and their methods. 
  
Much like the object diagram and unlike the sequence and state-machine diagrams, the class diagram is a static representation of the system, it does not show how states of objects change but shows which classes have the rights to alter the states of another and the dependencies between all parts of the system, meaning that if one part of a system changes state, the parts dependent on it will do so as well.   

Please note that we have not included a class **HandleError** since it is seen as a helper class to our Graphical user interface in order to display error messages upon certain conditions of the games . Through this we want to ensure that we do not transfer the complexity of our codes to the Class Diagram that is supposed to be the closest abstraction to the actual representation of our code. 


![Imgur](https://i.imgur.com/6QDlmxL.png?1)

  

**Figure1 -** Fantasy Soccer Class Diagram 

  

### **Class:** 

 

**-Team:** This class is a private class inside the User class. The client code can&#39;t access or edit the team class without calling public methods from the User class. We have made all of the attributes and operations of this class private encapsulated as well in order to ensure that the class is decoupled from the client code as much as possible. By doing this, we have hidden the implementation of an entire class to the user, decreasing the complexity of our code significantly and allowing the module of the system that is responsible for the greatest amount of functionalities that is given to the user to be deep. Due to the fact that this part of the system is the one that the user of the system interact with (**User** class providing link to deeper functionalities hidden in a private class **Team** ), we strived on building a simple User interface with deep functionalities as reflected in the **Team** class Implementation. 

### **Team Attributes:** 

<br>1. *-id: int* - Stores id of the team, assigned as equal to the id of the player when a player creates a team. 

<br>2. *-name: String* - Stores the name of the team given by the user. 

<br>3. *-players: Player[11]* - Stores the starting lineup of 11 players. 1 goalkeeper, 4 defenders, 3 midfielders, 3 forwards, this restriction is ensured through the enumerator **Formation** which keeps count of the players of each position in the starting lineup of the team.  

<br>4. *-bench: Player[4]* -  Consists of 4  additional players of any position.  

<br>5. *-totalPoints: Integer* - Stores the total points of all the players combined. This attribute is used when constructing the ranking of the league.  

<br>6. *-captainId: int* - Stores the id of the player that is assigned as the captain of the team, we have included this attribute as the captain have the opportunity to receive double points when collecting weekly points. 

<br>7. *-viceCaptainId: int* - Stores the id of the player that is assigned as the vice captain of the team, the vice captain will automatically become the captain in the event that the captain is injured. 

  

### **Team Operations:**  

<br>1. *-Team(name: String, id: int)* - private constructor for the class **Team** since the class is a private inner class within the **User** class, an instant of this class can only be created within the **User**  class, the parameters represent the name that the user has chosen for the team and the id of the team. Making this constructor private makes sense as a Team can only exist within the User class. Since a **Team** cannot exist without a **User**. </br>

<br>2. *-UpdateTeamAndBench(playerMarket: PlayerMarket)* - Updates the statistics of every player in the team by invoking a method from the **PlayerMarket** class (method parameter) *getPlayers()* which will ensure that the statistics of the players within the team is consistent with the statistics of the same players in **PlayerMarket** which is in direct relation to the API. 

<br>3. *-getRoundPoints()* - Collects the points that every player in the team have collected after every weekly game have been played in realtime, this collected points will be displayed as the team's accumulated points for the season. 

<br>4. *-addPlayer(player: Player)* - Invoked by the **User** method *buyPlayer()*, this method handles the adding of the player into either the team's *players* list attribute (starting lineup), or the team's *bench* list attribute (bench of team) if the starting lineup is full, once a player is purchased from the *PlayerMarket* by the **User** method *buyPlayer()* which handles the deduction of the credits accordingly form the **User** class. We have separated the two tasks from two classes under the consideration of good seperation of concern, since the **credits** attribute belongs to the **User** and the **Player** objects are stored within the **Team** class, we wanted to make sure that both classes are only altering their own attributes. 

<br>5. *-removePlayer(player: Player)* -Invoked by the **User** method *sellPlayer()*. This method handles the removal of players from either the team's *players* or *bench* list attributes, once a player is sold. For the same reason as *addPlayer()* we have seperated the removal of the players from the team and the return of  *User* credits once a player is sold off to the market (managed by *sellPlayer()*) to 2 different methods.   

<br>6. *-assignCaptainId(in captain: Player)* - Assigns the selected *Player* as the captain of the team by fetching the *Player* specified in the method parameter, this player will receive double points. This method is invoked by the **User** method *pickCaptain()* to seperate the implementation from the interface that the user has access to.  

<br>7. *-assignViceCaptainId(in viceCaptain: Player)* - Assigns the selected *Player* as the captain of the team by fetching the *Player* specified in the method parameter, this player will become the captain in the case that the captain is injured. This method is invoked by the **User** method *pickViceCaptain()* for the same reason as the method above  

<br>8. *-contains(player: Player): boolean* - Invoked when the **User** method *buyPlayer()* is invoked, this method handles the checking of existing players within the team in comparison to the parameter of *buyPlayer()*, this ensures that no duplicates are allowed to be in the same Team. We have implemented this in the *Team* class rather than in the method *buyPlayer()* to ensure that the encapsulation of the *Team* class attributes are not broken by a public method of another class . This method return a boolean with the value true if the same player from the parameter of the *buyPlayer()* method exists in the team, blocking the purchase and false otherwise.

<br>9. *-changeStartingLineup(benchPlayer: Player, starterPlayer: Player )* - Handles the physical switching of a starting players and a bench player given as specified in the parameter of the methods. The user will invoke this method through the method *changePlayers()* in the **User** class. 


### **Class:**

**User** - Represents the person who will be playing the game, once an authorized user has logged into the game. The user class provides functionalities for the user to create and delete a team, leagues, viewing the players that a user has within a team, fetching the latest information on the real life player data from the API, buying and selling players from the **PlayerMarket** and many other functionalities. <p>We have designed the class in a way that it will only gain access to the **Team** class's attributes through invoking the **Team** methods, since the user who plays the game will only be able to modify their team and the league that they have created. By seperating the implementation of the **Team** class from the **User** class we have ensured that the client code is unable to gain access to part of the systems that the user has no point in accessing and has no right in acessing; preventing future unnecessary bugs from occuring. Moreover, hiding the implementatation of the **Team** class (class which interacts with *PlayerMarket* to retrieve player statistics) as mentioned before, will ensure a simple interface with deep functionalities, meaning that the user of the system need not to deal with any of the complexity of processing their requests. </p>


### **User Attributes:**

<br>1. *-id : int* - Stores the id of the user who has signed up, upon signing up to the game (creation of new User object) the id is assigned incrementally, this was the best way we could ensure that the id is unique.

<br>2. *-username :String* - Stores the userName that is used to log in. This attribute is one of the attributes used to authenticate a person who is trying to log in to          the system.

<br>3. *-password : String* - Represents the password that a **User** has assigned to itself while signing up to the game. This is the second attribute used to authenticate the user during logging in. 

<br>4. *-team: Team* - Holds a in instant of a *Team* class, this object represents the team that the User has created and now owns, if the user has yet to create a team, this object variable will hold a null value. 

<br>6. *-credits: int* - Represents the credits of the user, once signed up, upon the creation of a new **User** object, the user is assigned with 1000 initial credits with which they are able to purchase players from the **PlayerMarket**. 

<br>7. *-hasTransferred: boolean* - Attribute represents whether the user has performed a once every week limit transferring of players (selling player from team and buying a different player from the market). Initially set to false.



### **User Operations:**

<br>1. *+User(username: String, password: String)*- Constructor for the to instantiate a *User* object, this constructor will only be used when a user successfully signs up to the game. The parameters of the contructor indicates a *username* and *password* that the user has chosen by entering and submitting into the textfields of **SignUpWindow**. Given that their inputs meets the system restrictions to be considered valid.

<br>2. *+hasTeam()*- Checks if the user's *team* attribute is a null value (indicating if user has a team or not). The purpose of this method is to help the help navigate the user to the correct GUI window uppon logging in/ signing up.Since the initial value of *team* is null, this method will return a false boolean (User signs up and **User** object created) leading the user straight to the **TutorialWindow** in order for them to create a team. Otherwise leading them to **UserWindow** to procceed with the game. 

<br>3. *+createTeam(name : String): Team* - Allows the user to create a new team, this method will only be invoked if the *team* attribute of the **User** contains a null value (user has yet to create a team or have deleted their team). This method invokes the constructor of **Team** chaining its parameter to the parameter of the **Team** contructor. This is in orer to seperate concerns as mentioned previously in the **Team** class and the description of this class.

<br>4. *+deleteTeam()* - Deletes the team in the **Team** attribute of the User, given that there is one. The method deletes the team by assigning a null value to *team*. Which then disconnects the reference to the team object, then the object will be deleted by the JVM garbage collector.

<br>5. *+buyPlayer(player: Player)* - Allows for a user to buy **Players** listed on the **PlayerMarket** taking a selected **Player** object as a parameter, the method only handles the functionality of deducting the *credits* attribute of the **User** from the *cost* of a **Player**. The method will invoke the **Team** method *contains()* and *addPlayer()* . We have considered implementing all the required functionalities to buy players into this single method but we saw an arising in the complexity of our system since there will be more dependencies tied to this single class.  


<br>6. *+sellPlayer(player: Player)* - Sells a selected player from the *team* to the **PlayerMarket** returning the *cost* of the selected player back to the *credits* of the user and removing the selected player from the team. The method only deals with the functionality of returning the *credits* accordingly to the **User** class, the removal of the **Player** object from the **Team** class is done by invoking and passing the parameter of this method to the **Team** method *removePlayer()*. 

<br>7. *+pickCaptain(player: Player)* - Allows a user to pick a selected player (parameter of this method) to be the captain of the team. This method does so by passing the paramter to the **Team** method **assignCaptain()**. This player will receive double *points*.

<br>8. *pickViceCaptain(player: Player)*- Allows a user to pick a selected player (parameter of this method) to be the Vicecaptain of the team. This method does so by passing the paramter to the **Team** method **assignViceCaptain()**. This player will be assigned to be the *captain* if the the assigned captain is injured.

<br>9. *+joinLeague(league: League)* - Allows the user to join a selected league (from the parameter). The method will invoke the method *addUser()* of the **League** object selected and passes the this **User** object as a parameter to the method. 

<br>10. *+exitLeague(league: League)* - Allows the user to exit a selected joined league (from the parameter). The method will invoke the method *removeUser()* of the **League** object selected and passes the this **User** object as a parameter to the method. 

<br>11. *+createLeague( name: String, manager: User, start: Date): League* - This operation allows the user to create a custom league where the creator of the league(user) will be assigned as the manager of the league. Done so by invoking the constructor of the **League**. The method also adds the league to the *leagueDatabase* of the **IOHandler** class (database of system). Returns **League** object and passed to parameter **IOHandler** Method *add*.

<br>12. *+deleteLeague(league: League)* - Allows the user to delete a league if they are the manager. Done so by invoking **IOHandler** method *remove()*. removing the league from the database.
 

<br>13. *+updatePlayers* - Updates the statistics of the players that are in the team. This was implemented to enable the collection of points based on new statistics of the players . Done by invoking **PlayerMarket** method *getPlayers()* and **Team** method *updateTeamAndBench()*.

<br>14. *+changePlayers(benchPlayer: Player, teamPlayer: player)*-  Switches a bench player with a starting player by invoking the  **Team** method *changeStartingLineup()*.
 


### **Class:**

**PlayerMarket** - This class acts as a container for all the players fetched from the API. The responsibility of this class is to convert the players fetched from the API by the **HandleAPI** class from JSON to **Player** object format and store it for further reference to the contents. The buying, selling, adding, removing of every players by the **Team** and **User** is done by referencing to this class. Previously, we did not have a **HandleAPI** class and did the API handling from this class too. However, due to poor seperation of concerns we have seperated the 2 tasks as we did not want all the other classes who are referring to **Player** objects to be aware of the API implementation. 

### **PlayerMarket Attributes:**

<br>1. *players: Player[ ]* - This stores the players that were converted from JSON (fetched by **HandleAPI**) to **Player** object in a list of **Player** type. 

### **PlayerMarket Operations:**
<br>1. *+iterator()* - returns an *iterator()* for the *players* attribute (explanation in Design Pattern section). Enabling the use of **Iterator** interface methods *hasNext()* and *next()*.

<br>2. *+getPlayers()* - gets players that were fetched by the **HandleApi**, returned by **HandleApi** method *getJsonObject* as a type of this class and returns it as a list of **Player** objects. Enables other classes to retrieve players from an object of **PlayerMarket** type. This design enables for the updating of players statistics, buying players, selling players, adding players to teams, removing players from the team. All operations involving **Player** objects begins with this single method. 


### **Class:**

**HandleAPI** - This class is responsible for connecting and fetching the data of players from the API [api](https://fantasy.premierleague.com/api/bootstrap-static/) using the GSON library. Without this class we are unable to get the lates statistics of all players in real time. This class is related to teh **PlayerMarker** class as it provides data for the player market to distribute to the rest of the outside classes requesting for players. The methods of this class are static as this class need not to be instantiated but just to seperate the handling of the API from the rest of the system as we do not see the point of every class being aware of the implementation of this class.

### **HandleAPI Operations:**
<br>1. *+getResponseBody(apiURL : String) : String* - This method sends a get request to the API (Url in the parameter) and fetches the JSON file from the API where information for the players of the English Premier League are stored. Then proceeds to convert the JSON to string format for further processing by the following method in this class. 

<br>2. *+getJsonObject() : playerMarket * - Invokes *getResponseBody()* and converts the return of the method to a **PlayerMarket** object, we have seperated the fetching and conversion implementation to maintain a good seperation of concern. Once returned as a **PlayerMarket ** object the **PlayerMarket** method *getPlayers* will be able to extract the seperate entries of the players and instantiate them as **Player** objects. 


### **Class:**

**League:** - This class represents a league which consists of every user who have joined the league (custom league) or have signed up to the game (global league). This class will represent the displaying of the ranking of each user's teams based on points they have gained within the league.


### **League Attributes:**

<br>1. *-id*: int = Stores the id of the league and allows the created leagues to have a unique method of reference, ensures that methods accessing the attributes refer to one unique instant of the object. 

<br>2. *-name: String* - Stores the name of the league which is set through the constructor of this class.

<br>3. *-competingUsers: User[\*]* - Stores all users competing within a particular league represented by **User** objects within a list. This attribute is the main determiner of the rankings within a league since this is how the class make reference to the attributes of a **User** in order to get the points of their team.

<br>4. *-teamPoints:HashMap<String,Integer>* - This stores the team names as keys to the amount of poi

<br>5. *-manager: String* - Stores the name of the manager of the league. This attribute is used to verify if a user is the manager of the league or not when attempting to perform modifications on the league. This is set to the name of the user who have created the league. 


### **League Operations:**

<br>1. *+League(name: String, manager: String)* - Constructor for the class, invoked by **User** method *createLeague* and receives its parameters from the method. Sets the *name* of the league to what the creator have inputted and the *manager* as the *name* attribute of the **User** that invoked the constructor. This constructor is public and is necessary for users to create a custom league. This constructor is not invoked upon the creation of the global league, since the global league is initialized with the system launch.  

<br>2. *+updateRoundPoints* - Uses an iterator to iterate through the *competingUsers* list and invokes the **User** method *getTeamRoundPoints* in order to collect the number of points every **User** has gained with their team during the week. Increments the *teamPoints* attribute in accordance to each team names with the number of points that the team gained during each week. This is necessary to update the leader board of the league.

<br>3. *+addUser(user: User)* - Adds a user (**User** object) requesting to join the league into the attribute *competingUsers*, this method is invoked and gets the value of its parameter by the **User** method *joinLeague()*. The method also adds an entry for the joined user's team and an initial value of 0 points into the **teamPoints** attribute (ensure fairness to all users).

<br>4. *+removeUser(In user: User)* - Removes user from the *competingUsers* list and deletes the entry of their team from the *teamPoints* hashMap. This method is invoked by the **User** method *exitLeague()*, representing a user exiting a league that they have joined. This method is designed in a way that a user can only exit a league that they have joined (parameter to the method exists).  

<br>5. *+showLeaderBoard()* - displays the *teamPoints* in an ordered manner in order to show the ranking of each teams based on the points that they have in the referred league.


### **Class:**

**Player** - This class represents the a player and its statistics that is fetched from the API by the **PlayerMarket** class (return type). Also stores all the attributes of a player which is necessary in order for teams to gain points. All of the methods in this class are getters and is unnecessary to represent most of them withing the class diagram as they do not provide much functionality but only gives refference to the attributes of this class. The attributes are are the main purpose of this class. 


### **Player Attributes:**

<br>1. *-id: int* - unique id of the player that was fetched from the api.

<br>2. *-firstName: String, lastName: String* - First and last name of a real life football player. 

<br>3. *-position: int* - Represents position of the player. Attribute  refers to positions indicated in the enumerator "Positions" (GK, DEF, MID, FWD). 

<br>4. *-cost: float* - the cost of the player. Used to deduct or return credits to **User** when purhcasing or selling player.

<br>5. *-realLifeTeam: int* - Stores the team(one of the teams in the English Premier League) of the player in real life.

<br>6. *-isAvailable: boolean* - False if user is injured or penalized. This attribute determines wheter a player can collect points for the team or not.

<br>7. *-totalPoints: int* - Total points of a player accumulated in the duration of the whole season. 

<br>8. *-roundPoints: int* - Weekly points of a player, this attribute changes state every week, used to increment the *totalPoints* attribute of a **Team** and the *teamPoints* attribute of a **League**

<br>9. *-minutes: int* - Minutes played by a player in the duration of the season. 

<br>10. *-goalsScored: int* - Number of goals scored by player in the duration of the season.  

<br>11. *- assists: int* - Number of goals assist by a player in the duration of the season.

<br>12. *-cleanSheets: int* - Number of clean sheets by a player (GK and DEF) in the duration of the season.

<br>13. *-goalsConceded: int* - Number of conceded goals by a player (GK and DEF) in the duration of the season.

<br>14. *-yellowCards: int* - Number of yellow cards committed by a player in the duration of the season. 1 yellow card = -1 point 

<br>15. *redCards: int* - Number of red cards commited by a player in the duration of the season. 1 red card = -3 points

<br>16. *saves: int* - Number of saves made by a player (GK).



### **Player Operations:**

*getStats(): HashMap <String, Integer>* - This method stores the statistics numbered 7 to 16 into a hashMap and gets it organized to the name of the attribute and the value of each statistics. This is the only getter function worth mentioning as the attributes it stores are the attributes that affects the number of points a player will receive after each week, thus the number of wekly points a team will receive. This method is used in **PlayerWindow** to represent these statistics of a player in an odered fashion.

### **Class:** 

**IOHandler** - Handles the creation of the databases which keeps track of leagues that have been created by a user and keeps track of the users who have signed up to the game, the class connects user input from the GUI classes to the logic classes (user logging in, signing up, creating league, joining league etc. ). We have applied singleton Design pattern on this class (Explanation in design pattern section). We have designed this class as it is due to the need to be able to take inputs from the text field of the GUI classes when users sign up and create a league. The methods of this class invokes the methods of a private inner **Database** class in order to encapsulate it from the client code as it stores sensitive information (username, password). 

  
### **IOHandler Attributes:** 

<br>1. *-userDatabase: Database <User> * - Stores a **Database** object of **User** type, meaning that the **ArrayList** type attribute in the **Database** private class will store a list of **Users**. This stores all users that have signed up to the league. Value is null at the first ever launch of system. 
  

<br>2. *-leagueDatabase: Database <League>* - Same purpose as *userDatabase* but since **Database** class is a generalization, we have changed the datatype in the parameter to fit **League** type objects. Keeps track Leagues created by user (always has Global league inside). 

  

### **IOHandler Operations:** 

<br>1. *+IOHandler()* - private constructor for the class, implemented for Singleton so that the class instant will not be instantiated. 

<br>2. *+getInstance()* - Accessor method, acts as the global access point to the class (part of singleton), enables outer classes to invoke the methods of this class.  

<br>3. *+init()* - Instantiates *userDatabase* and *leagueDatabase* attributes at the first launch of the game, and adds global league to *leagueDatabase* (by invoking **Database** method *add()*), otherwise reads the **Database** type attributes to utilize in system.  

<br>4. *+save()* - Saves the state of the *leagueDatabase* and *userDatabase* by wiriting a JSON file into a directory this file will be loaded by *init()* in the next system launch. Enables to save the state of the game (**User** attributes and **League** Attributes) since their objects are stored and their attributes determine who wins the game. 

<br>5. *+authUser(username : String, password : String)* - iterates through *userDatabase* (using iterator from **Database** class), to check if both parameters match to a **User** object stored. Represents authorizing user who is trying ot log in from the GUI class **LoginWindow** 

<br>6. *+add(user: User/ league: League)* - adds a **User** or **League** into the according **Database** attributes via overloading parameters. This method invokes the **Database** method *add()* which does the actual storing of the objects into the lists.  

<br>7. *+remove(user: User/ league: League)* - remove a **User** or **League** from the according **Database** attributes via overloading parameters. This method evokes the **Database** method *remove()* which does the actual removal of the objects from. 


### **Player Operations:**

*getStats(): HashMap <String, Integer>* - This method stores the statistics numbered 7 to 16 into a hashMap and gets it organized to the name of the attribute and the value of each statistics. This is the only getter function worth mentioning as the attributes it stores are the attributes that affects the number of points a player will receive after each week, thus the number of wekly points a team will receive. This method is used in **PlayerWindow** to represent these statistics of a player in an odered fashion.

### **Class:** 

**-Database** - This classs (Generalised) handles the physical storing of **User** and **League** objects when a user has signed up or created a league. This class is a private inner class of the **IOHandler** class. Its methods does the actual modifications to the attributes when invoked by **IOHandler** and the class type is used in the **IOHandler** class to act as a container due to its ArrayList type attribute. We have seperated thhem as we did not want the client code to break encapsulation gain access to the attributes of this class , since they store sensitive information. This class implements the **Iterable** interface, enabling it to return an iterator for itself, and enabling access to iterator methods (explanation in design pattern section).

  
### **Database Attributes:** 

<br>1. *-db: ArrayList*  - Used to store **League** and **User** object, representing the users who have singed up and leagues that have been created. The typing of this attribute is generalized to fit any datatype that is specified in the paramter. This attribute is the most crucial element of **IOHandler** and **Database** since it acts as the lowest level container that stores sensitive information.

  

### **Database Operations:** 

<br>1. *+iterator()* - returns an iterator for this class, enables the use of iterator methods next() and hasNext(), providing a standardised way of iterating through objects inside the *db* attribute (used by **IOHandler**) . 

<br>2. *-add()* - adds **User** or **League** object to the *db* attribute, this method is invoked by the **IOHandler** *add()* method implying a user has signed up to the game or a league has been created.  

<br> 3. *-remove()* -  removes either **User** or **League** class  from the *db* attribute, implying a User has de-registered from a game or a league has been deleted by its manager



### **GUI classes**

<br>**LoginWindow** - Where user signs up (private SignUpWindow) and logs into the game, this class is dependent on **IOHandler** (via global access) since it invokes the methods *userAuth()* when a user logs in and *add()** when a user signs up.

<br>**TutorialWindow** - Where users who dont have a team will create a team and pruchase players to add to the team, dependent on **IOHandler**  **PlayerMarket** and **User** since it invokes methods in those classes to view players in market, add player to the global league (stored in **IOHandler *leagueDatabase*) and to add players to the user's team.

<br>**UserWindow** - Where users view the inside of their team and create and view the leagues they manage (private **LeagueWindow** and **CreateLeague** window)). Dependent on **IOHandler**, **User** and **League** since it invokes methods to add a created league to *leagueDatabase*, fetch corresponding user from **userDatabse**, and getters to get information on a user's team and leagues a user is part of or manages.

<br>**TransferWindow** - Where users sell or buy players after passing the **TutorialWindow** stage. This class is dependent **IOHandler** to get access to corresponding **User**, dependent on **PlayerMarket** to view players in market and **User** to add or remove players from a user's team when they are bought or sold.

<br>**PlayerWindow** - Views the statistics of a selected player dependent on **Player** since it invokes *getStats* to view that values of a player's statistics stored in the  hash map.

### **Associations:**

<br>**Shared Aggregation between User and League:** - This relationship shows that a User can exist outside of a league and that a league can exist without any users. The bidirectional navigability is chosen as **User** is able to return a **League** type from the method *createLeague()* and is able to instantiate a **League**. On the other hand, **League**'s *competingUsers* list stores multiple instances of a **User** in order to represent users that have joined and become a part of the league. The multiplicites show that a User can be a part of minimum 1 league (Global League) and no maximum limit and that a league can contain no maximum limit number of players.


<br>**User directed composite of Team:** - this relationship between **Team** and **User** shows that teams cannot exist without a user since the user creates a team through the method **createTeam**. Also denoting that **Team** is a private class within ** User**, the navigability at the **Team** end shows that **User** has access to the methods of teams and may return an object of **Team** type in one of its methods. The reasoning behind this design is to ensure that a user can only create one team and that a team belongs to the user who created it. Moreover, creates a strong relationship between a team and the user that creates it. The multiplicity shows that a User may have 0 to 1 teams but 1 team maximum can be owned by a user.

<br>**Directed Shared aggregation between Team and Player:** - Indicates that a team may exists withouth players and a player may exist without a team since players exists as entities extracted from the **PlayerMarket** class and that every player in a team is just a reference to the same player from the **PlayerMarket**. The navigability at the **Player** end of the relationship shows that **Team** can access the methods and attributes of the **Player** class, this is necessary since  a player is stored as **Player** objects within the **Team** class. The multiplicity shows that 0 to a maximum of 15 players can be a part of any number of teams and that any number of teams may contain 0 to 15 maximum players. (Enumerator **Formation** keeps track of the count of the starters of 11 players). 
**PlayerMarket directed Composite of Players:** - This association shows that a player is contained in a player market, the navigability at the **Player** end is necessary as **PlayerMarket** stores a list of **Players** in itself. The multiplicity suggests that as many players must be a part of at most one player market.


<br>**Directed association from HandleApi to PlayerMarket:** - This relationship suggest that the **HandleApi** uses a type **PlayerMarket** to return from one of its methods and that **PlayerMarket** has no access to any of the **HandleApi** attributes and methods. This relationship is necessary in order to seperate the communication with the api and its implementation from the rest of the system.

<br>**Directed Association from IOHandler to User** - Necessary relationship in order for **IOHandler** to store mulitple **User** objects within its *userDatabase* Attribute. **User** has no access to any of **IOHandler**'s operations and attributes.

<br>**Directed Association from IOHandler to League** - Necessary relationship in order for **IOHandler** to store mulitple **League** objects within its *leagueDatabase* Attribute. **League** has no access to any of **IOHandler**'s operations and attributes.

<br>**IOHandler directed composite of Databse** - shows that **Database** is contained inside *IOHandler** and cannot exist without it. The Navigability at the **DatabaseEnd** is necessary in order to allow **IOHandler** to store **Database** type attributes and to gain access to the **ArrayList** attribute of **Database* to store new users and leagues.

<br>**Interface Realization between Iterable interface, Database and Player Market** - shows that these 2 classes implement a method from the **Iterable** interface to return **Iterator** objects for themselves (part of Iterator design pattern). The *Iterator()* method returns a subtype of the **Iterator** class shown as **Anonymous** since we do not know the subtype. 




## **Object Diagram**

Author(s): Mehmet Cetin, Sunny Dairam

![imgur](https://i.imgur.com/458Gijw.png)

**Figure2 -** Fantasy Football Object Diagram

This diagram represents some of the instances of the classes in the runtime of the program. The object diagram is much bigger in terms of quantity for the reason that

From the **User** class we are showing two user objects(user1, user2) with different ids, usernames, passwords, teams and competing leagues. Each user owns a team and that team can only be accessed by that user. Since every user has a team, the number of teams is equal to the number of users. Therefore, two objects(team1, team2) have been instantiated from the **Team** class. These object&#39;s attributes are assigned by the user through public functions, which makes the team object immutable to client code except for the team&#39;s user.

In the rules of the game, every team should have initially 15 players consisting of exactly 1 goalkeeper, 4 defenders, 4 midfielders, and 3 forwards in the starting line up. However, the object diagram doesn&#39;t depict all the 15 players of a team for the reason that representing all the 15 players as an object would be significantly hard in the diagram. Instead, we have made 3 player objects from the **Player** class. And these player objects can contribute to the team by gaining points when they play in real life games in the EPL and add the points that they have obtained to the total team points. This will happen if the player that has gotten the points in the real life game is also in the starting lineup in our game for the user&#39;s team. Briefly, if a player doesn&#39;t play in the starting lineup, then his points aren&#39;t added to the total points of the team, even if he has received points in a real life game.

From the **League** class we are depicting three league objects of which one of them is the global league. The other two are the custom leagues created by the user. The number of leagues in the runtime of the game is at least one and there is no upper limit because the number of custom leagues is dependent on the users since every user has the ability to create a league.

The **MarketPlace** class has only one object because in the runtime we also have one market and in the real game there is also one market. The market object serves as a pool of players where the user can purchase any player desired if he/she has enough credits to buy the player. The user can also sell players to the market and gain credits based on the sold player&#39;s cost. Moreover, player costs can change based on their real life performances in the EPL. If a player performs well, his cost will increase and vice versa is also possible.

There is one instance from the **Gson** class and within that instance the fromJson() method is used to get the responseBody and return the the **MarketPlace** class

The lines in the object diagram are the **instance specification** links between the objects have certain meanings and below we describe briefly what they mean.

The links between the players and the team means that the player is a part of the team and also in the class diagram there is a shared aggregation between the player and the team, which means that a player can exist within a team.

The link between the user and the team means that a team exists inside the user. Which also means that if there is no user there is also no team. Because a team can only exist upon the existence of a user.

Every user competes in at least one league. The link between the user object and the league object means that the user competes in that league and it also implies that the league can access its users attributes using public operations. This also means that the league stores the user&#39;s team points and ranks them among other user&#39;s team points.

There is also a relation between the market object and the players object. The meaning of the link is that the market can access all the players because the &quot;players&quot; list inside the market stores all the players that have been fetched from the api.

The market object receives the players from the api using the method of the **Gson** class. The link between the gson object and the market object specifies that the gson links the players fetched from the api call to the &quot;player&quot; list using the fromJson() method.

## **State machine diagrams**

Author(s): Ricardo Burgos Lara, Gilbert van Gerven

### **User Behavior:**

![Imgur](https://i.imgur.com/0A5CKMH.png)
** **

**Figure 3 -** User Behavioural State-Machine Diagram


The Following state machine diagram represents the different states that a **User** class object can take during the tasks of creating a new team and a new league. For this state machine we have emphasized more on the states that the **User** object can take when a new user has joined the game and creates a new team. Thus, the user object essentially represents a stakeholder of our system (the user). This is due to the fact that this is a required feature for our first minimal implementation of the Fantasy Soccer Game. The entry activity of this User state machine is to show the main menu of the game as well as load the values of the attributes of the **User** type object, where the user will be displayed with the choice of buttons linked to the operations *createLeague()* , *createTeam()*, *displayTeam()* and *deleteTeam()*  as well as having the attribute values loaded or initialized if the **User** object is new. However, due to technical issues related to the papyrus software, we were unable to display the Entry, Do and Exit activities of the state machine we represent.

To begin assuming that the **User** object has already been created. The initial state of the object leads straight away to a transition to an idle state. This idle state essentially is the process of waiting for an event to be executed by the user object. Since we are emphasizing the initial stage of the game, we will include states linked to the event caused by operations *createTeam()*,*createLeague()* and *displayTeam()*.

As soon as the *createTeam()* operation is executed it creates a transition to a *Team not created* a state where the user is asked for an input to set the name of the team. a decision node that utilizes 2 guards that checks whether a user already has a team or not. If the user does not yet have a team, the **User** object will transit to the *Team Creation* composite state. Within the *Team Creation* state, the entry activity displays the team set up page where a user is prompted to enter a name for the team, if the user chooses to cancel, the event *cancel button pressed* will trigger a transition out of the *Team Creation* state and back into the idle state of the user object without saving any progress. However, through the event of *confirm button pressed*. There is a transition in which the input that the user has given is checked, for the purpose of abstraction we did not go into detail of the input validation process where we check if the user has typed an input of a *teamName* that does not yet belong to another team. If the input is rejected, a transition via decision node back to *Team Not Created state* occurs. Otherwise, a transition to *Team Created* occurs, where the user&#39;s input is used in the constructor *new Team (teamName,manager)*. This state instantiates the **Team** class, which is a private class in the **User** class. The constructor parameters will set the input that the user has given as the name of the team and the user itself as the manager. Upon reaching the final state, a **Team** object is created, therefore a team is created, the team creation view is closed via Exit activity and a transition to the *Team Management* state occurs. 

The *Team Management* composite statecan be accessed via the transition events of finishing the *Team Creation* state as well as via the operation *displayTeam().* Due to the **Team** class being a private class inside **User,** this state involves operators and attributes of both classes, thus the complexity of the composite state requires it to be represented as another state machine, we have left it on external view in this state machine, we will explore the internal transitions infigure 4.

Finally from the *idle state,* the even *createLeague()* will trigger a transition to the *League Creation* compositestate. This state, similarly to the *Team Creation* state, is responsible for asking the user for an input on the name of the league they wish to create as well as validating the name, and the creation of a new custom league via the constructor, as shown in its sub-state *League Created.*

Overall, the **User** state machine is responsible for the initial steps that a user must take in order to reach eligibility in participating in the Fantasy Soccer game. As shown through creation of a Team and the optional step of creating a custom league. Moreover, the **User** state machine provides a platform for the user to make further modifications to the team as shown when the Userobject enters the *Team Management* state via the event *displayTeam()*.

### **Team Management Behavior:**

![Imgur](https://i.imgur.com/NHARq59.png)
** **

**Figure 4 -** Team management Behavioural State-Machine Diagram



The **Team Management** behavioral state-machine represents the **Team** class that is a private class inside the **User** class. This state-machine is responsible for the steps of filling a team with players of appropriate positions and in accordance with the conditions that creates an eligible team as mentioned in **Feature 7** of our **Feature Requirements.** Moreover, the state-machine is responsible for the deduction or addition of points when a player is purchased or sold.  When a player is sold, this state machine will do the necessary steps to remove the player from a team and when a player is successfully purchased; to add the player accordingly into the team.

To enter this state machine, the event *displayTeam()* must have occurred, this can occur from the idle state (through pressing a button) or through the *Team Creation* state when the state reaches the final state. The *Entry* activity of the **Team Management**  state machine is to display the team view, which shows details about a team that the user have created from the **User** state machine; such as players, the manager name, the captain of the team etc . The *Exit* activity is to close the team view and save the progress of the user&#39;s modification of the team (save the values of the **Team** Attributes and **User** object credits). Due to technical difficulties with papyrus we were only able to display the Entry and Exit activities via the external view of the state machine as shown in **Figure 3.**

As the **User** object enters the state machine *Team Management* The initial state immediately transitions to the *Initialize* state, where an entry activity involving the retrieval of the **User** object&#39;s *credits* attribute value and all the values of the **Team** object&#39;s attributes is performed. The completion of the Entry activities creates a completion event that causes a transition via a decision node to *Team Complete* and *Team not Complete* states. The guards of the transition edges of the decision node checks whether the number of total players in the team has reached 15 or not. The state *Team Complete* represents a state in which a **Team** has less exactly 15 players, disabling the transition to *Player Purchase* composite state. The *Team not complete* state represents a state in which a **Team** has less than 15 total players, enabling a transition to *Player Purchase* state. The reason why we have included these states is to set the *complete* attribute of a **Team** object accordingly as this Boolean attribute represents whether a team is eligible to collect points (participate) in the playing phase of the game.

Once the *Entry* Activities of the *Team Complete* and *Team* not complete states are completed, the completion event will cause a transition to the *Idle* state, where we wait for the events *buyPlayer()* and *sellPlayer()* to occur, upon the command of the user of the system or the event *exit button pressed* where a user exits the team view and the **Team Management** state.

Upon the event *buyPlayer()* the transition to the *Player Purchase*  composite state occurs. This transition is protected by a guard that checks whether the team is full or not; preventing the transition if so. The *Player Purchase* state represents the state of the **Team Management** state machine where the cost of the player is deducted from the user&#39;s credits and where the player is added to the **Team&#39;s** players or bench attributes. Once the state is entered the activities *get player info* is executed, which enables the *buyPlayer()* operations to get the cost of the player and perform the necessary deduction of **User** credits. The initial pseudostate of this composite state leads to a transition involving 2 decision nodes to check if the player cost is lower than the amount of credits the user has and to check if there are too many players of the same position as represented by the guards. These guards will prevent or allow for the transition to *Player Purchased* state to occur, where a *do* activity of the state will deduct the cost of the player being purchased from the credits attribute of the **User** object.  Upon prevention, a transition will occur where an error message is shown when the user does not have enough credits or there are too many instances of the player with the same position inside a team. This transition will exit the *Player Purchase composite* state via an exit point, leading to the *idle* state.

Once the activity of the *Player Purchased* state is done, the completion event will execute the event *addPlayer()* which is an operation of the **Team** private class. This event leads to a decision node with 2 possible transitions as outputs with guards that checks whether the bench or starters spot of a team is full, since the team cannot be complete in order to enter the composite state in the first place, this transition will lead to one of the states: *Player Added to Starters* or *Player Added to Bench*. These 2 sub-states will either add the purchased player to the starting sport or bench spot of a team. Denoted by appending a **Player** object to the *players* attribute of the **Team** object or to the *bench* attribute of the **Team** object. The completion state of either these 2 states will lead to a transition to the final state of the composite state. *Exit* activity will close the market display as the purchase is successful and a transition back to the *Initialize* state occurs.

The event *sellPlayer()* does not require a guard as the operation involved must have a player that is in either the *bench* or *players* attribute of the **Team** object in order to execute. The event leads to a transition to the composite state *Player Sale*.This composite state is responsible for adding credits back to the **User&#39;s** credit attribute when their player is sold and removing the player accordingly from either the *bench* or players attribute.

Entering the *Player Sale* state will perform the *Entry*activity of getting the info of theplayer involved from the **Team** object. This enables the **User** object to fetch the cost of the player when executing the *sellPlayer()* operation. Furthermore, the initial state immediately leads to a transition to the *Player Sold* sub-state where a do activity of adding points based on the **Player** cost back to the *credits*attribute of the **User** performed. The completion event of this state leads to the transition involving the event *removePlayer()*

*removePlayer()* leads to a decision node with guards that checks if the player involved is a captain or not. If the player is a captain, a transition to the sub-state *Vice Captain Promoted* occurs, where the captain attribute of the **Team** object is assigned with the value of the *viceCaptain* attribute. The completion event of this state leads to the state *Player Removed* where the player is removed from the team. If the player is not a captain , the transition of the decision node will lead straight to the *Player Removed State* . The completion event of this state will then lead to a transition to the final state of the *Player Sale* composite state, leading to a transition out of the composite state and back to *Initialize* state.

Notice that the *Initialize* state of this state-machine is very essential as the values of the *credits* attribute of a **User** object and the values of the *bench or team* attributes of the **Team** object will be changed by either of the composite states in the state-machine upon completing either of their internal states. Therefore it is necessary to retrieve the value of the **Team** attributes and the **User** credit attribute to  re-evaluate the boolean *complete* so that upon eventually reaching the *idle* state again, the events *buyPlayer()* and *sellPlayer()* will occur with the correct conditions in place and the credit values will always be computed correctly.

## Sequence diagrams									
Author(s): Gilbert Van Gerven &amp; Ricardo Burgos Lara

### **Sequence Diagram: Team Creation**

The following diagram depicts the process of creating a team and populating it with players from the market.

![Image description](https://imgur.com/s2UHmLl.png)
** **
**Figure 5 -** Sequence diagram of the task *Team Creation*

The sequence in Figure 5 shows the interaction between the game main class and the classes **User**, **Team**, **Market** and **Player** (represented by their corresponding lifelines).

*Team creation and player selection:*

After user successful login, an instance of the class User is created. The first action the user must perform is to create a new team by calling the *createTeam* method. An empty team is created for the user with no players and 1,000 initial credits are awarded to the newly created team; this team is stored as one of the User class attributes. To populate a team with players, a request message from the user to the team is executed (by calling *buyPlayer* method with the player&#39;s id as argument), a validation of the user&#39;s credits takes place and if the credit&#39;s are enough to buy the requested player (this is represented by the 'alt' combined fragment), then the transaction is granted; otherwise a message is returned to inform the user of insufficient credits (lower operand) and user state returns to idle.

The transaction takes place between the classes **Team**, **Market** and **Player**. Once the transaction is validated, credits are deducted and the instance of Team sends a message to the Market class (*addPlayer*), which is populated with all the players from all teams in the league. From its side, the **Market** instance uses its method *getPlayer(playerID)* to retrieve the requested player based on its unique identification code from a list of available instances of the class **Player** which were automatically fetched from the Premier League&#39;s website API, this list is stored as an instance attribute list named &#39;players&#39;. The Market finally returns the requested player object to the team, where it is stored as one element in an array list of either active (on field) or bench players.

*Team points collection:*

Moreover, the user is also capable of requesting a total point count to the team (by adding player's points), by sending the message *getTeamTotalPoints()*. The team instance, in turn, forwards this message to all of its players (by iterating the players and bench Array Lists and asking them to report their points (using the getTotalPoints method) and adding them together. The result is returned back to the user from the team instance since it is the return value for the first called method *getTeamTotalPoints*.

Lastly, the user can also delete their own team by calling the *deleteTeam()* method. The selected players and total points won by the team are lost.

*GUI integration:*

When the program is executed, a login screen appears asking for user name and password.
If the user is already registered on the system, successful authentication will lead to the User Window; if
authentication fails, the login screen appears again and if the users clicks on the sign up button the
sign up window will show up, in which the user will create a new account by providing a user name and
password.

Next, after user account creation, the Tutorial Window is displayed. This is the GUI interface
for team creation and players selection which is represented within the 'alt' combined fragment
shown on Figure 6.

Lastly, Tutorial and User Windows both shows the team's total points collected so far, and by selecting a player
on the list will display player's statistics window which also display the total points collected by that specific player.

### **Sequence Diagram: League Competition**

![Image description](https://imgur.com/HnTAWbJ.png)


**Figure 6 -** Sequence diagram of the task *League Competition*


This sequence diagram represents the interaction between the classes **User**, **League**, **Team** and **Player** (represented by their corresponding lifelines). The user can join a specific league (created already) by providing the name of the league they want to join (This calls the method *joinLeague* from **User** class). The **League** instance then executes its *addUser* method which stores the requesting user object in an ArrayList object attribute. In this way an user becomes a member of a league.

Users can also start their own league by issuing the **User** class' method *createLeague* which takes as argument a string with the name of the league; a new instance of the class **League** is returned to the user for which he/she is the manager. The newly created **League** instance is stored in the *competedList* array list which is an attribute of the **User** class.

The *computeRanking* method of the League class computes the League rankings based on the total points won by each team on the league. It relies on *getTotalPoints* method to retrieve the total team points. After the rankings are computed, they are reported to the user on display.

Lastly, The user can issue the *exitLeague* with reference to the league name to be withdrawn from. Once the League instance receives this message, it executes the method named *removeUser* with an User object argument. This action will remove the specified User object from the array list attribute *users*.

*GUI integration:*

![Image description](https://i.imgur.com/u0TXyPh.png)

**Figure 7 -** GUI screen showing available leagues (right pane) and options

The figure above shows the UserWindow class from which the user can perform operations over leagues (opening, creation and deletion).

## **Implementation**

Author(s): Mehmet Cetin, Sunny Dairam, Ricardo Burgos Lara

- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

In this chapter you will describe the following aspects of your project:

### **The strategy we followed when moving the UML models to the implementation code**

We started off making the models of the classes and the associated class diagram. We then implemented them in Java adjusting our models iteratively along the way.

**Class User**

We implemented the class user first and added all the attributes and operations that were already in the class diagram. After that we implemented the operations that were connected in order to implement the feature that was given from the first assignment (feature 2). After that we implemented the features in the order of 6, 11.1, 12 and 1. The implemented features were connected to each other.

**Class League:** We have coded the private class league inside the user class.

**Class MarketPlace**

We coded the MarketPlace class and added the attributes and the operations that were already in the class diagram. After that, we have implemented the operations that were connected with feature 2 and the other functions that we have implemented. We have also converted the json string to a json object and stored it in the marketplace class in the &quot;players&quot; list. We used that list for the user to store

**Class League**

The League class was implemented by adding attributes and the operations that were already in the class diagram. After that, we have implemented the operations that were connected with feature 2 and the other functions that we have implemented. We add users to the global league using the addUser() function which is one of the core functions that we have used when implementing the features.

### **Key solutions applied when implementing the system**

### **Logging in/ registering the user**

The user can login and register by inputting a username and password. If the username is not presented in the User Database the user instead gets added and registered. This problem was solved by the _key solution_ of converting an object to a json file using the fromJson() from the **Gson** library and storing that file locally inside the database directory.

In a class called **Game**, a classs with the main purpose of launching all the necessary class operations in order, when the user enters the game for the first time, a new Json file is instantiated in a specific directory. In order to identify someone as a new user we have a class under the name of **UserDatabase** which contains a setter and getter method *addUser(user)* and *authUser(String Username,String Password)*, *authUser* will attempt to get a an entry in the Json file with the matching User name and password, as it finds out that no such entry exists, the *addUser()* method will be run and adds a User object in Json form to Json object that is in a specified path (created dynamically at the first runtime of the system). If the user have already logged in previously, it will authenticate the user upon the confirmation of an existing (user,password) entry in the created Json file.

### **Getting the list of Premier League Players from the API**

We get all the players and their stats from the API link: [https://fantasy.premierleague.com/api/bootstrap-static/](https://fantasy.premierleague.com/api/bootstrap-static/)

We fetched the json string using a get request from the HttpUrlConnection library from java.net. After getting the json string, we used the Gson library&#39;s fromJson function to convert the given json string to a json object; which in this case is the MarketPlace class.

### **User creating the team**

The user is able to create a team apon registering himself. While the team is being constructed the user is asked for a team name and then gets a list of players presented to him. Then, the user can choose between the players based on their position which is in the order of goalkeeper, defender, midfielder and forwards. After the user creates its team, the team will be added to the global league automatically.

### **The League and User Databases**

We have 2 separate databases in our system. The purpose of this is for the information of the game to be maintainable. This makes the game a local multiplayer game so keeping track of all users and leagues is a must. In the user database we store a list of all users that are registered which are using our system. In the league database we store all created leagues of the system including the global league, which is initially inside the league database file

**The location of the main Java class needed for executing our system is found at:**

fantasy-football-team8\src\main\java\Main.java

**The location of the jar file to directly execute the system :**
fantasy-football-team8\out\artifacts\software\_design\_vu\_2020\_jar\software-design-vu-2020.jar

**A 30-second video showing the execution of our system:**

[https://www.youtube.com/watch?v=3i73DpzInhc](https://www.youtube.com/watch?v=3i73DpzInhc)



## References

[1] "UML @ Classroom: An introduction to Object-Oriented Modeling"; Seidel/Scholz/Huemer/Kappel; Springer; 2012.

[2] Course slides for Software Design 2019-2020; I. Malavolta; VU Amsterdam; 2020.
