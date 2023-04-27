package api

import (
	"encoding/json"
	"log"
	"net/http"
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
	request := req.UploadLogFileRequest{Schema: req.UploadLogFileRequestSchema{}}
	decoder := json.NewDecoder(r.Body)
	err := decoder.Decode(&request.Schema)
	if err != nil {
		request.DecodingError = err
	}
	response := b.Service.UploadLogsFile(request)
	encoder := json.NewEncoder(w)
	err = encoder.Encode(response)
	if err != nil {
		log.Fatal(err)
	}
}

func (b ServiceRouterBridge) ListLogs(w http.ResponseWriter, r *http.Request) {
	request := req.ListLogsRequest{Schema: req.ListLogsRequestSchema{}}
	decoder := json.NewDecoder(r.Body)
	err := decoder.Decode(&request.Schema)
	if err != nil {
		request.DecodingError = err
	}
	response := b.Service.ListLogs(request)
	encoder := json.NewEncoder(w)
	err = encoder.Encode(response)
	if err != nil {
		log.Fatal(err)
	}
}
