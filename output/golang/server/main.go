package main

import (
	mux2 "github.com/gorilla/mux"
	"server/api"
)

func main() {

	router := mux2.NewRouter()

	server := &api.ServerHandler{
		Router: router,
		Config: &api.ServerConfig{
			Port:     8080,
			UseHttps: false,
		},
		Service: &DefaultService{},
	}

	server.SetupRouter()
	server.SetupServer()
	server.RunServer()
}
