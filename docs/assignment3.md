# Assignment 3

Maximum number of words for this document: 18000

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.    

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Summary of changes of Assignment 2
Author(s): `name of the team member(s) responsible for this section`

Provide a bullet list summarizing all the changes you performed in Assignment 2 for addressing our feedback.

Maximum number of words for this section: 1000

### Application of design patterns
Author(s): `name of the team member(s) responsible for this section`

`Figure representing the UML class diagram in which all the applied design patterns are highlighted graphically (for example with a red rectangle/circle with a reference to the ID of the applied design pattern`

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

Example code for design pattern 1:
```java
PlayerMarket playerList;
// The following line will print all the names in playersList
playerList.forEach(e -> System.out.prinln(e.getFullName));
```

| ID  | DP2  |
|---|---|
| **Design pattern**  | Singleton Creational Design Pattern |
| **Problem**  | **Database** class being instantiated in userSignUpWindow class and in LoginWindow class for the use of its operations *userAut()* and *add()* where these operations acesses the **Database** Arraylist attributes *leagues* and *users*  |
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

The UML class Diagram we have included in this document is the closest representation of the codes that we have implemented to create our system. The class diagram is the most crucial diagram in terms of setting the standards for consistency between all UML diagrams and representing the structure of our classes, their attributes and their methods. From the class diagram, one will be able to understand the relationships between different components of our systems in order for them to cooperate with one another to embody the system itself.    

  

Much like the object diagram and unlike the sequence and state-machine diagrams, the class diagram is a static representation of the system, it does not show how states of objects change but shows which classes have the rights to alter the states of another and the dependencies between all parts of the system, meaning that if one part of a system changes state, the parts dependent on it will do so as well.   

  

Moreover, within our class diagrams we have included *associations*, which represents whether one class has access to another class's attributes or operations. Some *associations* will be unidirectional indicated with an arrow pointing to one side of the member, this means that the class that the *association* is pointing to does not have access to the attributes and operations of the class at the other end of the *association*.  

  

In addition, we have also included *aggregations* denoted by a black filled diamond or a white unfilled diamond with an edge coming out of the diamond which connects to another class. The black filled diamond denotes that there is a *composition* relationship between one class to another, the white unfilled diamond represents a *shared aggregation* relationship, the end of the member with the diamond represents the member that is composed of the member of the other end. Included in these relationships are the multiplicities of the classes.   

  

Interface realizations (denoted by dashed line and white arrowhead) were also included in the diagram in order for us to represent the design patterns that we have included in our implementation of the system. We have also added dependencies that we deem essential in order to convey the implementation of the Graphical User interface of our system as well as the design patterns.   

  

Please note that we have not included a class **HandleError** since it is seen as a helper class to our Graphical user interface in order to display error messages upon certain conditions of the games. We understand that for the sake of consistency in our diagrams, it is better to include classes that will be referred further in the following UML diagrams. However, with the case of **HandleError**, the class just provides an explicit way to write an error message which need not to be referred explicitly in all of our UML diagrams. As advised by our supervisors we have also omitted getters and setters that does not contain much functionality. Through this we want to ensure that we do not transfer the complexity of our codes to the Class Diagram that is supposed to be the closest abstraction to the actual representation of our code. 

  

  

