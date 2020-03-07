#
# Assignment 2

**Implemented features**

The following table consists of all the feature requirements that we will be implementing in this stage of our development. To assist us in making a decision of which features to implement we have utilised the **MoSCoW**  methodology and have categorized our features into different levels of priorities. Furthermore, the features that are listed below includes all of the &quot;must have&quot; **(M)** features that are highly necessary in order to shape the foundation of our system and give the user and the developers a definite idea of how the final product of our game would look visually. Additionally, we have added some of the &quot;should have&quot; features **(S),** in order for us to test the functionalities that our game would have when it is at a minimal working stage. The features below are sorted in the level of priority in which we will implement them.

| **ID** | **Short name** | **Description** |
| --- | --- | --- |
| F1 | Login User | The System shall ask for the username and password and will grant access upon verification or reject access to the game.  **Motivation:**  This allows some form of data protection within the game, so that users cannot meddle with other user&#39;s teams. |
| F2 | Create Team | The user shall be able to create one empty/non empty team that joins the global league automatically. Every team will be assigned 1000 initial credits. Users shall be able to buy players from the market with the given initial credits as long as it satisfies Feature 7 and does not exceed the initial credits stated in Feature 6.  **Motivation:** This allows the users to choose which players they would like to include in their team. Which is the main decision making part of the game that determines which users will gain more points. |
| F6 | Initial credits | The game shall assign a fixed initial number of credits; which is 1000 credits. This holds for all the new teams the user creates.  **Motivation:** Everybody has an equal opportunity of creating a team based on their own decisions. |
| F11.1 | League tournament | The system shall automatically add teams to the Global League upon the team&#39;s creation. All teams joining a league start off with zero points for that league.   **Motivation:** This is the process in which teams can receive points based on the performance of the players in their team, thus allowing the system to rank each team in the global league and in separate outside leagues. Moreover, since every team is automatically in a global league, this allows for the global league to accumulate the points of each team from the point of creation to the end of season. These accumulated points will be the value of the team&#39;s overall points. |
| F12 | Display league rankings | The Game shall display the league ranking to the user upon request from the user. The league ranking will consist of the names of different teams in order of their standing, the current match week and the points of each team.  **Motivation:** This allows the user to see how their  team is ranked against other teams in their respective league. |

**Used modeling tool**

For our UML diagrams we have used Papyrus, although we faced numerous technical difficulties with the software due to instability involving the graphics, it was still much prefered than draw.io due to the completeness of the nodes and edges that the software provides. We have briefly mentioned every limitations caused by the technical difficulties from papyrus in our UML diagrams that prevented us from expressing the correct UML notations that we wanted to express.

