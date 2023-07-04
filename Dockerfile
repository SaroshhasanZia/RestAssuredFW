FROM sarosh/java-8-mvn3.6.3-dep
COPY src  home/apiframework/src
COPY report/index.html  home/apiframework/index.html
COPY pom.xml	home/apiframework/pom.xml
COPY testng.xml	home/apiframework/testng.xml
#COPY entrypoint.sh home/apiframework/entrypoint.sh
WORKDIR home/apiframework
CMD mvn clean test
#CMD cp home/apiframework/index.html /home/vend-sarosh/
#ENTRYPOINT ["sh", "entrypoint.sh"]
