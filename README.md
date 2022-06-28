# scraping-tokopedia-agzw

### Requirement
- Java 1.8
- Maven
- Chrome Driver (use the same driver version as your chrome version) link download : https://chromedriver.storage.googleapis.com/index.html
- set path of chrome driver in application.properties

### Project Build
mvn clean install

### JAR Build
mvn clean compile assembly:single

Then, JAR file can be found in target directory.

### Run
java -jar {jar_file} [count]

Count parameter to determine how much to retrieve data.
example : java -jar scraping-tokopedia-agzw-1.1.0.1-SNAPSHOT-jar-with-dependencies.jar 100
that's mean you want to get 100 data

CSV file will be provided with a name in the following format: Product_TOKOPEDIA-SCRAPING-AGZW_<timeInMillis>.csv
