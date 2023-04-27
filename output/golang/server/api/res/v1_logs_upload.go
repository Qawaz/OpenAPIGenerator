package res

import "server/models"

type UploadLogsFileResponse interface {
	IsUploadLogsFileResponse() bool
}

type UploadLogsFileResponse200 struct {
	Schema models.Success
}

type UploadLogsFileResponse400 struct {
	Schema models.Error
}

type UploadLogsFileResponse401 struct {
	Schema models.Error
}

type UploadLogsFileResponse500 struct {
	Schema models.Error
}

func (r UploadLogsFileResponse200) IsUploadLogsFileResponse() bool {
	return true
}

func (r UploadLogsFileResponse400) IsUploadLogsFileResponse() bool {
	return true
}

func (r UploadLogsFileResponse401) IsUploadLogsFileResponse() bool {
	return true
}

func (r UploadLogsFileResponse500) IsUploadLogsFileResponse() bool {
	return true
}
