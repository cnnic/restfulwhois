<html xmlns="http://www.w3.org/1999/xhtml">
  
  


  <head>
    <title>
      UserGuide – Restful Whois
    </title>
<p>
RESTful Whois is written in JAVA and needs a database which MySQL is selected. You can find all the required system software <a class="wiki" href="/trac/RestfulWhois/wiki/Requirements">here</a>. All the source code and deployment files are posted. You can access the <a class="ext-link" href="http://restfulwhois.org/trac/RestfulWhois/browser"><span class="icon">​</span>Browse Source</a> or visit the  <a class="ext-link" href="https://github.com/cnnic/restfulwhois"><span class="icon">​</span>github project</a>.
</p>
<p>
<h4>Installation Steps</h4>
    <ol>
      <li>
        <a href="#SoftwareInstallation">Software Installation</a>
        <ol>
          <li>
            <a href="#JDKInstallation">JDK Installation</a>
          </li>
          <li>
            <a href="#TomcatInstallation">Tomcat Installation</a>
          </li>
          <li>
            <a href="#MysqlInstallation">Mysql Installation</a>
          </li>
          <li>
            <a href="#SolrInstallation">Solr Installation</a>
          </li>
        </ol>
      </li>
      <li>
        <a href="#SystemDeployment">System Deployment</a>
        <ol>
          <li>
            <a href="#ImportdatatoMysql">Import data to Mysql</a>
          </li>
          <li>
            <a href="#Tomcatconfiguration">Tomcat configuration</a>
          </li>
          <li>
            <a href="#RDAPWeb">RDAP Web</a>
          </li>
          <li>
            <a href="#Port43WhoisProxy">Port43 Whois Proxy</a>
          </li>
        </ol>
      </li>
      <li>
        <a href="#Instruction">Instruction</a>
        <ol>
          <li>
            <a href="#DataFormats">Data Formats</a>
          </li>
          <li>
            <a href="#RDAPWeb1">RDAP Web</a>
          </li>
          <li>
            <a href="#Curl">Curl</a>
          </li>
          <li>
            <a href="#Port43Whois">Port43 Whois</a>
          </li>
        </ol>
      </li>
    </ol>

