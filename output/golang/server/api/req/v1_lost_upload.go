package req

type UploadLogFileRequestSchema struct {
	IsEncrypted string `json:"is_encrypted" bson:"is_encrypted"`
}

type UploadLogFileRequest struct {
	Schema        UploadLogFileRequestSchema
	BearerToken   string
	DecodingError error
}
