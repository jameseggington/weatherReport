Weather Report program uses apache Tomcat 6.0.44, Axis2 1.6.2 and JFreeChart-1.0.19 and run in Eclipse as IDE
In order to run the program Axis2 runtime is need to installed and also apache Tomcat for application server to be specified.
download link for Axis2 and Tomcat6.0.44:
Axis2: http://axis.apache.org/axis2/java/core/download.cgi
Tomcat6.0.44: apache.mirror.serversaustralia.com.au/tomcat/tomcat-6/v6.0.44/bin/apache-tomcat-6.0.44.zip
To point Eclipse to Axis 2 runtime go (Preferences | Web services | Axis2 Preferences)
To specified an application server  (Preferences | Server | Runtime Environments | Add... )
The program also uses external library 'JFreeChart' which can be download here: http://sourceforge.net/projects/jfreechart/files/1.%20JFreeChart/
To add this in, download and extract the zip file. Right click on project folder (Properties|Java Build Path|Add External JARS...)

Create Dynamic Web Project on Eclipse by File>Others>Web>Dynamic Web Project. 
Target runtime change to Apache Tomcat v6.0
Dynamic web module version is 2.5

Create Web Service Client by File>Others>Web Service>Web Service Client
on service definition fill:
http://viper.infotech.monash.edu.au:8180/axis2/services/MelbourneWeather2?wsdl
and change Configuration(Server runtime Tomcat 6.0 server| Web service runtime Apache Axis2)
Click next and choose Port Name MelbourneWeather2HttpSoap12Endpoint
and finish
repeat the process but on service definition fill:
http://viper.infotech.monash.edu.au:8180/axis2/services/MelbourneWeatherTimeLapse?wsdl
instead
Click next and choose Port Name MelbourneWeatherTimeLapseHttpSoap12Endpoint
Drag the folder controller, model and userinterface to in SVN in to src folder in the created Dynamic Web Project.

To run the program, run on Driver class which is in controller package