</p>
<h2 id="SoftwareInstallation">Software Installation</h2>
<h3 id="JDKInstallation">JDK Installation</h3>
<p>
We take install JDK6.0 for example.
</p>
<ul><li>Download the latest version of JDK6.0 for Linux jdk-6u45-linux-x64-rpm.bin.
</li><li>Change the authority of this file, such as :
</li></ul><pre class="wiki">$ chmod +x jdk-6u45-linux-x64-rpm.bin
</pre><ul><li>Execute the command:
</li></ul><pre class="wiki">$ jdk-6u45-linux-x64-rpm.bin
</pre><ul><li>Configure environment variable for JAVA. Modify the /etc/profile file.
</li></ul><pre class="wiki">$ vi /etc/profile
</pre><blockquote>
<p>
Add the configuration information to the end of this file.
</p>
</blockquote>
<pre class="wiki">#set java environment
export JAVA_HOME=/usr/java/jdk1.6.0_30
export CLASSPATH=$CLASSPATH:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.
export PATH=$PATH:$JAVA_HOME/bin
</pre><ul><li>Check the encironment variables.
</li></ul><pre class="wiki">$ echo $JAVA_HOME
$ echo $CLASSPATH
$ echo $PATH
</pre><ul><li>Check if the JDK is installed successfully.
</li></ul><pre class="wiki">$ java -version
</pre><h3 id="TomcatInstallation">Tomcat Installation</h3>
<ul><li>Download file apache-tomcat-6.0.33.tar.
</li><li>Decompress the tomcat file.
</li></ul><pre class="wiki">$ tar -xvf apache-tomcat-6.0.33.tar -C /usr/tomcat
</pre><ul><li>Configure the environment varialbles.
</li></ul><pre class="wiki">$ vi /etc/profile
</pre><blockquote>
<p>
Add the configuration information to the end of this file.
</p>
</blockquote>
<pre class="wiki">export CATALINA_HOME=/usr/tomcat
</pre><blockquote>
<p>
To make the configuration effective, excecute command:
</p>
</blockquote>
<pre class="wiki">$ source /etc/profile
</pre><ul><li>There are two files startup.sh and shutdown.sh under the directory of /usr/tomcat/apache-tomcat-6.0.33/bin. Execute the shell script to start and shutdown the Tomcat service.
</li></ul><pre class="wiki">$ ./startup.sh
$ ./shutdown.sh
</pre><ul><li>Access <a class="ext-link" href="http://localhost:8080"><span class="icon">​</span>http://localhost:8080</a> in the browser and you will find the welcome page of Tomcat.
</li></ul><h3 id="MysqlInstallation">Mysql Installation</h3>
<ul><li>Download MySQL-client-5.5.21-1.linux2.6.i386.rpm and MySQL-server-5.5.21-1.linux2.6.i386.rpm.
</li><li>Install Mysql server and client.
</li></ul><pre class="wiki">$ MySQL-server-5.5.21-1.linux2.6.i386.rpm
$ MySQL-client-5.5.21-1.linux2.6.i386.rpm
</pre><ul><li>Check the status of Mysql.
</li></ul><pre class="wiki">$ /etc/rc.d/init.d/mysql status
</pre><blockquote>
<p>
If the status is [FAILED], please start the Mysql service.
</p>
</blockquote>
<pre class="wiki">$ service mysql start
</pre><h3 id="ApacheTomcatConnector">Solr Installation</h3>
<ul><li> download tomcat-solr: deployment/apache-tomcat-solr.tar.gz
</li></ul><ul><li>Install and start up
</li></ul>
<pre class="wiki"> 
tar -zxvf apache-tomcat-solr.tar.gz
cd  apache-tomcat-solr
bin/startup.sh
note that this tomcat use port '9090'.
</pre>
<blockquote>
<ul><li>shutdown service.
</li></ul><pre class="wiki">
bin/shutdown.sh
</pre><h2 id="SystemDeployment">System Deployment</h2>
<h3 id="ImportdatatoMysql">Import data to Mysql</h3>
<ul><li>Download the whois.sql from: deployment/whois.sql.
</li><li>Import data to the database.
</li></ul><pre class="wiki">$ source whois.sql
</pre><h3 id="Tomcatconfiguration">Tomcat configuration</h3>
<ul><li>Modify conf/server.xml,add following to 'Connector' element:
</li></ul><pre class="wiki">URIEncoding="UTF-8"
</pre><ul><li>Configure the user roles of digest authentication in tomcat-user.xml file. Currently, there are 3 types of roles, they are root, authenticated and administrator. Anonymous, authenticated and root user can access the rdap.restfulwhois.org to make queries. Administrators can access rdap.restfulwhois.org/admin to log in and configure the system parameters of back end. Following is an example of user configuration.
</li></ul><pre class="wiki">&lt;role rolename="authenticated"/&gt;
&lt;role rolename="root"/&gt;
&lt;role rolename="administrator"/&gt;
  
 
&lt;user username="auth" password="auth" roles="authenticated"/&gt;
&lt;user username="root" password="root" roles="root"/&gt;
&lt;user username="admin" password="admin" roles="administrator"/&gt;
</pre><ul><li>Restart Tomcat.
</li></ul><h3 id="RDAPWeb">RDAP Web</h3>
<ul><li>download war from:deployment/whois.war,unzip and copy to /usr/tomcat/webapps/ROOT.
<pre class='wiki'>
rm -rf /usr/tomcat/webapps/ROOT/*
unzip whois.war
cp -r whois/*  /usr/tomcat/webapps/ROOT/
</pre>
</li>

<li>config file: WEB-INF/classes/whois.properties
<pre class='wiki'>
solr.url：change to solr service url
serverurl: change to rdap service url
</pre>
</li>

<li>config file: WEB-INF/classes/jdbc.properties
<pre class='wiki'>
jdbc.url：change to mysql service url
jdbc.username: change to mysql username
jdbc.password: change to mysql password
</pre>
</li>

<li>Restart tomcat.
</li></ul><h3 id="Port43WhoisProxy">Port43 Whois Proxy</h3>
<ul><li>Copy all the files under the directory of deployment/Whois43Proxy to /usr/whoisProxy.
</li><li>Configure environment parameter.
</li></ul><pre class="wiki">$ export WHOISPROPERTIES_HOME=/usr/whoisProxy/bin
$ nohup ./startup.sh &
</pre><h2 id="Instruction">Instruction</h2>
<h3 id="DataFormats">Data Formats</h3>
<p>
We provide JSON, XML and html data formats.
</p>
<h3 id="RDAPWeb1">RDAP Web</h3>
<p>
Select data format and input the query information.
</p>
<h3 id="Curl">Curl</h3>
<pre class="wiki">curl -H Accept:application/json rdap.restfulwhois.org/ip/1.0.0.0
curl -H Accept:application/xml rdap.restfulwhois.org/domain/qq.cn
curl -H Accept:application/html rdap.restfulwhois.org/entity/APNIC
curl rdap.restfulwhois.org/autnum/1223
curl rdap.restfulwhois.org/nameserver/ns2.ee28.cn
</pre><h3 id="Port43Whois">Port43 Whois</h3>
<pre class="wiki">whois -h rdap.restfulwhois.org 1.0.0.0
whois -h rdap.restfulwhois.org qq.cn
whois -h rdap.restfulwhois.org entity APNIC
whois -h rdap.restfulwhois.org as 1223
whois -h rdap.restfulwhois.org ns ns2.ee28.cn
</pre><h3>Configuration Restful Whois OauthAPI SDK Demo</h3>

<ul>
	<li>download the consumer project, find <strong>src/consumer.properties</strong> file
		modify "sample.serviceProvider.baseURL" value, the value from whois project url
		Following is an example of user configuration.
	</li>
</ul>
<pre>
	sample.serviceProvider.baseURL: http://localhost:8080/
</pre>

<ul>
	<li>modify "sample.consumerKey、sample.consumerSecret" value
		to database : whois --> table : oauth_users_app --> column value : app_key, app_secret
		Following is an example of user configuration.
	</li>
</ul>
<pre>
	sample.consumerKey: key1385973838215
	sample.consumerSecret: secret1385973838215
</pre>


</div>
          
        

    
  </body>
</html>

