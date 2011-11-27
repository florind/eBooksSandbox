mkdir /tmp/txtr
cd /tmp/txtr
curl -O http://txtr.com/reaktor/data/javaclient-1.20.12.zip
unzip javaclient-1.20.12.zip
mvn install:install-file -Dfile=/tmp/txtr/server.wsapi-1.20.12.jar -DgroupId=com.bookpac \
	    -DartifactId=server -Dversion=1.20.12 -Dpackaging=jar
mvn install:install-file -Dfile=/tmp/txtr/server.wsapi-core-1.20.12.jar -DgroupId=com.bookpac \
	    -DartifactId=server.wsapi-core -Dversion=1.20.12 -Dpackaging=jar
