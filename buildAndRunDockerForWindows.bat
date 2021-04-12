call mvn clean package
call docker build -t fi.ptuomaal/ping .
call docker rm -f ping
call docker run -d -p 9990:9990 -p 8080:8080 -p 4848:4848 --name ping fi.ptuomaal/ping