[Link to Papyrus](https://www.eclipse.org/papyrus/download.html)

**Class diagram**

Authors: Mehmet Cetin, Gilbert van Gerven, Sunny Dairam

**Introduction**

The class diagram that we have shown in the figure above represents the associations between all of the classes that our game system comprises. Through the associations of the classes we have a clearer picture on how the operations and attributes connect with one another. Moreover, we can also see the dependencies that some classes have to another class. For instance, the fact that the **Team** class has a shared aggregation with the player class as the team class requires instants of the **Player** class in order to buy players and fill the team with players.

Furthermore, through compositions, the class diagram illustrates the classes whose existence solely depends on another class. Such as the fact that a Team does not exist without a User.

From all the associations, generalizations and derivations of the class diagram we can see that there are separate components that are connected and functioning as one unit; thus the very definition of a system.

![Image](https://i.imgur.com/gzAGy7o.png)

**Figure1 -** Fantasy Soccer Class Diagram

| **Class** |
| --- |
| **User** | The user class represents the person who will be playing the game, once an authorized user has logged into the game. The user class provides functionalities for the user to create and delete a team, leagues, as well as allowing the user to proceed to viewing the internal structure of the team that they have created (team view with players). This class is essentially the starting point of the game or the startup menu before entering the game itself. |
| **Attributes** |
| _id : int_ | Stores the id of the user. |
| _username :String_ | Stores the userName that is used to log in. When the user is logging in the user can play the game with its own team. |
| _password : String_ | If the username and the password matches a specific user the  user will be granted access to the game with that specific user. |
| _team: Team_ | This attribute holds the team object of the user so the user can access any attribute of its team using public functions. Every user can have only one team. |
| _competedLeagues: League[\*]_ | This attribute stores the leagues that the user competes in. When the user wants to join or exit a league, the required operations will be performed on the competedLeagues attribute. |
| _hasTransferred: int_ | This attribute is initially false and won&#39;t be changed while the user is creating its team. This attribute will prevent the user from transferring players whenever he/she wants. |
| _credits: int_ | The user will have initially 1000 credits. The user will use these credits to buy players while creating a team at the beginning of the game. |
| **Operations** |
| _createTeam(): Team_ | This operation allows the user to create a new team, given that the _team_ array in the user object is empty. Indicating that the user does not already have a team. Returns an object of type **Team.** |
| _deleteTeam()_ | Deletes the team in the _team_ attribute of the User, given that there is one. |
| _createLeague( __name: String,_ _manager: User,__ start: Date__): League_ | This operation allows the user to create a custom league where the creator of the league(user) will be assigned as the manager of the league. The operation adds the created league to the competedLeagues list returns the created league. |
| _deleteLeague( __league: League__ )_ | The user can delete the specified league if the user is the manager of that league. |
| _joinLeague( __league: League__ )_ | The user can join the specified league and add it to its competedLeagues list. In this operation, the specified league adds the user to its &quot;competingUsers&quot; list. |
| _exitLeague( __league: League__ )_ | This operation allows the user to exit the league that is specified by the parameter. |
| _buyPlayer ( __player: Player__ )_ | User buys the player if there are enough credits and then the user calls the addPlayer() operation of the team class, which adds the player to the team. |
| _sellPlayer ( __player: Player__ )_ | User sells the player after the team removes the player from the &quot;bench&quot; or &quot;players&quot; list. The user gains credits based on the sold player&#39;s credit attribute. |
| _pickCaptains (Captain:Player,__viceCaptain:Player)_ | User picks the captain and the vice captain of the team and passes those values to the Team class so the team can assign the captains. |
| _displayTeam()_ | This allows the user to display its whole team. |
|   |





| **Class** |
| --- |
| **MarketPlace** | This class stores the players fetched from the api using Gson. It enables the user to buy and add the players to its team by showing the players with certain specifications. |
| **Attributes** |
| _players: Player[\*]_ | This stores the players fetched, using a get request, from the [api](https://fantasy.premierleague.com/api/bootstrap-static/) using Gson. |
| **Operations** |
| _showPlayers()_ | This operation displays the players no matter which position they are from. This function is specifically used when constructing the bench players because the bench players don&#39;t have any restrictions for positions. |
| _showPlayers__WithPosition (__position : int)_ | This operation only displays the players with the specified position. |
| _getPlayer (__id: int)_ | This operation loops through all the players and returns the player with the specified id. |







| **Class** |
| --- |
| **League** | This class represents a league that holds the different users who are competing in the league with their teams. Within the league class, the points of each Teams that every user owns is stored. Through this we are able to compute the ranking and display every user (manager) involved in the league in order of the total points they have attained in between the time they have joined the league to the end date of the league. |
| **Attributes** |
| _Id: int_ | Stores the id of the league and allows the created leagues to be unique. |
| _name: String_ | This attribute holds the name of the league. It is assigned when the league is created via a constructor. |
| _manager: User_ | This attribute stores the manager of the league. The manager has the ability to delete and modify the end date of this league. |
| _teamPoints:_ _HashMap\&lt;String,Integer\&gt;_ | This stores the team names as keys and the total points of the team as values. It is used when displaying the league ranking. |
| _startDate: Date_ | Stores the creation date and time of the league. It is assigned when the league is first created by the user. |
| _endDate: Date_ | Stores the end of the league. |
| _competingUsers:__User[\*]_ | Stores the users that are participating/competing in the league. |
| **Operations** |
| _addUser(In user: User)_ | This operation adds the user to the &quot;competingUsers&quot; list and adds the user&#39;s team and team points to the teamPoints HashMap. |
| _removeUser(In user: User)_ | This operation removes the user (if the user exists in the league) from the &quot;competingUsers&quot; list and removes the user&#39;s team and team points to the teamPoints HashMap.  |
| _showRanking()_ | This function displays the ranking of the teams by printing the team name and the points of that team. |
| _setEndDate( __endDate: Date__ )_ | This function sets the end date of the league based on the desire of the league manager. |



| **Class** |
| --- |
| **Player** | This class represents a player. A player has many attributes and stats based on football games played every week. All of the player attributes are fetched from the [api](https://fantasy.premierleague.com/api/bootstrap-static/) and dependent on live games. |
| **Attributes** |
| _id: int_ | A unique integer is assigned to this attribute with the data that was fetched from the api. |
| _firstName: String; __lastName:__ String_  | A real life player&#39;s first name  and last name is assigned to this attribute with the data that was fetched from the api. |
| _position: int_ | The position of the player is stored in this attribute as an integer. The representation of the integers:1 -\&gt; Goalkeeper, 2 -\&gt; Defener, 3 -\&gt; Midfielder, 4 -\&gt; Forward |
| _isAvailable:_ _String_ | This attribute stores if the player is available -\&gt; &quot;a&quot; or not available(injured) -\&gt; &quot;i&quot;. An injured player can&#39;t play in a game. Therefore the injured player can&#39;t obtain points. |
| _realLifeTeam:__int_ | This attribute stores the team(one of the teams in the English Premier League) of the player in real life.   |
| _statistics: String, int_ | The statistics are the attributes that are fetched from the api and assigned to the attributes. All of them are private and almost all of them are used in the &quot;getStats()&quot; function. |
| **Operations** |
| _getStats():_ _HashMap \&lt;__String, Integer\&gt;_ | This function allows the client code to retrieve the statistics of the player in a structural manner. These stats can be used to calculate the points that the player has obtained so far. |



| **Class** |
| --- |
| **-Team** | This class is a private class inside the User class. The client code can&#39;t access or edit the team class without calling public methods from the User class. |
| **Attributes** |
| _id: int_ | Stores unique integer |
| _name: String_ | Stores the name of the team given by the user. |
| _players: Player[11]_ | Stores the starting lineup of 11 players. 1 goalkeeper, 4 defenders, 3 midfielders, 3 forwards. |
| _bench: Player[4]_ | Currently consists 4 players of any position. Later on there may be additional restrictions on the positions of the players. |
| _captain: Player_ | Stores the captain of the team picked by the user and assigned by the team. |
| _viceCaptain: Player_ | This attribute stores the second captain of the team. The second captain becomes the primary captain if the captain gets injured. The vice captain is selected in the same manner as the captain. |
| _totalPoints: Integer_ | Stores the total points of all the players combined. This attribute is used when constructing the ranking of the league. |
| **Operations** |
| _addPlayer(__player: Player)_ | Adds the player to the players list if the players list&#39;s size isn&#39;t 11 yet. Else, this function adds the player to the bench list. |
| _removePlayer(__player: Player)_ | Removes the player from the players list. This function is used in combination with sellPlayer. In order for the user to sell the player, the player first has to be removed from the team. |
| _assignCaptains(__in captain: Player, in viceCaptain: Player)_ | Assigns the captains, which is selected by the user, to the captain and viceCaptain attribute. |
| _isComplete(): Boolean_ | This operation returns true if the total players in the team is at least 11. This function The system restricts the team from receiving any points unless the team has 11 players.  |
| _changeStarters_ | This operation changes the starting lineup by replacing some of the players with the bench players. |

**Associations**

1. **1)****Directed Association from User class to League class:**

This  directed association signifies that the **User** class can access the attributes and operations of the **League** class. This is necessary due to the fact that the user will be the one creating and managing a league as shown by the operations _createLeague()_and _deleteLeague()._ Moreover, the **User** must be able to access the operations of **League** because when _createLeague()_ is executed, it will need to access _setEndDate()_ of the **League** class in order for a user to set the duration of the league tournament that they have created. Moreover, since the user is the manager of the league as shown by the _manager_ attribute of the type **User** in league, the user must be able to add and kick other users who are also participating in the league, thus strengthening the justification of this chosen association between the two classes.

1. **2)****Team composite to User:**

This association between the **User** and **Team** class signifies that a team in our game cannot exist without a user. Therefore the **User** class will have access to all the operations and attributes of the **Team** class and a **Team** type object cannot exist without a **User** object. We have shown this in our implementation by placing **Team**  as a private class of the class **User.** Similarly to the first association mentioned, it is important for this association to be a composite as auser should be the one who creates a team, deletes a team and  manages the team in which they are a manager of.

1. **3)****Shared aggregation between Team and Player:**

This association between the **Team** and **Player** class signifies that a player may exist without a team (not in a team) and that a team may exist without any players. This is shown by the fact that when the **User** class calls the operation _createTeam()_ an instant of the **Team** is created and that instant has yet to contain any players . Moreover, a justification for this association to exist would be the fact that when a **Player** object is in the attribut _players_ or _bench_ in the **Team** class (signifying players being part of a squad in a team or a bench in the team), the **Team** must access the statistics of all of its players in order for _totalPoints_ to be computed as well as in order for the team to recognize which players are injured and which players they have as captain; this is shown through the attributes of the **Player** class.

1. **4)****Directed association from MarketPlace to player:**

