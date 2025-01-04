
**Run instance 1**
```
java -jar -Dhazelcast.network.port=5701 -Dinstance.name=instance1 -Dserver.port=8081 target/hz-0.0.1-SNAPSHOT.jar
```

**Run instance 2**
```
java -jar -Dhazelcast.network.port=5702 -Dinstance.name=instance2 -Dserver.port=8082 target/hz-0.0.1-SNAPSHOT.jar
```

**Run management console jar [download from Hazelcast website]**
```
java -jar hazelcast-management-center-5.6.0.jar
```

**Access management console UI available**
```
http://localhost:8080
```

![img.png](img.png)