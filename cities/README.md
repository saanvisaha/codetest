
Test code for MasterCard inc is done .

Go to target file and run command 
java -jar Cities-0.0.1-SNAPSHOT.jar run web serive .


_______________________________________________________________________________________________
Code test details below

Problem statement:

 

Two cities are considered connected if there’s a series of roads that can be travelled from one city to another.

 

List of roads is available in a file.

File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

 

It will be deployed as a spring-boot app and expose one endpoint:

http://localhost:8080/connected?origin=city1&destination=city2

 

Your program should respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.

Any unexpected input should result in a ’no’ response.

 

Example:

city.txt content is:

Boston, New York

Philadelphia, Newark, Edison

Newark, Boston

Trenton, Albany

 

http://localhost:8080/connected?origin=Boston&destination=Newark

Should return yes

http://localhost:8080/connected?origin=Boston&destination= Philadelphia

Should return yes

http://localhost:8080/connected?origin=Philadelphia&destination=Albany

Should return no

 

 Once solved, please share the public GIT repo with us. “

 

 

Let me know if you have questions.

All the best!

 