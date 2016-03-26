# kosmos-example

Simpliest example of using kosmos and configs for kosmos-web and nrepl..

## Configuration File

```clj
{
 :web {
   :kosmos/init :kosmos.web/RingJettyComponent
   :ring-app kosmos-example/app
   :port 1111
  } 
  :nrepl {
    :kosmos/init :kosmos.nrepl/NreplComponent
    :port 5001 
   } 
}
```

I put this file in resources 

## Usage

run the system with: 


``` 
lein run
```

Visit server url (localhost:1111) in browser to see the swagger UI for the clojure-api application. 

## Development

The repl will switch to the user namespace upon loading. Type `(dev)` to switch to dev namespace and load the repl functions. Use `(go)` to start the jetty server and you can develop and `(reset)` as needed. 






