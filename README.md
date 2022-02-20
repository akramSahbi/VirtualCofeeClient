# VirtualCofeeClient
java client for VirtualCofeeServer

# VirtualCofeeServer
is an RPC server using soap web service implemented in java to find common slots for a coffee break online.

<h1>Installation Instructions:</h1>

<h2>Requirements</h2>
<ul>
  <li>you must have apache maven installed and added to system PATH variable</li>
  <li>you must have java JDK 1.8 installed from oracle and added to system PATH variable</li>
  <li>you must configure your project settings to use JDK 1.8</li>
</ul>    



<h2>Running the application:</h2>

<ol>
  <li>go to command prompt (DOS)</li>
  <li>cd to VirtualCofeeClient folder</li>
  <li>cd to src\main\java</li>
  <li>wsimport -keep -verbose "http://localhost:7779/ws/coffee?wsdl"</li>
  #where <strong>localhost</strong> is the hostname or ipaddress configured for the server
  <li>cd back to VirtualCofeeClient folder</li>
//compile and run the application
  <li>mvn clean install</li> 
  <li>mvn exec:java -Dexec.args="-s '8am' -e '12pm' -o 'gmt+1' -n '3' -h '127.0.0.1'"</li>
</ol>

<h2>Usage:</h2>
usage: mvn exec:java -Dexec.args="-s '8am' -e '8pm' -o 'gmt+1' -n '3' -h
           '127.0.0.1'"
           <br>
 -e <arg>   You must provide end time like "8am" or "8pm" or any other
            valid time in this format
  <br>
 -h <arg>   You can provide the host ip address which you will connect to
            or the dns name like "127.0.0.1" or "localhost"
  <br>
 -n <arg>   You must provide the number of participants which must be
            between 1 and 3
  <br>
 -o <arg>   You must provide offset from GMT in the format "gmt+x" or
            "gmt-x" where x is the offset of hours from gmt
  <br>
 -s <arg>   You must provide start time like "8am" or "8pm" or any other
            valid time in this format
  <br>
