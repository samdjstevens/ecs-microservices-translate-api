# Translate API

A demo HTTP API that translates text from a source language to a target language.

## Building

To build a docker image for the application, run the below command:

```bash
./build.sh
```

This will compile the application and build a Docker image.


## Running with Docker Compose

Once the image has been built, it can be ran locally usinf Docker Compose with the below command:

```bash
docker-compose up
```



## Using the API 

Clients can consume the API by issuing a HTTP request to the `translate` endpoint as below:

```bash
curl --location --request POST 'http://localhost:8080/translate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sourceLangCode": "en",
    "targetLangCode": "de",
    "text": "Hello World!"
}'
```

