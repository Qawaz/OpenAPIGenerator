package res

import "server/models"

type ListLogsResponse interface {
    GetListLogsSchema() any
    GetStatusCode() int
}

type ListLogsResponse200Schema struct {
	Logs []models.DatabaseLogEntry `json:"logs" bson:"logs"`
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

func (r ListLogsResponse200) GetListLogsSchema() any {
    return r.Schema
}

func (r ListLogsResponse200) GetStatusCode() int {
    return 200
}

func (r ListLogsResponse400) GetListLogsSchema() any {
    return r.Schema
}

func (r ListLogsResponse400) GetStatusCode() int {
    return 400
}

func (r ListLogsResponse401) GetListLogsSchema() any {
    return r.Schema
}

func (r ListLogsResponse401) GetStatusCode() int {
    return 401
}

func (r ListLogsResponse500) GetListLogsSchema() any {
    return r.Schema
}

func (r ListLogsResponse500) GetStatusCode() int {
    return 500
}