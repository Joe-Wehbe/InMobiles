# InMobiles
<a href="https://www.inmobiles.net/">InMobiles</a> 
is a global information and communications technology provider, present since 2006 
in the Middle East, Africa, Europe, Asia and the American continent, that develops and delivers 
world-class innovative solutions. Since 2015, InMobiles became a member of the network of 
entrepreneurs for EMENA with a continuous work program. Additionally, InMobiles was elected 
a member of the ISAG Board of Directors for 2021-2022 and a GSMA Open Connectivity SMS hub 
solution provider. Since its establishment in 2006, it has taken up the challenge 
of reshaping the future of technology by delivering the best-integrated communication solutions 
to facilitate the flow of SMS, voice, and data across global markets.
InMobiles was voted best VAS supplier for 2020. Customers include Alfa, Touch, Korek, Orange, Zain, 
Etisalat, KCell... 

# Repository Overview 
This repository includes all of the Java training projects that I completed during my internship at InMobiles. It is composed of three Eclipse workspaces: <br>
> `ClientWorkspace` which contains all the client applications that interact with the servers <br>
> `ServerWorkspace` which contains all the server programs that I developed <br>
> `OtherWorkspace ` which contains all Java exercises that do not require client-server implementation <br>

Below is a table that associates each client program to its server:
| ClientWorkspace | ServerWorkspace |
| ------ | ------ |
| TCPclient | TCPserver |
| UDPclient | UDPserver |
| - | RemoteServer |
| GroupChatClient | GroupChatServer |
| ClientWebAPI | ServerWebAPI <br> ServerWebAPI-DB |

#### TCPclient/TCPserver
> A simple client-server application that uses `Transmission Control Protocol (TCP)` to establish a connection and allow communication on the localhost.

#### UDPclient/UDPserver
> A simple client-server application that uses `User Datagram Protocol (UDP)` to allow communication between the client and the server on the localhost.

#### -/RemoteServer
> A simple client-server application that uses Transmission Control Protocl (TCP) to establish a connection and allow communication between my machine (server) and a remote client (developed by another intern).

#### GroupChatClient/GroupChatServer
> A multi-threaded client-server application that features an implementation of a group chat system where multiple clients can communicate with each other via messages.

#### ClientWebAPI/ServerWebAPI
> A client-server application that allows a client to manage users using web APIs `GET` `POST` `DELETE` `PUT`. This application entails `JSON` serialization/deserialization, and users are stored in a hashmap.

#### ClientWebAPI/ServerWebAPI-DB 
> The client-side implementation remains the same as the one aforementioned, but the server was updated to replace the hashmap by a `MySQL` database.
