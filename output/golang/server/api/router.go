package api

import (
    "encoding/json"
    "log"
    "net/http"
    token "server/api/auth"
    "server/api/req"
)

type DefaultApiRouter interface {
    GetApiStatus(http.ResponseWriter, *http.Request)
    GenerateToken(http.ResponseWriter, *http.Request)
    UploadLogFile(http.ResponseWriter, *http.Request)
    ListLogs(http.ResponseWriter, *http.Request)
}

type ServiceRouterBridge struct {
    Service Service
}

func (b ServiceRouterBridge) GetApiStatus(w http.ResponseWriter, r *http.Request) {
    
    response := b.Service.GetApiStatus()
    w.Header().Set("Content-Type", "application/json")
    encoder := json.NewEncoder(w)
    w.WriteHeader(response.GetStatusCode())
    err := encoder.Encode(response.GetGetApiStatusSchema())
    if err != nil {
        log.Fatal(err.Error())
    }
}

func (b ServiceRouterBridge) GenerateToken(w http.ResponseWriter, r *http.Request) {
    
    response := b.Service.GenerateToken()
    w.Header().Set("Content-Type", "application/json")
    encoder := json.NewEncoder(w)
    w.WriteHeader(response.GetStatusCode())
    err := encoder.Encode(response.GetGenerateTokenSchema())
    if err != nil {
        log.Fatal(err.Error())
    }
}

func (b ServiceRouterBridge) UploadLogFile(w http.ResponseWriter, r *http.Request) {
        bearerToken := token.ExtractTokenFromAuthorizationHeader(r)
    request := req.UploadLogFileRequest{
        Schema:      req.UploadLogFileRequestSchema{},
        Request:     r,
        Writer:      w,
        BearerToken: bearerToken,
    }
        
            request.Schema.IsEncrypted = strings.EqualFold(r.PostFormValue("is_encrypted"),"true")   

    response := b.Service.UploadLogFile(request)
    w.Header().Set("Content-Type", "application/json")
    encoder := json.NewEncoder(w)
    w.WriteHeader(response.GetStatusCode())
    err := encoder.Encode(response.GetUploadLogFileSchema())
    if err != nil {
        log.Fatal(err.Error())
    }
}

func (b ServiceRouterBridge) ListLogs(w http.ResponseWriter, r *http.Request) {
        bearerToken := token.ExtractTokenFromAuthorizationHeader(r)
    request := req.ListLogsRequest{
        Schema:      req.ListLogsRequestSchema{},
        Request:     r,
        Writer:      w,
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
    w.WriteHeader(response.GetStatusCode())
    err = encoder.Encode(response.GetListLogsSchema())
    if err != nil {
        log.Fatal(err.Error())
    }
}

