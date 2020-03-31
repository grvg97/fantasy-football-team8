# Assignment 3

Maximum number of words for this document: 18000

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.    

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Summary of changes of Assignment 2
Author(s): `name of the team member(s) responsible for this section`

Provide a bullet list summarizing all the changes you performed in Assignment 2 for addressing our feedback.

Maximum number of words for this section: 1000

### Application of design patterns
Author(s): Mehmet Cetin, Sunny Diaram

For each application of any design pattern you have to provide a table conforming to the template below.

| ID  | DP1  |
|---|---|
| **Design pattern**  | Iterator Behavioural Design Pattern |
| **Problem**  | **PlayerMarket** class contains a list of available players (players : List\<Players\>) that are retreived from the Premier League Fantasy Football website through API calls.
| |A traditional way to iterate through the elements of this list is through a 'for' loop on the player's list, but since the number of elements this class handles is large, it makes sense to apply the Iterator behavioural design pattern.|
| **Solution**  | PlayerMarket class now uses the 'Iterable' java interface and contains an overriden iterator method which returns an iterable player list to the caller. |
| **Intended use**  | Any instance of the PlayerMarket class can now use iterable methods (e.g. forEach). See the example code snippet below this table. |
| |
| **Constraints**  | Application and implementation of the iterator java interface is pretty straightforward. |
| **Additional remarks**  | No remarks |


| ID  | DP2  |
|---|---|
| **Design pattern**  | Singleton Creational Design Pattern |
| **Problem**  | **Database** class being instantiated in userSignUpWindow class and in LoginWindow class for the use of its operations *userAuth()* and *add()* where these operations acesses the **Database** Arraylist attributes *leagues* and *users*  |
| **Solution**  | With the application of singleton creational design pattern, we are able to ensure that instead of creating a new instance of the **Database** class everytime we are acessing the attributes of the classs to check for exisiting users and to add new users, we are able to set up one global access to the class by encapsulating an instance of the class wihtin the class itself and creating a private contructor of the class to ensure that it can never be instantiated in other classes. This is highly crucial to our system due to the fact that whenever a user signs up , the *add(user)* method of the **Database** class is called and mutates the *users* array list of the class. When a user logs in the method *userAuth*  iterates through the *users* array list of the **Database** object, therefore ensuring that the two operations of the user class modifies and iterates from the same instance of the class is very important to avoid data loss.|
| **Intended use**  | At run time when the class *loginWindow* is displayed ot the user,the user enters user name and password to the available text fields and the user activates an event handler for the login button, this event handler will get an instance of the **Database** class via an operation named getInstance() since the object itself is already instanitated within the glass. Through this global access point it will then access the public method *authUser()* of the **Database** class where it will return a **User** type object to the class *.|
| **Constraints**  | Any additional constraints that the application of the design pattern is imposing, if any |
| **Additional remarks**  | Optional, only if needed |

