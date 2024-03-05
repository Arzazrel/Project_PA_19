DESCRIPTION
	Folder of the application built in Java (using ) for the Advanced Programming project of the bachelor's degree in computer engineering at the University of Pisa.
	This application allows multiple users to record their running sessions, calculate the calories and fat burned and display their training sessions over time in a graph.
	This examination and its documentation are in Italian (language used in the bachelor's degree).

The folder contains:
	This cpp files:
	- Server.cpp: contains the code relating to the server; 
    	- Client.cpp: contains the code relating to the client;
    	- Common.h: contains variables and functions useful for both the server and the client.
	
	This document:
	- Password.txt: The passwords for the private keys of the server and the three pre-registered users are stored in this file. 
 			The passwords are required to initiate the authenticated and secure connection between the client and the server.
	- FoC_Documentation.pdf: the documentation of the project that explain protocol, the format of the packets and so on...

	This folder:
	- ClientFiles: containing all files (keys, certificates, files) concerning the client;

Commands used to compile and run:
	- to compile (The project folder also contains the two executables of the server and client compiled with these commands)
		-- with debug
		g++ server.cpp -lssl -lcrypto -pthread -g -o server
		g++ client.cpp -lssl -lcrypto -g -o client
		-- without debug
		g++ server.cpp -lssl -lcrypto -pthread -o server
		g++ client.cpp -lssl -lcrypto -o client

	- run (with example arguments)
		./server 5000
		./client 127.0.0.1 5000 UserA

Developer's notes:
	The work related to the university examination is finished and the project is completed. 
	There may be updates or improvements to the project in the future, but nothing is planned for now.

Developers:
	- Alessandro Diana