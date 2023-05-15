package res

type GetApiStatusResponse interface {
    GetGetApiStatusSchema() any
    GetStatusCode() int
}

type GetApiStatusResponse200Schema struct {
	Version int `json:"version" bson:"version"`
	Status string `json:"status" bson:"status"`
}

type GetApiStatusResponse200 struct {
    Schema GetApiStatusResponse200Schema
}

func (r GetApiStatusResponse200) GetGetApiStatusSchema() any {
    return r.Schema
}

func (r GetApiStatusResponse200) GetStatusCode() int {
    return 200
}