| ID  | DP3  |
|---|---|
| **Design pattern**  | Name of the applied pattern |
| **Problem**  | A paragraph describing the problem you want to solve |
| **Solution**  | A paragraph describing why with the application of the design pattern you solve the identified problem |
| **Intended use**  | A paragraph describing how you intend to use at run-time the objects involved in the applied design patterns (you can refer to small sequence diagrams here if you want to detail how the involved parties interact at run-time |
| **Constraints**  | Any additional constraints that the application of the design pattern is imposing, if any |
| **Additional remarks**  | Optional, only if needed |

Maximum number of words for this section: 2000

## Class diagram									


Authors: Mehmet Cetin, Gilbert van Gerven, Sunny Dairam

### **Introduction:**

The UML class Diagram we have included in this document is the closest representation of the codes that we have implemented to create our system. The class diagram is the most crucial diagram in terms of setting the standards for consistency between all UML diagrams and representing the structure of our classes, their attributes and their methods. 
  

Much like the object diagram and unlike the sequence and state-machine diagrams, the class diagram is a static representation of the system, it does not show how states of objects change but shows which classes have the rights to alter the states of another and the dependencies between all parts of the system, meaning that if one part of a system changes state, the parts dependent on it will do so as well.   

  
Please note that we have not included a class **HandleError** since it is seen as a helper class to our Graphical user interface in order to display error messages upon certain conditions of the games. We understand that for the sake of consistency in our diagrams, it is better to include classes that will be referred further in the following UML diagrams. However, with the case of **HandleError**, the class just provides an explicit way to write an error message which need not to be referred explicitly in all of our UML diagrams. As advised by our supervisors we have also omitted getters and setters that does not contain much functionality. Through this we want to ensure that we do not transfer the complexity of our codes to the Class Diagram that is supposed to be the closest abstraction to the actual representation of our code. **In our system, for everytime a an outside class would like to retrieve an attribute of another class it is done so through a public getter method.** 


![Imgur](https://i.imgur.com/oRAEidq.png?1) 

  

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

<br>11. *+createLeague( name: String, manager: User, start: Date): League* - This operation allows the user to create a custom league where the creator of the league(user) will be assigned as the manager of the league. Done so by invoking the constructor of the **League**. The method also adds the league to the *leagueDatabase* of the **IOHandler** class (database of system).

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

<br>3. *-position: int* - Represents position of the player. Attribute  refers to positions indicated in the enumerator "Positions" (1= GK, 2= DEF, 3 = MID, 4 = FWD). 

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



### **Associations:**

<br>1. *Directed Association from User class to League class:*

This  directed association signifies that the **User** class can access the attributes and operations of the **League** class. This is necessary due to the fact that the user will be the one creating and managing a league as shown by the operations *createLeague()* and *deleteLeague().* Moreover, the **User** must be able to access the operations of **League** because when *createLeague()* is executed, it will need to access *setEndDate()* of the **League** class in order for a user to set the duration of the league tournament that they have created. Moreover, since the user is the manager of the league as shown by the *manager* attribute of the type **User** in league, the user must be able to add and kick other users who are also participating in the league, thus strengthening the justification of this chosen association between the two classes.

<br>2. *Team composite to User:*

This association between the **User** and **Team** class signifies that a team in our game cannot exist without a user. Therefore the **User** class will have access to all the operations and attributes of the **Team** class and a **Team** type object cannot exist without a **User** object. We have shown this in our implementation by placing **Team**  as a private class of the class **User.** Similarly to the first association mentioned, it is important for this association to be a composite as auser should be the one who creates a team, deletes a team and  manages the team in which they are a manager of.

<br>3. *Shared aggregation between Team and Player:*

This association between the **Team** and **Player** class signifies that a player may exist without a team (not in a team) and that a team may exist without any players. This is shown by the fact that when the **User** class calls the operation *createTeam()* an instant of the **Team** is created and that instant has yet to contain any players . Moreover, a justification for this association to exist would be the fact that when a **Player** object is in the attribut *players* or *bench* in the **Team** class (signifying players being part of a squad in a team or a bench in the team), the **Team** must access the statistics of all of its players in order for *totalPoints* to be computed as well as in order for the team to recognize which players are injured and which players they have as captain; this is shown through the attributes of the **Player** class.

<br>4. *Directed association from MarketPlace to player:*

This association signifies that the **MarketPlace** class has access to all the attributes and operations of the **Player** class. This is very important due to the fact that a market place within our game must display all of the available players and their stats in order for a **User** to purchase (*buyPLayer()* operation)  the player once they are satisfied with the statistics of the player.

The significance of this association is shown by the operations of the **MarketPlace** class and by the fact that it stores instants of the **Player** class in a list in its attribute. A necessary step in order to display all of the available players to the user of the game.



<br>5. *Directed association from MarketPlace to GSON library:*

The directed association means that the **MarketPlace** class has access to the json object which is provided by the **Gson** library. The **Gson** library parses the response, provided by the function fromJson() inside the **Gson** library, received from the api.


### **Object diagram**

Author(s): Mehmet Cetin, Sunny Dairam
![Image description](https://i.imgur.com/M7tLq29.png)

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



## Implementation									
Author(s): Mehmet Cetin, Sunny Dairam, Gilbert van Gerven, Ricardo Burgos



## References

[1] "UML @ Classroom: An introduction to Object-Oriented Modeling"; Seidel/Scholz/Huemer/Kappel; Springer; 2012.

[2] Course slides for Software Design 2019-2020; I. Malavolta; VU Amsterdam; 2020.
