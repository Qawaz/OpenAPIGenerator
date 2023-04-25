package main

type LogEntry struct {
	DeviceState DeviceState `json:"device_state" bson:"device_state"`
	TimeFired *int `json:"time_fired" bson:"time_fired"`
	Values map[string]string `json:"values" bson:"values"`
	Tag *string `json:"tag" bson:"tag"`
	StackTrace *string `json:"stack_trace" bson:"stack_trace"`
	Type *string `json:"type" bson:"type"`
	Message *string `json:"message" bson:"message"`
}