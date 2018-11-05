If you check the source code you will find what is this project about.

To run tests:
```
mvn test
```

To build project:

```
mvn clean package 
```

To run project (first need to build the package):
```
java -jar target/dragons-1.0-SNAPSHOT.jar
```

Application can be tweaked at `src/main/resources/application.yml`. 
Currently the score is calculated based on three factors:
1) Probability (arbitrary order). Can be changed/expanded `voters.probability.probability-scores`
2) Message/Ad classification (Found out that only "Steal .. and share some profit" affects reputation negatively - hence negative score). Can be changed/expanded `voters.textclassifications` 
3) Reward (Prioritize higher reward). Can be changed `voters.reward.multiplier`

At the time of this commit highest score is `4321` achieved on game id `U1itzPAK`