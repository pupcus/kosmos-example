# kosmos-example

Simpliest example of using kosmos and config  for kosmos-web and nrepl..

## Configuration File

resources/config.edn

```clj
{
  :web {
    :kosmos/init :kosmos.web/RingJettyComponent
    :join? false
    :ring-app kosmos-example/app
    :port 1111
  } 
  :nrepl {
    :kosmos/init :kosmos.nrepl/NreplComponent
    :port 5001 
  } 
}

```



## Usage

Standalone, run the app with: 

``` 
lein run
```

Visit server url (localhost:1111) in browser to see the swagger UI for the clojure-api application. 

Connect to the nrepl with

```
lein repl :connect 5001
```

## Reloaded Style Development

The repl will switch to the user namespace upon loading. Type `(dev)` to switch to dev namespace and load the repl functions. Use `(go)` to start the application. Develop and `(reset)` as needed. 

More [info on reloaded](http://thinkrelevance.com/blog/2013/06/04/clojure-workflow-reloaded).
