# Wumpus-world
### This project implements inference engine for the wumpus world problem using knowledge based AI agent.

### How to run the Code
        - Compile using command:
          javac -classpath . CheckTrueFalse.java
	
        - Execute using command:
	        java CheckTrueFalse my_wampus_rules.txt [additional_knowledge_file] [statement_file] 
          for example: java CheckTrueFalse my_wampus_rules.txt my_kb.txt my_statement.txt

        - Command to retrieve execution time:
          time java CheckTrueFalse my_wampus_rules.txt [additional_knowledge_file] [statement_file]  
          for example: time java CheckTrueFalse my_wampus_rules.txt my_kb.txt my_statement.txt
          
### Note
        Wumpus world rules (my_wampus_rules.txt) and other sample knowledge bases have been included in 'Inputs' folder for  
        trial purpose.

