package api

import (
	"encoding/json"
	"log"
	"net/http"
	token "server/api/auth"
	"server/api/req"
)

type DefaultApiRouter interface {
	UploadLogFile(http.ResponseWriter, *http.Request)
	ListLogs(http.ResponseWriter, *http.Request)
}

type ServiceRouterBridge struct {
	Service Service
}

func (b ServiceRouterBridge) UploadLogFile(w http.ResponseWriter, r *http.Request) {
	bearerToken := token.ExtractTokenFromAuthorizationHeader(r)
	request := req.UploadLogFileRequest{
		Schema:      req.UploadLogFileRequestSchema{},
		BearerToken: bearerToken,
	}
	decoder := json.NewDecoder(r.Body)
	err := decoder.Decode(&request.Schema)
	if err != nil {
		request.DecodingError = err
	}
	response := b.Service.UploadLogsFile(request)
	w.Header().Set("Content-Type", "application/json")
	encoder := json.NewEncoder(w)
	err = encoder.Encode(response)
	if err != nil {
		log.Fatal(err.Error())
	}
}

func (b ServiceRouterBridge) ListLogs(w http.ResponseWriter, r *http.Request) {
	bearerToken := token.ExtractTokenFromAuthorizationHeader(r)
	request := req.ListLogsRequest{
		Schema:      req.ListLogsRequestSchema{},
		BearerToken: bearerToken,
	}
	decoder := json.NewDecoder(r.Body)
	err := decoder.Decode(&request.Schema)
	if err != nil {
		request.DecodingError = err
	}
	response := b.Service.ListLogs(request)
	w.Header().Set("Content-Type", "application/json")
	encoder := json.NewEncoder(w)
	err = encoder.Encode(response)
	if err != nil {
		log.Fatal(err.Error())
	}
}
