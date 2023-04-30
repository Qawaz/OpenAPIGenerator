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
			Logs: []models.DatabaseLogEntry{{
				Notes:           nil,
				Log:             nil,
				CreatedAt:       nil,
				Id:              nil,
				State:           "",
				Device:          models.DeviceInformation{},
				AppInformation:  models.AppInformation{},
				UserInformation: models.UserInformation{},
			}},
		},
	}
}

func (service TestService) UploadLogFile(request req.UploadLogFileRequest) res.UploadLogFileResponse {
	if request.DecodingError != nil {
		log.Printf("ERROR : %s", request.DecodingError.Error())
	}
	log.Printf(
		"token %s is_encrypted %t",
		request.BearerToken,
		request.Schema.IsEncrypted,
	)
	return res.UploadLogFileResponse200{
		Schema: models.Success{Success: true},
	}
}
