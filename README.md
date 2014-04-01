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
1. install jdk 1.6 ,or higer verison,downlad from: http://www.oracle.com/technetwork/java/javase/downloads/index.html
2. install git client:
	yum install git
3. create work dir, as: $WORK_DIR , eg: /home/guest/whois
4. download source from github

	```
	git config --global http.sslVerify false 
	git clone http://github.com/cnnic/restfulwhois.git
	```
5. install maven
	
	```
	cd  $WORK_DIR 
	cd restfulwhois/deployment 
	tar -zxvf apache-maven-3.1.tar.gz 
	export PATH=`pwd`/apache-maven-3.1/bin:$PATH 
	(export this PATH to .bash_profile is best) 
	chmod a+x apache-maven-3.1/bin/mvn
	```
6. build project
	
	```
	cd  $WORK_DIR 
	cd restfulwhois/source/whois
	mvn package  (this may take some minutes. )
	ll target/whois.war (whois.war is the artifact)
	```
7. install mysql  (use port 3306)

	```install 5.5. download from http://dev.mysql.com/downloads/mysql/5.5.html and install.```
8. import data to mysql
      
	```
	download mysql data: https://github.com/cnnic/restfulwhois/blob/master/deployment/whois.sql
	login mysql : 
		mysql -h127.0.0.1 -uroot -p
	add user, execute commands:
		GRANT ALL PRIVILEGES ON *.* TO 'whois'@'%' IDENTIFIED BY 'cnnic';
		FLUSH  PRIVILEGES;
	import data, execute commands: 
		source whois.sql;
	```
9. install solr (use port 9090)
	
	```
	cd  $WORK_DIR
	cd restfulwhois/deployment
	tar -zxvf apache-tomcat-solr.tar.gz
	update configure file: vi apache-tomcat-solr/conf/Catalina/localhost/solr.xml :
		In secode line ,'docBase' set to :"$WORK_DIR/restfulwhois/deployment/apache-tomcat-solr/solr/solr-4.5.0.war"
		In third line ,'value' set to :"$WORK_DIR/restfulwhois/deployment/apache-tomcat-solr/solr"
		note: both '$WORK_DIR'  must replaced by the existing real file path .
	startup solr:  
		./apache-tomcat-solr/bin/startup.sh 
	```
10. deploy whois webapp  (use port 8080)
      	
	```
	cd  $WORK_DIR
	cd restfulwhois/deployment
	tar -zxvf apache-tomcat-whois.tar.gz
	cd  $WORK_DIR
	cd restfulwhois
	cp source/whois/target/whois.war deployment/apache-tomcat-whois/webapps/ROOT/
	cd deployment/apache-tomcat-whois/webapps/ROOT
	unzip whois.war
	if mysql or solr service port are changed, should configure property file (if not please ignore this step):
		cd WEB-INF/classes
		update confiuration file :
			in file jdbc.properties: 'jdbc.url' change to mysql url, if mysql in install on localhost, no need to change;
			in file whois.properties: 'solr.url' change to solr url, if solr install on localhost ,no need to change
	cd  $WORK_DIR
	cd restfulwhois/deployment/apache-tomcat-whois
	bin/startup.sh
	(done, whois webapp service url is : http://localhost:8080)
	```
11. deploy whois port43 proxy

	```
	cd  $WORK_DIR
	cd restfulwhois/deployment/whois43Proxy
	update configraution file: vi startUp.sh :
		update 'WHOISPROPERTIES_HOME' value to current path
	./startUp.sh
	```
12. test for whois webapp
	
	1>.use robotframework:
	
	```
		install python and pip first,then install robotframework:
			pip install robotframework
			pip install --upgrade robotframework-httplibrary
		cd  $WORK_DIR
		cd restfulwhois/source/whois/test/robot
		pybot .
			and this command will generate test result : report.html/output.xml/log.html
	```
	2>.use curl:

	```
		curl -H Accept:application/json rdap.restfulwhois.org/ip/1.0.0.0
		curl -H Accept:application/xml rdap.restfulwhois.org/domain/qq.cn
		curl -H Accept:application/html rdap.restfulwhois.org/entity/APNIC
		curl rdap.restfulwhois.org/autnum/1223
		curl rdap.restfulwhois.org/nameserver/ns2.ee28.cn
	```
13. test for port43 whois

	```
	whois -h rdap.restfulwhois.org 1.0.0.0
	whois -h rdap.restfulwhois.org qq.cn
	whois -h rdap.restfulwhois.org entity APNIC
	whois -h rdap.restfulwhois.org as 1223
	whois -h rdap.restfulwhois.org ns ns2.ee28.cn
	```
14. configuration oauth if need oauth support:

	oauth consumer project dir is restfulwhois/source/consumer
	update configuration file : src/consumer.properties :
	
	```
		sample.serviceProvider.baseURL: http://localhost:8080/
		sample.consumerKey: rdap db table oauth_users_app 's column 'app_key'
		sample.consumerSecret" rdap db table oauth_users_app 's column 'app_secret'
	```
