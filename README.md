# StreamerCommandCenter
Utility Application for every day needs of a Streamer

## Features
- Go Live Tweet
- Twitch Chat Replication Bot for co-streams
- Launch videoclip in java window with vlc engine 
- Trigger video clips in co-stream via chat

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

Video clips are configured in scc_clips.xml file
