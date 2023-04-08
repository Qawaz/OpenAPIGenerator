package main

type ThreadInformation struct {
	thread_id int
	is_interrupted bool
	thread_name string
	is_alive bool
	thread_traces []StackTraceElem
	thread_state string
	is_daemon bool
	priority int
}