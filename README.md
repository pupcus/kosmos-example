# kosmos-example

Simpliest example of using kosmos and configs for kosmos-web and nrepl..

## Configuration File


```clj
;; resources/config.edn
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

run the system with: 


``` 
lein run
```

Visit server url (localhost:1111) in browser to see the swagger UI for the clojure-api application. 

## Reloaded Style Development


The repl will switch to the user namespace upon loading. Type `(dev)` to switch to dev namespace and load the repl functions. Use `(go)` to start the jetty server and you can develop and `(reset)` as needed. 






