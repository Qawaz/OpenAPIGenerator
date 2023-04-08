package main

type ThreadInformation struct {
	ThreadId int `json:"thread_id" bson:"thread_id"`
	IsInterrupted bool `json:"is_interrupted" bson:"is_interrupted"`
	ThreadName string `json:"thread_name" bson:"thread_name"`
	IsAlive bool `json:"is_alive" bson:"is_alive"`
	ThreadTraces []StackTraceElem `json:"thread_traces" bson:"thread_traces"`
	ThreadState string `json:"thread_state" bson:"thread_state"`
	IsDaemon bool `json:"is_daemon" bson:"is_daemon"`
	Priority int `json:"priority" bson:"priority"`
}