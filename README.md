Trello Metrics
=============

Scala project to extract project metrics from Trello

## Create dist file
activator dist

## Generating trello credentials
https://trello.com/1/appKey/generate

### Request a token granting read-only access forever:
```
https://trello.com/1/authorize?key=substitutewithyourapplicationkey&name=My+Application&expiration=never&response_type=token
```
