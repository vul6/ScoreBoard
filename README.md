# ScoreBoard
ScoreBoard is a library implemented as a part of recruitment process to SportRadar company. 
It's purpose is to keep scores of ongoing Football Worldcup games and allow starting/updating/finishing and receiving summary of such games. 
I've assumed that since in real world World Cup Video Assistant Referee is present, updating of scores won't be limited to just increments if one of the team scores. 
This solution allows to both deduct scored goals (if they are overturned by VAR) and also updating them to values user wants. 
I assumed it's better to allow easy correction of mistakes instead of being too overzelous with validations.

ScoreBoard can be easily added as a maven dependency if some system with artifactory is available. It could be also turned into Spring Beans if needed. 
Main class with user prompt could also be added, but requirements said it should be a library. Use cases can be analysed throught Test classes, especially ScoreBoardService.java.