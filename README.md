# Tzeva Adom Tracker
Spring Boot service that allows anyone to get real-time **Tzeva Adom** notifications by exposing an endpoint within their web appications.\
The alert detection is provided by a [separate library](https://github.com/DavidTheExplorer/Tzeva-Adom-API) I wrote, which may be used on any Java project interested to get notifications.

## Usage
To register an endpoint, one needs to register an account in order to get an **API Token** - which identifies their account.\
Once a Tzeva Adom happens, a *POST* request is sent to all registered endpoints.\
Every request contains an Authorization header with the user's **API Token** - without it the endpoint should ignore the request!

Example of request's payload:\
![image](https://github.com/DavidTheExplorer/Tzeva-Adom-Tracker/assets/69223217/661095a9-ada9-4fd5-a7fd-afb98aff59c8)


## Console Logging
Notifications are also logged to the console:
![image](https://github.com/DavidTheExplorer/Tzeva-Adom-Tracker/assets/69223217/bac8725f-eae8-4d0a-81a4-e5e87b2272c7)
* When a registered endpoint cannot be notified(e.g. offline), the exact error is logged.

## How to Install
The project uses Docker - so all you need to do is to download the latest image: \
`docker pull ghcr.io/davidtheexplorer/tzeva-adom-tracker:master`\
To run, create a container and expose the port `8080`.
