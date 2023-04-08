package main

type LogEntry struct {
	device_state DeviceState
	time_fired int
	values map[string]string
	tag string
	stack_trace string
	type string
	message string
}