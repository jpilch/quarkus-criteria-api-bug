# foobar

This repo was made for easy reproduction of a bug with the Criteria API and quarkus reactive hibernate orm.

To get the error, it is enough to start the application in dev mode and execute the request defined in `src/main/kotlin/org/acme/find-items.http`.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.
