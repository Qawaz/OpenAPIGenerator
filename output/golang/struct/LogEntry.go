package main

type LogEntry struct {
	Device_state DeviceState `json:"device_state" bson:"device_state"`
	Time_fired int `json:"time_fired" bson:"time_fired"`
	Values map[string]string `json:"values" bson:"values"`
	Tag string `json:"tag" bson:"tag"`
	Stack_trace string `json:"stack_trace" bson:"stack_trace"`
	Type string `json:"type" bson:"type"`
	Message string `json:"message" bson:"message"`
}