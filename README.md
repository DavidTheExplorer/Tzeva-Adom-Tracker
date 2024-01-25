# Tzeva Adom Tracker
Spring Boot service that allows anyone to get real-time **Tzeva Adom** notifications by exposing an endpoint within their web appications.\
The alert detection is provided by a [separate library](https://github.com/DavidTheExplorer/Tzeva-Adom-API) I wrote, which may be used on any Java project interested to get notifications.

## Usage
To register an endpoint, one needs to register an account in order to get an **API Token** - which identifies their account.\
Once a Tzeva Adom happens, a *POST* request is sent to all registered endpoints.\
Every request contains an Authorization header with the user's **API Token** - without it the endpoint should ignore the request!

Example of request's payload:\
![image](https://github.com/DavidTheExplorer/Tzeva-Adom-Tracker/assets/69223217/14514cd6-ef58-4bb5-9018-5ad4b0018896)

## Console Logging
Notifications are also logged to the console:
![image](https://github.com/DavidTheExplorer/Tzeva-Adom-Tracker/assets/69223217/91168d57-db87-4b07-81de-eaa587e6f206)
* When a registered endpoint cannot be notified(e.g. offline), the exact error is logged.

## How to Install
The project uses Docker - so all you need to do is to download the latest image: \
`docker pull ghcr.io/davidtheexplorer/tzeva-adom-tracker:master`\
To run, create a container and expose the port `8080`.
