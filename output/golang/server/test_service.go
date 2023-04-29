package main

import (
	"log"
	"server/api/req"
	"server/api/res"
	"server/models"
)

type TestService struct {
}

func (service TestService) ListLogs(request req.ListLogsRequest) res.ListLogsResponse {
	if request.DecodingError != nil {
		log.Printf("ERROR : %s", request.DecodingError.Error())
	}
	log.Printf(
		"token %s pkg %s page %d",
		request.BearerToken,
		request.Schema.PackageName,
		request.Schema.Page,
	)
	return res.ListLogsResponse200{
		Schema: res.ListLogsResponse200Schema{
			Logs: []models.DatabaseLogEntry{},
		},
	}
}

func (service TestService) UploadLogsFile(request req.UploadLogFileRequest) res.UploadLogsFileResponse {
	if request.DecodingError != nil {
		log.Printf("ERROR : %s", request.DecodingError.Error())
	}
	log.Printf(
		"token %s is_encrypted %s",
		request.BearerToken,
		request.Schema.IsEncrypted,
	)
	return res.UploadLogsFileResponse200{
		Schema: models.Success{Success: true},
	}
}
