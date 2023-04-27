package res

import "server/models"

type ListLogsResponse interface {
	IsListLogsResponse() bool
}

type ListLogsResponse200Schema struct {
	Logs []models.DatabaseLogEntry `json:"logs"`
}

type ListLogsResponse200 struct {
	Schema ListLogsResponse200Schema
}

type ListLogsResponse400 struct {
	Schema models.Error
}

type ListLogsResponse401 struct {
	Schema models.Error
}

type ListLogsResponse500 struct {
	Schema models.Error
}

func (r ListLogsResponse200) IsListLogsResponse() bool {
	return true
}

func (r ListLogsResponse400) IsListLogsResponse() bool {
	return true
}

func (r ListLogsResponse401) IsListLogsResponse() bool {
	return true
}

func (r ListLogsResponse500) IsListLogsResponse() bool {
	return true
}