This association signifies that the **MarketPlace** class has access to all the attributes and operations of the **Player** class. This is very important due to the fact that a market place within our game must display all of the available players and their stats in order for a **User** to purchase (_buyPLayer()_ operation_)_  the player once they are satisfied with the statistics of the player.

The significance of this association is shown by the operations of the **MarketPlace** class and by the fact that it stores instants of the **Player** class in a list in its attribute. A necessary step in order to display all of the available players to the user of the game.



1. **5)****Directed association from MarketPlace to GSON library:**

The directed association means that the **MarketPlace** class has access to the json object which is provided by the **Gson** library. The **Gson** library parses the response, provided by the function fromJson() inside the **Gson** library, received from the api.

**       **

**Object diagram**

Author(s): Mehmet Cetin, Sunny Dairam
![Image description](https://i.imgur.com/sSJklWs.png)

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

**State machine diagrams**

Author(s): Ricardo Burgos Lara, Gilbert van Gerven

**User Behavior:**

 ![Image description](https://i.imgur.com/M7tLq29.png)
**Figure3 -** User Behavioural State-Machine Diagram

The Following state machine diagram represents the different states that a **User** class object can take during the tasks of creating a new team and a new league. For this state machine we have emphasized more on the states that the **User** object can take when a new user has joined the game and creates a new team. Thus, the user object essentially represents a stakeholder of our system (the user). This is due to the fact that this is a required feature for our first minimal implementation of the Fantasy Soccer Game. The entry activity of this User state machine is to show the main menu of the game as well as load the values of the attributes of the **User** type object, where the user will be displayed with the choice of buttons linked to the operations _createLeague() , createTeam(), displayTeam() and deleteTeam()_  as well as having the attribute values loaded or initialized if the **User** object is new_._ However, due to technical issues related to the papyrus software, we were unable to display the Entry, Do and Exit activities of the state machine we represent.

To begin assuming that the **User** object has already been created. The initial state of the object leads straight away to a transition to an idle state. This idle state essentially is the process of waiting for an event to be executed by the user object. Since we are emphasizing the initial stage of the game, we will include states linked to the event caused by operations createTeam(),createLeague() and displayTeam().

As soon as the _createTeam()_ operation is executed it creates a transition to a _Team not created_ a state where the user is asked for an input to set the name of the team. a decision node that utilizes 2 guards that checks whether a user already has a team or not. If the user does not yet have a team, the **User** object will transit to the _Team Creation_ composite state. Within the _Team Creation_ state, the entry activity displays the team set up page where a user is prompted to enter a name for the team, if the user chooses to cancel, the event _cancel button pressed_ will trigger a transition out of the _Team Creation_ state and back into the idle state of the user object without saving any progress. However, through the event of _confirm button pressed_ **.** There is a transition in which the input that the user has given is checked, for the purpose of abstraction we did not go into detail of the input validation process where we check if the user has typed an input of a _teamName_ that does not yet belong to another team. If the input is rejected, a transition via decision node back to _Team Not Created state_ occurs. Otherwise, a transition to _Team Created_ occurs, where the user&#39;s input is used in the constructor _new Team (teamName,manager)_. This state instantiates the **Team** class, which is a private class in the **User** class. The constructor parameters will set the input that the user has given as the name of the team and the user itself as the manager. Upon reaching the final state, a **Team** object is created, therefore a team is created, the team creation view is closed via Exit activity and a transition to the _Team Management_state occurs. _ _

The _Team Management_ composite statecan be accessed via the transition events of finishing the _Team Creation_ state as well as via the operation _displayTeam()._ Due to the **Team** class being a private class inside **User,** this state involves operators and attributes of both classes, thus the complexity of the composite state requires it to be represented as another state machine, we have left it on external view in this state machine, we will explore the internal transitions infigure 4.

Finally from the _idle state,_ the even _createLeague()_ will trigger a transition to the _League Creation_ compositestate. This state, similarly to the _Team Creation_ state, is responsible for asking the user for an input on the name of the league they wish to create as well as validating the name, and the creation of a new custom league via the constructor, as shown in its sub-state _League Created._

Overall, the **User** state machine is responsible for the initial steps that a user must take in order to reach eligibility in participating in the Fantasy Soccer game. As shown through creation of a Team and the optional step of creating a custom league. Moreover, the **User** state machine provides a platform for the user to make further modifications to the team as shown when the Userobject enters the _Team Management_ state via the event _displayTeam()._

**Team Management Behavior:**

![Image description](https://i.imgur.com/uRhVxAM.png)
** **

**Figure 4 -** Team management Behavioural State-Machine Diagram



The **Team Management** behavioral state-machine represents the **Team** class that is a private class inside the **User** class. This state-machine is responsible for the steps of filling a team with players of appropriate positions and in accordance with the conditions that creates an eligible team as mentioned in **Feature 7** of our **Feature Requirements.** Moreover, the state-machine is responsible for the deduction or addition of points when a player is purchased or sold.  When a player is sold, this state machine will do the necessary steps to remove the player from a team and when a player is successfully purchased; to add the player accordingly into the team.

To enter this state machine, the event _displayTeam()_ must have occurred, this can occur from the idle state (through pressing a button) or through the _Team Creation_ state when the state reaches the final state. The _Entry_ activity of the **Team Management**  state machine is to display the team view, which shows details about a team that the user have created from the **User** state machine; such as players, the manager name, the captain of the team etc **.** The _Exit_ activity is to close the team view and save the progress of the user&#39;s modification of the team (save the values of the **Team** Attributes and **User** object credits). Due to technical difficulties with papyrus we were only able to display the Entry and Exit activities via the external view of the state machine as shown in **Figure 3.**

As the **User** object enters the state machine _Team Management_The initial state immediately transitions to the _Initialize_ state, where an entry activity involving the retrieval of the **User** object&#39;s _credits_ attribute value and all the values of the **Team** object&#39;s attributes is performed. The completion of the Entry activities creates a completion event that causes a transition via a decision node to _Team Complete_ and _Team not Complete_ states_._ The guards of the transition edges of the decision node checks whether the number of total players in the team has reached 15 or not. The state _Team Complete_ represents a state in which a **Team** has less exactly 15 players, disabling the transition to _Player Purchase_ composite state. The _Team not complete_ state represents a state in which a **Team** has less than 15 total players, enabling a transition to _Player Purchase_ state. The reason why we have included these states is to set the _complete_ attribute of a **Team** object accordingly as this Boolean attribute represents whether a team is eligible to collect points (participate) in the playing phase of the game.

Once the _Entry_ Activities of the _Team Complete_ and _Team_ not complete states are completed, the completion event will cause a transition to the _Idle_ state, where we wait for the events _buyPlayer()_ and _sellPlayer()_ to occur, upon the command of the user of the system or the event _exit button pressed_ where a user exits the team view and the **Team Management** state.

Upon the event _buyPlayer()_ the transition to the _Player Purchase_  composite state occurs. This transition is protected by a guard that checks whether the team is full or not; preventing the transition if so. The _Player Purchase_ state represents the state of the **Team Management** state machine where the cost of the player is deducted from the user&#39;s credits and where the player is added to the **Team&#39;s** players or bench attributes. Once the state is entered the activities _get player info_ is executed, which enables the _buyPlayer()_ operations to get the cost of the player and perform the necessary deduction of **User** credits. The initial pseudostate of this composite state leads to a transition involving 2 decision nodes to check if the player cost is lower than the amount of credits the user has and to check if there are too many players of the same position as represented by the guards. These guards will prevent or allow for the transition to _Player Purchased_ state to occur, where a _do_ activity of the state will deduct the cost of the player being purchased from the credits attribute of the **User** object.  Upon prevention, a transition will occur where an error message is shown when the user does not have enough credits or there are too many instances of the player with the same position inside a team. This transition will exit the _Player Purchase composite_ state via an exit point, leading to the _idle_ state.

Once the activity of the _Player Purchased_ state is done, the completion event will execute the event _addPlayer()_ which is an operation of the **Team** private class. This event leads to a decision node with 2 possible transitions as outputs with guards that checks whether the bench or starters spot of a team is full, since the team cannot be complete in order to enter the composite state in the first place, this transition will lead to one of the states: _Player Added to Starters_ or _Player Added to Bench_. These 2 sub-states will either add the purchased player to the starting sport or bench spot of a team. Denoted by appending a **Player** object to the _players_ attribute of the **Team** object or to the _bench_ attribute of the **Team** object. The completion state of either these 2 states will lead to a transition to the final state of the composite state. _Exit_ activity will close the market display as the purchase is successful and a transition back to the _Initialize_ state occurs.

The event _sellPlayer()_ does not require a guard as the operation involved must have a player that is in either the _bench_ or _players_ attribute of the **Team** object in order to execute. The event leads to a transition to the composite state _Player Sale_.This composite state is responsible for adding credits back to the **User&#39;s** credit attribute when their player is sold and removing the player accordingly from either the _bench_ or players attribute.

Entering the _Player Sale_ state will perform the _Entry_activity of getting the info of theplayer involved from the **Team** object. This enables the **User** object to fetch the cost of the player when executing the _sellPlayer()_ operation. Furthermore, the initial state immediately leads to a transition to the _Player Sold_ sub-state where a do activity of adding points based on the **Player** cost back to the _credits_attribute of the **User** performed. The completion event of this state leads to the transition involving the even removePlayer().

_removePlayer()_ leads to a decision node with guards that checks if the player involved is a captain or not. If the player is a captain, a transition to the sub-state _Vice Captain Promoted_ occurs, where the captain attribute of the **Team** object is assigned with the value of the _viceCaptain_ attribute. The completion event of this state leads to the state _Player Removed_ where the player is removed from the team. If the player is not a captain , the transition of the decision node will lead straight to the _Player Removed State_ . The completion event of this state will then lead to a transition to the final state of the _Player Sale_ composite state, leading to a transition out of the composite state and back to _Initialize_ state.

Notice that the _Initialize_ state of this state-machine is very essential as the values of the _credits_ attribute of a **User** object and the values of the _bench or team_ attributes of the **Team** object will be changed by either of the composite states in the state-machine upon completing either of their internal states. Therefore it is necessary to retrieve the value of the **Team** attributes and the **User** credit attribute to  re-evaluate the boolean _complete_ so that upon eventually reaching the _idle_ state again, the events _buyPlayer()_ and _sellPlayer()_ will occur with the correct conditions in place and the credit values will always be computed correctly.



**Sequence diagrams**

Author(s): Gilbert Van Gerven &amp; Ricardo Burgos Lara:

**Sequence Diagram: Team Creation**

![Image description](https://i.imgur.com/fcO0nsR.png)

This diagram shows the interaction between the game main class and the classes User, Team, Market and Player (represented by their corresponding lifelines). After user authentication and on successful login, an instance of User is created. The first action the user must perform is to create a new team by calling the &#39;createTeam&#39; method. An empty team is created for the user with no players and 1000 initial credits are awarded to the newly created team; this team is stored as one of the class attributes. To populate a team with players, a request message from the user to the team is executed (buyPlayer with the player&#39;s id as argument), a validation of the user&#39;s credits takes place and if the credit&#39;s are enough to buy the requested player (this is represented by combined fragment with label &#39;alt&#39;), then the transaction is granted; otherwise a message is returned to inform the user of insufficient credits (lower fragment) and user state returns to idle.

The transaction takes place between the classes Team, Market and Player. Once the transaction is validated, credits are deducted and the instance of Team sends a message to the Market class (addPlayer), which is populated with all the players from all teams in the league. From its side, the Market instance uses its method getPlayer(playerID) to retrieve the requested player based on its unique identification code from a list of available instances of the class Player which were automatically fetched from the Premier League&#39;s website API, this list is stored as an instance attribute list named &#39;players&#39;. The Market finally returns the requested player object to the team, where it is stored as one element in an array list of either active (on field) or bench players.

Moreover, the user is also capable of requesting a total point count to the team, by sending the message &#39;getTeamTotalPoints&#39;. The team instance, in turn, forwards this message to all of its players (by iterating the players and bench Array Lists and asking them to report their points (using the getTotalPoints method) and adding them together. The result is returned back to the user from the team instance since it is the return value for the first called method &#39;getTeamTotalPoints&#39;.

Lastly, the user can also delete their own team by calling the &#39;deleteTeam()&#39; method. The selected players and total points won by the team are lost.

**Sequence Diagram: User - League Interaction**

![Image description](https://i.imgur.com/ZvHHmi8.png)

This sequence diagram represents the interaction between the classes User, League, Team and Player (represented by their corresponding lifelines). The user can join a specific league by providing the name of the league they want to join (This calls the method &#39;joinLeague&#39; from User class). The League instance then executes its &#39;addUser&#39; method which stores the requesting user object in an ArrayList object attribute. In this way an user becomes a member of a league.

Users can also start their own leagues by issuing the User class method &#39;createLeague&#39; which takes as arguments a string with the name of the league and an instance of the class User as league manager. A new instance of the class League is returned to the user for which he is the manager. The newly created League instance is stored in the &#39;competedList&#39; array list which is an attribute of the User class.

The &#39;computeRanking&#39; method of the League class computes the League rankings based on the total points won by each team on the league. It relies on &#39;getTotalPoints&#39; method to retrieve the total team points. After the rankings are computed, they are reported to the user on display.

Lastly, The user can issue the &#39;exitLeague&#39; with reference to the league name to be withdrawn from. Once the League instance receives this message, it executes the method named &#39;removeUser&#39; with an User object argument. This action will remove the specified User object from the array list attribute &#39;users&#39;.

**Implementation**

Author(s): Mehmet Cetin, Sunny Dairam

- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

In this chapter you will describe the following aspects of your project:

**The strategy we followed when moving the UML models to the implementation code**

We started off making the models of the classes and the associated class diagram. We then implemented them in Java adjusting our models iteratively along the way.

**Class User**

We implemented the class user first and added all the attributes and operations that were already in the class diagram. After that we implemented the operations that were connected in order to implement the feature that was given from the first assignment (feature 2). After that we implemented the features in the order of 6, 11.1, 12 and 1. The implemented features were connected to each other.

**Class League:** We have coded the private class league inside the user class.

**Class MarketPlace**

We coded the MarketPlace class and added the attributes and the operations that were already in the class diagram. After that, we have implemented the operations that were connected with feature 2 and the other functions that we have implemented. We have also converted the json string to a json object and stored it in the marketplace class in the &quot;players&quot; list. We used that list for the user to store

**Class League**

The League class was implemented by adding attributes and the operations that were already in the class diagram. After that, we have implemented the operations that were connected with feature 2 and the other functions that we have implemented. We add users to the global league using the addUser() function which is one of the core functions that we have used when implementing the features.

**Key solutions applied when implementing the system**

**Logging in/ registering the user**

The user can login and register by inputting a username and password. If the username is not presented in the User Database the user instead gets added and registered. This problem was solved by the _key solution_ of converting an object to a json file using the fromJson() from the **Gson** library and storing that file locally inside the database directory.

**Getting the list of Premier League Players from the API**

We get all the players and their stats from the API link: [https://fantasy.premierleague.com/api/bootstrap-static/](https://fantasy.premierleague.com/api/bootstrap-static/)

We fetched the json string using a get request from the HttpUrlConnection library from java.net. After getting the json string, we used the Gson library&#39;s fromJson function to convert the given json string to a json object; which in this case is the MarketPlace class.

**User creating the team**

The user is able to create a team apon registering himself. While the team is being constructed the user is asked for a team name and then gets a list of players presented to him. Then, the user can choose between the players based on their position which is in the order of goalkeeper, defender, midfielder and forwards. After the user creates its team, the team will be added to the global league automatically.

**The League and User Databases**

We have 2 separate databases in our system. The purpose of this is for the information of the game to be maintainable. This makes the game a local multiplayer game so keeping track of all users and leagues is a must. In the user database we store a list of all users that are registered which are using our system. In the league database we store all created leagues of the system including the global league, which is initially inside the league database file

**The location of the main Java class needed for executing our system is found at:**

fantasy-football-team8\src\main\java\Main.java

**The location of the jar file to directly execute the system is:** fantasy-football-team8\out\artifacts\software\_design\_vu\_2020\_jar\software-design-vu-2020.jar

_A 30-second video showing the execution of our system:_

[https://www.youtube.com/watch?v=MtKv0an2e7c](https://www.youtube.com/watch?v=MtKv0an2e7c)

**References**

References, if needed.

#
