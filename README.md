# StreamerCommandCenter
Utility Application for every day needs of a Streamer

## Features
- Go Live Tweet
- Launch videoclip in vlc
- Twitch Chat Replication Bot for co-streams 

MORE TO COME

## Build
Run Maven build on commandline:
mvn clean package -DskipTests

This creates StreamerCommandCenter.jar in dist folder.

## Run
Double click on jar or:
java -jar dist/StreamerCommandCenter.jar

It expects the mcc.properties right next to it or in src/main/resources

You can create mcc.properties.dev and mcc.properties.secret files for custom property values.
