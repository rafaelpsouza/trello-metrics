Trello Metrics
=============

* Extract time per list from trello cards

## Compile and create dist file
```sbt compile dist```

## Generating trello credentials
https://trello.com/1/appKey/generate

### Request a token granting read-only access forever:
```
https://trello.com/1/authorize?key=substitutewithyourapplicationkey&name=My+Application&expiration=never&response_type=token
```

## Running Trello Metrics

Unzip trello-metrics/target/universal/trello-metrics-1.0.zip and run ```./trello-metrics-1.0/bin/trello-metrics <board-id> <list-name> <token> <key>```

* **list-name**: List name (from **board-id**) containing cards that you want to extract metrics;
* **token and key**: Trello API token and key (see Generating trello credentials section).