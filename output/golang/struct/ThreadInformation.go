package main

type ThreadInformation struct {
	Thread_id int `json:"thread_id" bson:"thread_id"`
	Is_interrupted bool `json:"is_interrupted" bson:"is_interrupted"`
	Thread_name string `json:"thread_name" bson:"thread_name"`
	Is_alive bool `json:"is_alive" bson:"is_alive"`
	Thread_traces []StackTraceElem `json:"thread_traces" bson:"thread_traces"`
	Thread_state string `json:"thread_state" bson:"thread_state"`
	Is_daemon bool `json:"is_daemon" bson:"is_daemon"`
	Priority int `json:"priority" bson:"priority"`
}