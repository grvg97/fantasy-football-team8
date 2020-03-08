# **Assignment 1**

# Introduction

**Authors:** Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

**Overview**

The main Idea of the game is to allow the user to create multiple teams consisting of real life football players from the same real life leagues. The user will then be able to enter the match statistics of each player in their team once they have played their weekly real match. The game will compute points based on the statistics of each player in the team and tally the total points, assigning the points as the total points one team have earned in the current week. The points accumulated by a team is the most important factor in winning the game as this will decide which team wins a league or tournament.

Furthermore, each team created by a player comprises a minimum of 11 and maximum 16 players (11 starters and 4 bench). To begin with, the user starts with an empty team, is given a fixed amount of 1000 credits upon the creation of the team and the user may spend the credits in any manner they wish.The players purchased may vary in prices depending on their quality, also the players will have been assigned the same playing position (e.g. midfield, defender, goalkeeper or forward) as they have in real life, the positions of these players will be maintained within the game.

In addition, the user will be allowed to create their own private leagues and have the created teams compete against one another in a scheduled tournament, the winner will be the team with the most points at the end of the tournament, this team will receive rewards.

The systems of the game we are implementing  are originally inspired by the [EPL Fantasy Football](https://fantasy.premierleague.com/).

**Targeted users**



**Football Fans/Online Players** - our first stakeholder consists of people who are interested in playing football , are followers of the premier league,or just avid online gamers with enough interest in the sport to participate. This target user will most likely be the largest category of user we will have in our implementation of the game. The target user mentioned will have done enough research, studied the point system and the rules of the game in order to create a team and compete to be the top players in their respective leagues.

**Football Players** - The real players of the premier league are also possible stakeholders for the game. They will be able to compare themselves with other players of their position by placing themselves within a team with their respective teammates of the same position or against their opponents of the same position. Moreover, the players may also include real life bench players from their team in order to compare them to starters and perhaps help the manager in assessing who deserves more playing minutes (especially if the player is a captain or a vice captain).

**Managers of a team** - The managers of a football (premier league) can highly benefit from this game as they will be able to better assess their players in a bird eye view and with statistics that are collected weekly by our system. The managers will be able to compare their players with players of different teams and formulate possible market trades, sell or buy as well as assigning minutes to players who perform better (e.g. more average weekly points).



**Rules:**

Below we have given the basic rules of the game which are necessary in order for the user to participate and in order for the game to work:

1. A team must consist of at least 1 goalkeeper, 4 defenders, 3 midfielders and 3 forwards every week.
2. Team must have at least 11 (starting) players and may have no more than 15 total players.
3. All players must  be placed in their corresponding positions
4. Teams can no longer be modified when the deadline(Friday 7pm before the start of the game of the week) reaches. After Monday 6pm the users can edit their teams again.

5. Teams will be given an initial credit of 1000, the user may spend the 1000 credits as they wish.
6. Each team is given 1 free transfer every week, 1 transfer counts as selling/buying players from the market, selling players returns the number of credits they are valued at to your team, buying will deduct credits further transfers will cost 4 points from the accumulated team points, the user may make any number of transfers as long as it does not violate credit limit and team points.

7. Bench players will receive half points, captains will receive double points.









**Player Market system:**

We will assign the initial pricing of the players available in the game marketplace based on Fantasy Premier League&#39;s market. Initially we will manually assign all values ourselves. Eventually, we&#39;ll use the Fantasy Premier League API for bonus.

**Possible Extensions:**

Our main extensions with regard to the project track will be the ability to get real time data based on matches being played and calculate the resulting points into the user&#39;s team based on the performance of each player, by using the Fantasy Premier League API. Furthermore, by using this API, we can automatically adjust the prices of each player in the market, based on the ICT Index (mentioned in the marketplace system section).

[https://readthedocs.org/projects/fpl/downloads/pdf/latest/](https://readthedocs.org/projects/fpl/downloads/pdf/latest/)

[https://fantasy.premierleague.com/api/bootstrap-static](https://fantasy.premierleague.com/api/bootstrap-static/)

In addition, we will also adjust the price of the players weekly, based on the number of points they have received (performance) in the previous match week.

Furthermore, multiplayer support can be added to the original game in order for multiple users to compete against each other by creating their own personal teams and joining private leagues.



# Features

**Authors:** Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

**Feature Requirements**

**Note:**

For this section of our documentation, we have introduced a priority scheme, in which we will categorise priority of each feature that we want to implement with the respective MOSCOW method tags:

**M-** Must have

**S -** Should have

**C-** Could have

**W -** Will not have





| **ID** | **Short name** | **Description** |
| --- | --- | --- |
| F1 | Login User **(M)** | The System shall ask for the username and password and will grant access upon verification or reject access to the game.  **Motivation:**  This allows some form of data protection within the game, so that users cannot meddle with other user&#39;s teams. |
| F2 | Create Team **(M)** | The user shall be able to create one empty/non empty team that joins the global league automatically. Every team will be assigned 1000 initial credits. Users shall be able to buy players from the market with the given initial credits as long as it satisfies Feature 7 and does not exceed the initial credits stated in Feature 6.  **Motivation:** This allows the users to choose which players they would like to include in their team. Which is the main decision making part of the game that determines which users will gain more points. |
| F3.1 | Add player stats **(S)** | Users shall be able to manually add players&#39; statistics and their values based on live games played by the real players. [https://fantasy.premierleague.com/statistics](https://fantasy.premierleague.com/statistics).   **Motivation:** Allows the system to compute the points in accordance with feature 5. |
| F3.2 | Fetch player stats from API **(C)** | The System shall fetch player statistics from the premier league fantasy API. If this succeeds, users shall not enter player statistics manually.  **Motivation:** This allows for the point system to essentially be automatically executed, therefore lessening the workload that users must do to compute the respective points of each player. |
| F4 | Modification Deadline **(C)** | The game shall restrict the user from making any further changes (transfers, subtitutions, captain assignments) after the deadline period is reached, which is from Friday 7pm to Monday 7pm. This deadline applies every week.  **Motivation:** This will restrict the user from cheating by moving players during the game to receive more points |
| F5 | Points computation **(S)** | System shall compute points based on the statistics of the players that the user has manually entered or that the system fetched from the API. Taking into account if the player is a starter, bench or a captain. This feature is the foundation of the point system of the game, which decides the winners and losers of the leagues based on ranking.  **Movation:** Ranking system in the league is based on points that the team has and that decides which team wins the league. In the table below, we have listed all the possible ways in which a player of a team can receive their points: |



| F6 | Initial credits **(M)** | The game shall assign a fixed initial number of credits; which is 1000 credits. This holds for all the new teams the user creates.  **Motivation:** Everybody has an equal opportunity of creating a team based on their own decisions. |
| --- | --- | --- |
| F7.1 | Starting Lineup  Restriction **(M)** | The system shall restrict the team from receiving any points unless the team has 11 players consisting of exactly 1 goalkeeper, 4 defenders, 4 midfielders and 3 forwards.  **Motivation:** This restricts different teams from having different numbers of players with different positions. To give a more standardised form of  how teams receive their points from players. |
| F7.2 | Overall Team Restriction **(S)** | The system shall restrict the player from receiving any points unless the team has exactly 15 players which consists of at least 2 goalkeepers, 4 defenders, 4 midfielders, 3 forwards. The starting lineup consists of 11 players with 1 goalkeeper, 4 defenders, 4 midfielders and 3 forwards.  **Motivation:** This restricts different teams from having different numbers of players with different positions. To give a more standardised form of  how teams receive their points from players. |
| F8 | Captain assignment **(S)** | The system shall force the user to assign exactly one captain and vice captain of their choice for their team.  **Motivation:** This allows  the user to decide which players they feel most confident in because the captains will get double points and the vice captain will be automatically promoted to captain in F9 and will get the double points.  |
| F9 | Vice captain promotion **(C)** | The system shall promote the vice captain automatically to captain in the case that the captain of the team is injured. The system will do the same if the captain is on the bench but will ask the user to assign a new vice captain.  **Motivation:** The user has a chance to still have a player in their team to receive double points for being the captain. |
| F10 | Transfer Players **(S)** | The user shall be able to make 1 free transfer per week, selling and buying a player counts as one transfer.  Further transfers will deduct 4 points from the team overall points. If a player is sold to the market the credit value of the player is returned as credits back to the team, the user must then buy the same player or a player of the same position in return if they belong to the starting lineup.  System shall restrict the user from buying a player that costs more than the number of credits they have. System shall restrict users from transferring (selling then buying) players of different positions.  **Motivation:** This will allow for users to make desired changes in their team and a sense of freedom.  Moreover, this enables the user to make credit profit if the player they have bought in the past has gone up in credit value and is sold.   |
| F11.1 | League tournament **(M)** | The system shall automatically add teams to the Global League(can&#39;t be deleted or removed from the game) upon the team&#39;s creation. All teams joining a league start off with zero points for that league.   **Motivation:** This is the process in which teams can receive points based on the performance of the players in their team, thus allowing the system to rank each team in the global league and in separate outside leagues. Moreover, since every team is automatically in a global league, this allows for the global league to accumulate the points of each team from the point of creation to the end of season. These accumulated points will be the value of the team&#39;s overall points. |
| F11.2 | Create or join a league **(S)** | The user shall be able to create/join different leagues of their choice.  **Motivation:****  **This allows users to create a tournament with its own schedule that begins from the week that the league is created. |
| F12 | Display league rankings **(M)** | The Game shall display the league ranking to the user upon request from the user. The league ranking will consist of the names of different teams in order of their standing, the current match week and the points of each team.  **Motivation:** This allows the user to see how their team is ranked against other teams in their respective league and the global league. |

**Quality requirements**

**Authors:** Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

| **ID** | **Short name** | **Quality Attribute** | **Description** |
| --- | --- | --- | --- |
| QR1 | User experience | Usability | Users should be able to run, fully interact (input data, modify data, create teams and leagues, buy players, visualize results, etc.) and exit the game without experiencing any software malfunction, significant delay or complete crash.   **Motivation:** This quality requirement ensures that we build a high quality code with as little bug as possible, following a strategic approach so that we build a stable and modifiable system. |
| QR3 | Display team information | Responsiveness | When the user wants to see the team statistics and the statistics of each player within the team,the game shall display team and player information upon a specific command given by the user and the system will show the requested information within 1 second.  **Motivation:** This is important because the user must be able to access the information of the players and their team quickly in order to make adjustments in their team |
| QR4 | Instantaneous Results | Responsiveness | When all the match statistics of the players are provided by the user, the computed points of the team shall be available within 1 second.  **Motivation:** This ensures that the teams will receive their weekly points as fast as possible so that the league rankings can be updated before the following game week in order for the user to see the progress of each team and make desired adjustments in their teams. |
| QR5 | Up-to-date information | Availability | The game shall be able to provide information to users based on the up-to-date data stored in local file. As soon as new data is entered, the file gets updated and the newly stored information becomes available. The system shall fetch statistics from the API every week or the user can fetch the statistics manually from the api using a certain command.  **Motivation:** This allows for the game to be in sync with the real time events and statistics surrounding the premier league players |
| QR6 | Expandable | Maintainability | The game shall be easily expanded in terms of adding new teams, adding new players to teams, vice versa is also possible,  and inserting player statistics or fetching them from an api.  **Motivation:** This enables the user to make changes in their team via transfers and substitutions, enables users to create new teams in a league as well as create new leagues. |

**Java libraries**

Authors: Gilbert van Gerven, Sunny Dairam, Ricardo Burgos, Mehmet Cetin

| **Name** | **Description** |
| --- | --- |
| [Gson](https://github.com/google/gson) | Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. |
| [Time4J](https://github.com/MenoData/Time4J) | This library will help us with the scheduling of the real time matches, as well as our league scheduling system. The library provides necessary time stamps for us to fetch current times necessary to match with our deadline settings. Preventing the user from modifying their teams after a specified time. |
| [JavaFX](https://openjfx.io/)   | We will be using this library as our main GUI. We have chosen this above the others due to the fact that the design of the GUI is clean and very clear to navigate around as well as the extensive number of resources online. |
| [FXGL](https://almasb.github.io/FXGL/) | Potential extension to our JavaFX GUI, we can use this library to give a better display of the team contents, i.e. the players in a football field view with their respective real life jerseys, positioning and names. |
| [JFoenix](http://www.jfoenix.com/) | We will use JFoenix to construct a basic GUI for our game. We have chosen this GUI as a contingency plan. This is because the GUI provides clear and concise instruction on how to implement the necessary buttons, windows etc. However, we believe that JAVAFX has more useful materials online. |