![Imgur](https://i.imgur.com/oRAEidq.png?1) 

  

**Figure1 -** Fantasy Soccer Class Diagram 

  

### **Class:** 

  

**-Team:** This class is a private class inside the User class. The client code can&#39;t access or edit the team class without calling public methods from the User class. We have made all of the attributes and operations of this class private encapsulated as well in order to ensure that the class is decoupled from the client code as much as possible. By doing this, we have hidden the implementation of an entire class to the user, decreasing the complexity of our code significantly and allowing the module of the system that is responsible for the greatest amount of functionalities that is given to the user to be deep. Due to the fact that this part of the system is the one that the user of the system interact with (**User** class providing link to deeper functionalities hidden in a private class **Team** ), we strived on building a simple User interface with deep functionalities as reflected in the **Team** class Implementation. 

### **Team Attributes:** 

  

1. *-id: int* - Stores id of the team, assigned as equal to the id of the player when a player creates a team. 

  

2. *-name: String* - Stores the name of the team given by the user. 

  

3. *-players: Player[11]* - Stores the starting lineup of 11 players. 1 goalkeeper, 4 defenders, 3 midfielders, 3 forwards.  

  

4. *-bench: Player[4]* -  Consists of 4  additional players of any position.  

  

5. *-totalPoints: Integer* - Stores the total points of all the players combined. This attribute is used when constructing the ranking of the league.  

  

6. *-captainId: int* - Stores the id of the player that is assigned as the captain of the team, we have included this attribute as the captain have the opportunity to receive double points when collecting weekly points. 

  

7. *-viceCaptainId: int* - Stores the id of the player that is assigned as the vice captain of the team, the vice captain will automatically become the captain in the event that the captain is injured. 

  

  

  

  

### **Team Operations:**  

1. *-Team(name: String, id: int)* - private constructor for the class **Team** since the class is a private inner class within the **User** class, an instant of this class can only be created within the **User** or **Team** class, the parameters represent the name that the user has chosen for the team and the id of the team (assigned same value as id of User). Making this constructor private makes sense as a Team can only exist within the User class. Since a Team cannot exist without a user (entity who instantiates the **Team** object). 

  

2. *-UpdateTeamAndBench(playerMarket: PlayerMarket)* - This method updates the statistics of every player in the team by invoking a method from the **PlayerMarket** class *getPlayers()* which will ensure that the statistics of the players within the team is consistent with the statistics of the same players in **PlayerMarket** which is in direct relation to the API. This method takes a **PlayerMarket** object as a parameter in order to invoke its method. This method is very essential in order for the game to progress and for players to receive the points that players have gained or lost which is connected to the following method below. 

  

3. *-getRoundPoints()* - This method collects the points that every player in the team have collected after every weekly game have been played in realtime, this collected points will be displayed as the team's accumulated points for the season. Our reason for the implementation of this method is to ensure that the points of the team is not the total accumulated  points of every players in the team since the beginning of the season. We highly considered the scenario of users signing up to the game in the midst of the season.  

4. *-addPlayer(player: Player)* - This method is invoked by the **User** method *buyPlayer()*, this method handles the adding of the player into either the team's *players* list attribute (starting lineup), or the team's *bench* list attribute (bench of team) if the starting lineup is full, once a player is purchased from the *PlayerMarket* by the **User** method *buyPlayer()* which handles the deduction of the credits accordingly form the **User** class. We have separated the two tasks from two classes under the consideration of good seperation of concern, since the **credits** attribute belongs to the **User** and the **Ployer** objects are stored within the **Team** class, we wanted to make sure that both classes are only altering their own attributes. Having the method in *User* class invoke this method allows for the task altering the contents of the team and the credits of the user to be done concurrently while separating the implementation, thus providing a simpler interface. 

  

5. *-removePlayer(player: Player)* -This method is invoked by the **User** method *sellPlayer()*. This method handles the removal of players from either the team's *players* or *bench* list attributes, once a player is sold. For the same reason as *addPlayer()* we have seperated the removal of the players from the team and the return of  *User* credits once a player is sold off to the market (managed by *sellPlayer()*) to 2 different methods.   

  

6. *-assignCaptainId(in captain: Player)* - Assigns the selected *Player* as the captain of the team by fetching the *Player* specified in the method parameter, this player will receive double points every week upon the event that the method *getRoundPoints()* is invoked. This method is invoked by the **User** method *pickCaptain()* to seperate the implementation from the interface that the user has access to.  

  

7. *-assignViceCaptainId(in viceCaptain: Player)* - Assigns the selected *Player* as the captain of the team by fetching the *Player* specified in the method parameter, this player will have the opporutnity to become the captain in the case that the captain is injured. This method is invoked by the **User** method *pickViceCaptain()* for the same reason as the method above  

  

8. *-contains(player: Player): boolean* - this method is invoked when the **User** method *buyPlayer()* is invoked, this method handles the checking of existing players within the team in comparison to the parameter of *buyPlayer()*, this ensures that no duplicates are allowed to be in the same Team, which is an essential part of the game. We have implemented this in the *Team* class rather than in the method *buyPlayer()* to ensure that the encapsulation of the *Team* class attributes are not broken by a public method of another class 

  

9. *-changeStartingLineup(benchPlayer: Player, starterPlayer: Player )* - This operation handles the physical switching of a starting players and a bench player given as specified in the parameter of the methods. The user will invoke this method through the method *changePlayers()* in the **User** class. 


### **Class:**

**User** - The user class represents the person who will be playing the game, once an authorized user has logged into the game. The user class provides functionalities for the user to create and delete a team, leagues, viewing the players that a user has within a team, fetching the latest information on the real life player data from the API, buying and selling players from the **PlayerMarket** and many other functionalities. This Class essentially embodies the interactions that the person playing the game can have with the rest of the system. 


### **User Attributes:**

1. *id : int* - Stores the id of the user who has signed up. 

2. *username :String* - Stores the userName that is used to log in. This attribute is one of the attributes used to authenticate a person who is trying to log in to          the system. 

3. *password : String* - Represents the password that a **User** has assigned to itself while signing up to the game. This is the second attribute used to authenticate the user during logging in . 

4. *team: Team* - This attributes holds a in instant of a *Team* class, this object represents the team that the User has created and now owns, if the user has yet to create a team, this object variable will hold a null value. The variable is able to store players and their stats which is the part about a user that plays the game. Refer to the *Team* class to see the internal structure of the object. 

5. *competedLeagues: League[\*]* - This attribute stores the leagues that the user competes in. When the user wants to join or exit a league, the required operations will be performed on the competedLeagues attribute.

6. *hasTransferred: int* - This attribute is initially false and won&#39;t be changed while the user is creating its team. This attribute will prevent the user from transferring players whenever he/she wants. 

7. *credits: int* - The user will have initially 1000 credits. The user will use these credits to buy players while creating a team at the beginning of the game. 


### **User Operations:**

1. *createTeam(): Team* - This operation allows the user to create a new team, given that the _team_ array in the user object is empty. Indicating that the user does not already have a team. Returns an object of type **Team.**

2. *deleteTeam()* - Deletes the team in the _team_ attribute of the User, given that there is one. 

3. *createLeague( name: String, manager: User, start: Date): League* - This operation allows the user to create a custom league where the creator of the league(user) will be assigned as the manager of the league. The operation adds the created league to the competedLeagues list returns the created league. 

4. *deleteLeague(league: League)* - The user can delete the specified league if the user is the manager of that league. 

5. *joinLeague(league: League)* - The user can join the specified league and add it to its competedLeagues list. In this operation, the specified league adds the user to its &quot;competingUsers&quot; list. 

6.  *exitLeague(league: League)* - This operation allows the user to exit the league that is specified by the parameter. 

7.  *buyPlayer (player: Player)* - User buys the player if there are enough credits and then the user calls the addPlayer() operation of the team class, which adds the player to the team. 

8. *sellPlayer (player: Player)* - User sells the player after the team removes the player from the &quot;bench&quot; or &quot;players&quot; list. The user gains credits based on the sold player&#39;s credit attribute. 

9. *pickCaptains (Captain:Player, viceCaptain:Player)* - User picks the captain and the vice captain of the team and passes those values to the Team class so the team can assign the captains. 

10. *displayTeam()* - This allows the user to display its whole team. 
 


### **Class:**

**MarketPlace** - This class stores the players fetched from the api using Gson. It enables the user to buy and add the players to its team by showing the players with certain specifications.

### **MarketPlace Attributes:**

1. *players: Player[\*]* - This stores the players fetched, using a get request, from the [api](https://fantasy.premierleague.com/api/bootstrap-static/) using Gson. 

### **MarketPlace Operations:**

1. *showPlayers()* - This operation displays the players no matter which position they are from. This function is specifically used when constructing the bench players because the bench players don&#39;t have any restrictions for positions.

2. *showPlayersWithPosition (position : int)* = This operation only displays the players with the specified position. 

3. *getPlayer (id: int)* - This operation loops through all the players and returns the player with the specified id. 




### **Class:**

**League:** - This class represents a league that holds the different users who are competing in the league with their teams. Within the league class, the points of each Teams that every user owns is stored. Through this we are able to compute the ranking and display every user (manager) involved in the league in order of the total points they have attained in between the time they have joined the league to the end date of the league. 


### **League Attributes:**

1. *id*: int = Stores the id of the league and allows the created leagues to be unique. 

2. *name: String* - This attribute holds the name of the league. It is assigned when the league is created via a constructor. 

3. *manager: User* - This attribute stores the manager of the league. The manager has the ability to delete and modify the end date of this league. 

4. *teamPoints:HashMap<String,Integer>* - This stores the team names as keys and the total points of the team as values. It is used when displaying the league ranking. 

5. *startDate: Date* - Stores the creation date and time of the league. It is assigned when the league is first created by the user. 

6. *endDate: Date* - Stores the end of the league. 

7. *competingUsers: User[\*]* - Stores the users that are participating/competing in the league. 


### **League Operations:**

1. *addUser(In user: User)* - This operation adds the user to the &quot;competingUsers&quot; list and adds the user&#39;s team and team points to the teamPoints HashMap. 

2. *removeUser(In user: User)* - This operation removes the user (if the user exists in the league) from the &quot;competingUsers&quot; list and removes the user&#39;s team and team points to the teamPoints HashMap.  

3. *howRanking()* - This function displays the ranking of the teams by printing the team name and the points of that team. 

4. *setEndDate(endDate: Date)* - This function sets the end date of the league based on the desire of the league manager. 




### **Class:**

**Player** - This class represents a player. A player has many attributes and stats based on football games played every week. All of the player attributes are fetched from the [api](https://fantasy.premierleague.com/api/bootstrap-static/) and dependent on live games. 


### **Player Attributes:**

1. *id: int* - A unique integer is assigned to this attribute with the data that was fetched from the api.

2. *firstName: String, lastName: String* - A real life player&#39;s first name  and last name is assigned to this attribute with the data that was fetched from the api. 

3. *position: int* - The position of the player is stored in this attribute as an integer. The representation of the integers: 1 Goalkeeper, 2 Defenders, 3 Midfielders, 4 Forwards 

4. *isAvailable: String* - This attribute stores if the player is available -\&gt; &quot;a&quot; or not available(injured) -\&gt; &quot;i&quot;. An injured player can&#39;t play in a game. Therefore the injured player can&#39;t obtain points. 

5. *realLifeTeam: int* - This attribute stores the team(one of the teams in the English Premier League) of the player in real life.

6. *statistics: String, int* - The statistics are the attributes that are fetched from the api and assigned to the attributes. All of them are private and almost all of them are used in the &quot;getStats()&quot; function. 


### **Player Operations:**

*getStats(): HashMap \&lt; String, Integer\&gt;* = This function allows the client code to retrieve the statistics of the player in a structural manner. These stats can be used to calculate the points that the player has obtained so far. 




 





### **Associations:**

1. *Directed Association from User class to League class:*

This  directed association signifies that the **User** class can access the attributes and operations of the **League** class. This is necessary due to the fact that the user will be the one creating and managing a league as shown by the operations *createLeague()* and *deleteLeague().* Moreover, the **User** must be able to access the operations of **League** because when *createLeague()* is executed, it will need to access *setEndDate()* of the **League** class in order for a user to set the duration of the league tournament that they have created. Moreover, since the user is the manager of the league as shown by the *manager* attribute of the type **User** in league, the user must be able to add and kick other users who are also participating in the league, thus strengthening the justification of this chosen association between the two classes.

2. *Team composite to User:*

This association between the **User** and **Team** class signifies that a team in our game cannot exist without a user. Therefore the **User** class will have access to all the operations and attributes of the **Team** class and a **Team** type object cannot exist without a **User** object. We have shown this in our implementation by placing **Team**  as a private class of the class **User.** Similarly to the first association mentioned, it is important for this association to be a composite as auser should be the one who creates a team, deletes a team and  manages the team in which they are a manager of.

3. *Shared aggregation between Team and Player:*

This association between the **Team** and **Player** class signifies that a player may exist without a team (not in a team) and that a team may exist without any players. This is shown by the fact that when the **User** class calls the operation *createTeam()* an instant of the **Team** is created and that instant has yet to contain any players . Moreover, a justification for this association to exist would be the fact that when a **Player** object is in the attribut *players* or *bench* in the **Team** class (signifying players being part of a squad in a team or a bench in the team), the **Team** must access the statistics of all of its players in order for *totalPoints* to be computed as well as in order for the team to recognize which players are injured and which players they have as captain; this is shown through the attributes of the **Player** class.

4. *Directed association from MarketPlace to player:*

This association signifies that the **MarketPlace** class has access to all the attributes and operations of the **Player** class. This is very important due to the fact that a market place within our game must display all of the available players and their stats in order for a **User** to purchase (*buyPLayer()* operation)  the player once they are satisfied with the statistics of the player.

The significance of this association is shown by the operations of the **MarketPlace** class and by the fact that it stores instants of the **Player** class in a list in its attribute. A necessary step in order to display all of the available players to the user of the game.



5. *Directed association from MarketPlace to GSON library:*

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


## Object diagrams								
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the description of a "snapshot" of the status of your system during its execution. 
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

`Figure representing the UML class diagram`
  
`Textual description`

Maximum number of words for this section: 1000

## State machine diagrams									
Author(s): `name of the team member(s) responsible for this section`

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

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

![Image description](https://imgur.com/OwbdXnB.png)


**Figure 6 -** Sequence diagram of the task *League Competition*


This sequence diagram represents the interaction between the classes **User**, **League**, **Team** and **Player** (represented by their corresponding lifelines). The user can join a specific league by providing the name of the league they want to join (This calls the method *joinLeague&* from **User** class). The **League** instance then executes its *addUser* method which stores the requesting user object in an ArrayList object attribute. In this way an user becomes a member of a league.

Users can also start their own leagues by issuing the **User** class method *createLeague* which takes as arguments a string with the name of the league and an instance of the class **User** as league manager. A new instance of the class **League** is returned to the user for which he is the manager. The newly created **League** instance is stored in the *competedList* array list which is an attribute of the **User** class.

The *computeRanking* method of the League class computes the League rankings based on the total points won by each team on the league. It relies on *getTotalPoints* method to retrieve the total team points. After the rankings are computed, they are reported to the user on display.

Lastly, The user can issue the *exitLeague* with reference to the league name to be withdrawn from. Once the League instance receives this message, it executes the method named *removeUser* with an User object argument. This action will remove the specified User object from the array list attribute *users*.

**DESCRIPTION**

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

## Implementation									
Author(s): `name of the team member(s) responsible for this section`

In this chapter you will describe the following aspects of your project:
- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

IMPORTANT: remember that your implementation must be consistent with your UML models. Also, your implementation must run without the need from any other external software or tool. Failing to meet this requirement means 0 points for the implementation part of your project.

Maximum number of words for this section: 2000

## References

References, if needed.
