# ScoreBoard
ScoreBoard is a library implemented as a part of recruitment process to SportRadar company. 
It's purpose is to keep scores of ongoing Football Worldcup games and allow starting/updating/finishing and receiving summary of such games. 
I've assumed that since in real world World Cup Video Assistant Referee is present, updating of scores won't be limited to just increments of one of the team scores. 
This solution allows to both deduct scored goals (if they are overturned by VAR) and also updating them to values user wants. 
I assumed it's better to allow easy correction of mistakes instead of being to overzelous with validations.