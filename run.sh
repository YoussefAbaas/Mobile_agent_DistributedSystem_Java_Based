#!/bin/bash
#Installations of java
sudo apt update
sudo apt install default-jdk
echo java -version
sudo apt install openjdk-8-jdk
sudo apt install software-properties-common
sudo add-apt-repository ppa:linuxuprising/java
sudo apt update
sudo apt install oracle-java11-installer
sudo update-alternatives --config java
#Compile
javac  'Server.java' 'Intermediate.java' 'AreaComputers.java' 'Sensors.java'
#Run the app
gnome-terminal --title="Server" --tab -- java Server 
sleep 1
gnome-terminal --title="Intermediate" --tab -- java Intermediate 
sleep 1
gnome-terminal --title="AreaComputers" --tab -- java AreaComputers
sleep 1
gnome-terminal --title="Sensors" --tab -- java Sensors
sleep 1