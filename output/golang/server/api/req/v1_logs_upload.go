package req

import "net/http"

type UploadLogFileRequestSchema struct {
	IsEncrypted bool `json:"is_encrypted" bson:"is_encrypted"`
}

type UploadLogFileRequest struct {
	Schema        UploadLogFileRequestSchema
    Writer        http.ResponseWriter
    Request       *http.Request
	BearerToken   string
	DecodingError error
}