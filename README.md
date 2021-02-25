This is the readme file for implementing filter and bakery locks in Java. For analysis and conclusion of results read here: https://medium.com/furkankamaci/implementing-filter-and-bakery-locks-in-java-234cbf545354

# How To Compile Application?

Project is developed as a maven project. So, 

	mvn clean install

will be enough. There are also maven plugins for downloading licenses and analyzing application code with Sonar and they can be run individually.

# How To Run Application?

Application has a command line interface which will guide you to run it. When you navigate to
distribution folder or target/hw1-1.0 folder (not directly under target folder) you can use that
instructions:

	java -jar filbak-{version}.jar -t [THREAD LIST] -m [MAX ITERATION NUMBER] -r [RETRY COUNT]

 	-a,--about                get info about application
	-m,--maxNumber <arg>      loop up to that maximum number
	-r,--retryCount <arg>     number of retry count to test each thread
	-t,--threadCounts <arg>   comma separated thread count list

i.e. application default is:

	java -jar filbak-1.0.jar -t 1,2,3,4,5,6,7,8 -r 20 -m 10000000

# Where is Output?

Errors and algorithm outputs are printed into System output but application is configured to log algorithm 
specific outputs into log file of it too. When you run the application a folder with a name "logs" will
be created and under that folder:

	FurkanKAMACI_filbak.log

is algorithm output which can be used for analysis.

## Author

Furkan KAMACI


