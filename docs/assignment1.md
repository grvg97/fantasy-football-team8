# **Assignment 1**

# Introduction

**Authors:** Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

**Overview :**

The main Idea of the game is to allow the user to create multiple teams consisting of real life football players from the same real life leagues. The user will then be able to enter the match statistics of each player in their team once they have played their weekly real match. The game will compute points based on the statistics of each player in the team and tally the total points, assigning the points as the total points one team have earned in the current week. The points accumulated by a team is the most important factor in winning the game as this will decide which team wins a league or tournament.

Furthermore, each team created by a player comprises a minimum of 11 and maximum 16 players (11 starters and 5 bench). To begin with, the user starts with an empty team, is given a fixed amount of 100 credits upon the creation of the team and the user may spend the credits in any manner they wish.The players purchased may vary in prices depending on their quality, also the players will have been assigned the same playing position (e.g. midfield, defender, goalkeeper or forward) as they have in real life, the positions of these players will be maintained within the game.

In addition, the user will be allowed to create their own private leagues and have the created teams compete against one another in a scheduled tournament, the winner will be the team with the most points at the end of the tournament, this team will receive rewards.

The systems of the game we are implementing  are originally inspired by the [EPL Fantasy Football](https://fantasy.premierleague.com/).

**Targeted users:**

Although knowledge in football or the league involved does not affect the usability of our system, we expect that users with better knowledge in the sport, players and league involved will have an advantage. Nevertheless, this game is targeted to any online game enthusiasts who have an interest in creating a team to compete against other users worldwide.



**Rules:**

Below we have given the basic rules of the game which are necessary in order for the user to participate and in order for the game to work:

1. A team must consist of at least 1 goalkeeper, 4 defenders, 3 midfielders and 3 forwards.
2. Team must have at least 11 (starting) players and may have no more than 16 total players
3. All players must  be placed in their corresponding positions
4. Teams can no longer be modified when the deadline(Friday 7pm) reaches. After Monday 6pm the users can edit their teams again.
5. Teams will be given an initial credit of 100, the user may spend the 100 credits as they wish
6. Each team is given 1 free transfer every week, further transfers will cost 4 points from the accumulated team points, the user may make any number of transfers as long as it does not violate credit limit and team points.
7. Bench players will receive half points, captains will receive double points.



**Player Market system:**

We will assign the initial pricing of the players available in the game marketplace based on Fantasy Premier League&#39;s market. Initially we will manually assign all values ourselves. Eventually, we&#39;ll use the Fantasy Premier League API for bonus.

**Possible Extensions:**

Our main extensions with regard to the project track will be the ability to get real time data based on matches being played and calculate the resulting points into the user&#39;s team based on the performance of each player, by using the Fantasy Premier League API. Furthermore, by using this API, we can automatically adjust the prices of each players in the market, based on the ICT Index (mentioned in marketplace system section).

[https://readthedocs.org/projects/fpl/downloads/pdf/latest/](https://readthedocs.org/projects/fpl/downloads/pdf/latest/)

[https://fantasy.premierleague.com/api/bootstrap-static](https://fantasy.premierleague.com/api/bootstrap-static/)

In addition, we will also adjust the price of the players weekly, based on the number of points they have received (performance) in the previous match week.

Furthermore, multiplayer support can be added to the original game in order for multiple users to compete against each other by creating their own personal teams and joining private leagues.

# Features

**Authors:** Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

**Feature Requirements**

| **ID** | **Short name** | **Description** |
| --- | --- | --- |
| F1 | Login User | The System shall ask for the username and password and will grant access upon verification or reject access to the game. |
| F2 | Create Teams | Users shall be able to one or more teams choosing players from different teams and/or leagues. |
| F2.1 | Add player stats | Users shall be able to manually add players&#39; statistics and their values based on live games played by the real players. [https://fantasy.premierleague.com/statistics](https://fantasy.premierleague.com/statistics) |
| F2.2 | Fetch player stats from API | The System shall fetch player statistics from the premier league fantasy API. |
| F3 | Modification Deadline | The game shall restrict the user from making any further changes (transfers, subtitutions, captain assignments) after the deadline of 7pm has been reached. |
| F4 | Points computation | System shall compute points based on the statistics of the players that the user has manually entered or that the system fetched from the API. Taking into account if the player is a starter, bench or a captain. In the table below, we have listed all the possible ways in which a player of a team can receive their points: |

| **Event** | **Points awarded** |
| --- | --- |
| Player plays at least 45 minutes in a match | +1 |
| Player plays more than 60 minutes in a match | +2 |
| Forward Scores a goal | +3 |
| Midfielder scores a goal | +4 |
| Defender or Goalkeeper scores a goal | +5 |
| Goalkeeper makes 3 saves | +1 |
| Goalkeeper saves penalty | +5 |
| Penalty miss | -2 |
| Clean Sheet by a defender or goalkeeper | +4 |
| Own goal | -3 |
| Player receives yellow card | -1 |
| Player receives red card | -3 |



| F5 | Initial credits | The game shall assign a fixed initial number of credits; which is 100 credits. This holds for all the new teams the user creates. |
| --- | --- | --- |
| F6 | Transfer Players | Users shall be able to buy and sell players as long as it does not violate conditions such as credit limit, and points limit (see rule 6 for reference) |
| F7 | Squad quantity restriction | The system shall restrict the player to having a squad that consists of a minimum of 11 players and a maximum of 16 players. |
| F8 | Team quantity restriction | The system shall restrict the user to having a team of at least 1 goalkeeper, 4 defenders, 4 midfielders and 3 forwards. |
| F9 | Captain assignment | The system shall force the user to assign exactly one captain and vice captain for their team. The system shall allow the user to pick a captain and vice captain of their choice. |
| F10 | Vice captain promotion | The system shall promote the vice captain automatically to captain in the case that the captain of the team is injured |
| F11 | Player trading | Users shall be able to trade a maximum of one player per week on the market. |
| F12 | Create or join a league | The user shall be able to create/join a league of their choice. During creation the user can choose out of two different league types.  Classic scoring: Users score points each week. At the end of the season the player with the highest score wins. Head to Head: Each week two players in a league play against each other. Whoever gets the most points wins. The player at the end with the most wins; wins the league. |
| F13 | League tournament | Users shall be able to play tournaments in a league with other teams. |
| F14 | League winner | The Game shall be able to compute the winner in a league based on points. This is done by comparing the points between each teams with the highest points being the winner of the league |
| F15 | Print league results | The Game shall display the league results to the user upon request from the user. The league results will consist of the names of different teams in order of their standing, the current matchweek and the points of each team. |

**Quality requirements**

**Authors:** Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

| **ID** | **Short name** | **Quality Attribute** | **Description** |
| --- | --- | --- | --- |
| QR1 | User experience | Usability | Users should be able to run, fully interact (input data, modify data, create teams and leagues, buy players, visualize results, etc.) and exit the game without experiencing any software malfunction, significant delay or complete crash. |
| QR2 | Storage | Reliability | System shall keep the file where all data is kept locally in Json format. This shall happen whenever the user makes a change in any stat. This enables the user to not lose his/her data based on the game. Json file for each user. |
| QR3 | Display team information | Responsiveness | When the user wants to see how the team and the players are doing,the game shall display team and player information upon a specific command given by the user and the system will show the requested  information within 1 second. |
| QR4 | Instantaneous Results | Responsiveness | When all the match statistics of the players are provided by the user, the computed points of the team shall be available within 1 second. |
| QR5 | Up-to-date information | Availability | The game shall be able to provide information to users based on the up-to-date data stored in local file. As soon as new data is entered, the file gets updated and the newly stored information becomes available. The system shall fetch statistics from the API every 1 minute **(Extension)** |
| QR6 | Expandable | Maintainability | The game shall be easily expanded in terms of adding new teams, adding new players to teams, creating new leagues and inserting player statistics. |

**Java libraries**

Authors: Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

| **Name** | **Description** |
| --- | --- |
| [Gson](https://github.com/google/gson) | Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. |
| [Time4J](https://github.com/MenoData/Time4J) | This library will help us with the scheduling of the real time matches, as well as our league scheduling system. The library provides necessary time stamps for us to fetch current times necessary to match with our deadline settings. Preventing the user from modifying their teams after a specified time. |
| [JavaFX](https://openjfx.io/)   | We will be using this library as our main GUI. We have chosen this above the others due to the fact that the design of the GUI is clean and very clear to navigate around as well as the extensive number of resources online. |
| [FXGL](https://almasb.github.io/FXGL/) | Potential extension to our JavaFX GUI, we can use this library to give a better display of the team contents, i.e. the players in a football field view with their respective real life jerseys, positioning and names. |
| [JFoenix](http://www.jfoenix.com/) | We will use JFoenix to construct a basic GUI for our game. We have chosen this GUI as a contingency plan. This is because the GUI provides clear and concise instruction on how to implement the necessary buttons, windows etc. However, we believe that JAVAFX has more useful materials online. |
